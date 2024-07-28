package com.app.service.api;

import com.app.exception.InvalidApiResponseException;
import com.app.exception.MaxCallsReachedException;
import com.app.service.util.RequestUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalTime;
import com.app.service.util.JsonUtils;

/**
 * This service class is responsible for using the Ebay Shopping API to fetch product images.
 */

@Service
@Qualifier("ebayApiService")
public class EbayApiService implements ApiService {

    private static final String API_TOKEN = System.getenv("API_TOKEN");
    private final RestTemplate restTemplate;

    private static final String BASE_URL = "https://open.api.ebay.com/shopping?";

    private static final Logger logger = LoggerFactory.getLogger(EbayApiService.class);

    @Autowired
    public EbayApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private HttpHeaders createHeadersForRequest() {
        HttpHeaders headers = new HttpHeaders();

        headers.set("X-EBAY-API-IAF-TOKEN", "Bearer" + API_TOKEN);
        headers.set("X-EBAY-API-SITE-ID", "0");
        headers.set("X-EBAY-API-CALL-NAME", "FindProducts");
        headers.set("X-EBAY-API-VERSION", "863");
        headers.set("X-EBAY-API-REQUEST-ENCODING", "xml");

        return headers;
    }

    private String constructApiEndpoint(String searchTerm) {
        String encodedSearchTerm = searchTerm.replace(' ', '+');

        return BASE_URL + "callname=FindProducts&" +
                          "responseencoding=JSON&" +
                          "siteid=0&" +
                          "version=967&" +
                          "QueryKeywords=" + encodedSearchTerm + "&" +
                          "AvailableItemsOnly=false&" +
                          "MaxEntries=1&";
    }

    /**
     *
     * @param jsonObject json response object
     * @return a boolean representing whether the JSON response is valid
     * @throws MaxCallsReachedException if the response indicates max call limit has been exceeded
     */
    private boolean validateJSONResponse(JSONObject jsonObject) throws MaxCallsReachedException, InvalidApiResponseException {

        if(jsonObject.has("Errors"))
        {

            JSONArray errors = jsonObject.getJSONArray("Errors");
            JSONObject error = errors.getJSONObject(0);

            if (error.getString("ShortMessage").equals("No match found."))
                return false;

            else if (error.getString("ShortMessage").equals("IP limit exceeded."))
                throw new MaxCallsReachedException("Max api calls reached.");

            else
                throw new InvalidApiResponseException("JSON response contains error(s). Probable cause: invalid API token");

        }
        return true;
    }

    /**
     * Returns a string url of a product image
     * @param searchTerm search term for product
     */
    public String getImgUrl(String searchTerm) throws MaxCallsReachedException, InvalidApiResponseException {

        logger.info("Fetching image url for \"{}\"", searchTerm);

        String apiEndpoint = constructApiEndpoint(searchTerm);
        HttpHeaders headers = createHeadersForRequest();

        ResponseEntity<String> response = RequestUtils.makeGetRequest(apiEndpoint, headers, restTemplate);
        if (response.getStatusCode().is2xxSuccessful())
        {
            JSONObject jsonData = JsonUtils.stringToJsonObject(response.getBody());
            if(!validateJSONResponse(jsonData))
            {
                logger.info("No match found for \"{}\"", searchTerm);
                return null;
            }

            JSONArray products = jsonData.getJSONArray("Product");
            JSONObject product = products.getJSONObject(0);
            boolean hasImage = product.getBoolean("DisplayStockPhotos");

            if(hasImage)
            {
                logger.info("Image URL found for search term \"{}\"", searchTerm);
                return product.getString("StockPhotoURL");
            }
        }
        return null;
    }
    
    public int getRateLimit() { return 5000; }

    /**
     * reset: T07:00:00.000Z
     */
    public LocalTime getResetTime() { return LocalTime.of(4, 0); }
}
