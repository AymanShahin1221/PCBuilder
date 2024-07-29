package com.app.service.db;

import com.app.entity.model.PCPart;
import com.app.exception.InvalidApiResponseException;
import com.app.exception.MaxCallsReachedException;
import com.app.service.api.ApiService;
import com.app.service.util.DBUtils;
import com.app.service.util.ImageDownloader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.app.service.util.DBUtils.getClassInstance;
import static com.app.service.util.DelayUtils.delay;

/**
 * This service class is responsible for updating the img_location column for every category table in the databse.
 * baseImagesDirectory is the root dir containing all product related images, stored in their respective categories
 * Each part in the database is mapped to a corresponding img location within the directory
 * Format of image name: <product_id>.jpg
 */
@Service
public class PartImageService {

    private final Connection connection;
    private final ApiService apiService;
    private final int MAX_API_CALLS_PER_DAY;
    private final PartService partService;
    private int currentApiCallCount = 0;

    private static final Logger logger = LoggerFactory.getLogger(PartImageService.class);

    @Autowired
    public PartImageService(@Qualifier("ebayApiService") ApiService apiService, PartService partService) {
        this.connection = DBUtils.initDBConnection();
        this.apiService = apiService;
        this.partService = partService;
        this.MAX_API_CALLS_PER_DAY = apiService.getRateLimit();
    }

    /**
     *
     * @param table represents category table
     * @return a map of products where they key is the pid and value is the part name
     */
    public <T extends PCPart> Map<String, String> fetchPartIdsAndNames(Class<T> table) {
        Map<String, String> partsMap = new HashMap<>();

        JSONArray partsJsonArray = partService.getAllPartsByCategory(table);

        for(int i = 0; i < partsJsonArray.length(); i++)
        {
            JSONObject cpuJsonData = partsJsonArray.getJSONObject(i);
            String pid = cpuJsonData.getString("pid");
            String name = cpuJsonData.getString("name");

            partsMap.put(pid, name);
        }
        return partsMap;
    }

    /**
     *
     * @param table represents category table
     * @param part part name
     * @param imageLocation  corresponding image associated with product
     */
    private void updateTable(String table, String part, String imageLocation) throws SQLException {
        PreparedStatement preparedStatement = null;
        try
        {
            String stmt = "UPDATE " + table + " " +
                          "SET image_location = ? " +
                          "WHERE name = ?";

            preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setObject(1, imageLocation);
            preparedStatement.setObject(2, part);

            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            logger.error("Error while updating image locations in table {}", table);
        }
        finally
        {
            if (preparedStatement != null)
                preparedStatement.close();
        }
    }

    /**
     * Goes through each table in database and downloads the product image, and sets the image location to the location of the downloaded image.
     * Ex. If a fetched image has url https://i.ebayimg.com/images/g/Cr0AAOSwaLZiiL2N/s-l1600.jpg then it would be downloaded and stored in its respective category:
     * <category_name>/<pid>.jpg
     * In the database, each product image name is in the form <pid>.jpg
     *
     * Also keeps track of current API call count
     * If this limit is exceed, the delay will be invoked
     */
    public void updateImagesInTables() throws SQLException, InterruptedException, IOException, MaxCallsReachedException, InvalidApiResponseException {

        for(String table : DBUtils.CATEGORY_TABLES)
        {
            Map<String, String> partsMap = fetchPartIdsAndNames(getClassInstance(table));
            for(String pid : partsMap.keySet())
            {
                String partName = partsMap.get(pid);

                String imgUrl;
                try
                {
                    imgUrl = apiService.getImgUrl(partName);
                    currentApiCallCount++;
                }

                catch(MaxCallsReachedException mcre)
                {
                    logger.info("Max calls reached while updating {}. Delaying...", table);

                    delay(apiService.getResetTime());
                    currentApiCallCount = 0;

                    imgUrl = apiService.getImgUrl(partName);
                    currentApiCallCount++;
                }

                catch(InvalidApiResponseException iare)
                {
                    logger.warn("Could not make request while updating images. Resetting API token...");

                    apiService.refreshAPIToken();
                    imgUrl = apiService.getImgUrl(partName);
                    currentApiCallCount++;
                }

                if(currentApiCallCount > MAX_API_CALLS_PER_DAY)
                {
                    logger.info("Max calls exceeded while updating {}. Delaying...", table);

                    currentApiCallCount = 0;
                    delay(apiService.getResetTime());
                }

                if(imgUrl != null)
                {
                    String fname = pid + ".jpg";
                    ImageDownloader.downloadImage(imgUrl, fname, table);
                    String pathName = table + "\\" + fname;
                    updateTable(table, partName, pathName);
                }
                else
                    updateTable(table, partName, null);
            }
        }
        logger.info("Finished updating product images");
    }
}
