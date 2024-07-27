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

    private static final String API_TOKEN = "v^1.1#i^1#p^1#f^0#I^3#r^0#t^H4sIAAAAAAAAAOVYfWwURRS/u16LfBQMFCEFzbEF/qDZu9m92/vY9M5ce6U9vvpxpUBBy+zuXLuyt3vs7NEeGNPUCKQxaIwFxRjrB4FoEEMIiMZCBIMkkhA0oIQ/CAkIAU2UIJrwh7N3R7lWAkgvsYmXu1zmzZs37/eb92beDOgpGb9gc/3m26XWcbaBHtBjs1qZiWB8SXHl5CJbebEF5ClYB3rm9th7i65WYZhQknwzwklNxcjRnVBUzGeEQSqlq7wGsYx5FSYQ5g2Rj4WXLuFZJ+CTumZooqZQjmgkSAksEuN+TwC4yY/jBCJV79ps0YIU4jycFyDgBYIYB24v6cc4haIqNqBqBCkWsB4a+GjW28IEePIFnDPg5tooRyvSsaypRMUJqFDGXT4zVs/z9cGuQoyRbhAjVCgaXhhrCEcjtctaqlx5tkI5HmIGNFJ4eKtGk5CjFSop9OBpcEabj6VEEWFMuULZGYYb5cN3nXkM9zNUxzmRZRESPF4oIA/0F4TKhZqegMaD/TAlskTHM6o8Ug3ZSD+MUcKG8AISjVxrGTERjTjMv6YUVOS4jPQgVVsdXhVubKRC4XQCqrFOSDfWVKdkRUJ0Y3OE5hDnkwKSn6PdgiiyjAfmJspay9E8YqYaTZVkkzTsWKYZ1Yh4jUZyA/K4IUoNaoMejhumR3l6LHOXQzbQZi5qdhVTRqdqritKECIcmebDV2BotGHospAy0JCFkR0ZioIUTCZliRrZmYnFXPh04yDVaRhJ3uXq6upydrmdmt7hYgFgXCuXLomJnSgBKaJr5npWX374AFrOQBERGYll3kgniS/dJFaJA2oHFfIE/AzH5Hgf7lZopPQfgjzMruEZUagM8QDEMhKH3ALr9fj8vkJkSCgXpC7TDyTANJ2A+jpkJBUoIlokcZZKIF2WeDcXZ93+OKIlbyBOewLxOC1wkpdm4ggBkraCGPD/nxLlUUM9hkQdGQWJ9YLFedPGDqE6XNusN6fZbhSRcStKu2qhp823aMPi9atii1dtNNbjJYw3ioOPmg33BV+jyISZFjJ/IQgwc71wJNRr2EDSqODFRC2JGjVFFtNja4HdutQIdSMdQ4pCBKMCGU4mo4XZqwsG719uE4+Hu3Bn1H90Pt0XFTZDdmyhMsdjYgAmZad5AjlFLeEyc12DpPwwxe0Zr0eFWyaV65hCTUBm0cpStuR0ZuA68QbRqSOspXRSbTsbzAqsRVuHVHKeGbqmKEhvHV0EmPmcSKQMKChorCV2AQJchmPssGV8LKkmGHJRHBUuMXOUto+1LakQW7G97jHLatfwS37IkvkwvdavQa910Ga1giowj6kAc0qKltuLJpVj2UBOGcadWO5Qyd1VR851KJ2Esm6bZvn9g/76mvLahu0LNrWkT79zwjIp741h4Dkwc+iVYXwRMzHvyQHMvtdTzEyZUcp6gI/1MgEmALg2UHGv1848ZS87MHPb/M3n5AtXmNLpl6puTqnb+vwgKB1SslqLLfZeqyXEDYIdwatKEzev+KMtu8rqLz+796D30MXTZ26VRibMOPpmZcWJeTeeObn/zq/flYgvbXnVve/AT1PL1ff7/oKvh6TBM1XbbecDx/Yws44cvL7r1Ny+prLAFPXa7sovSo58svbKW4fTdfJv1Bs7L02tWDHh1p5J0yKT7yyCP1x/u7Su7NDOxLSL2yqalz992/+NyzJndXDr0tnhoqPHv5ou72+oexeePXyZGffa7v4vv+/rVz4+vGf+y5uOv/dk8c+TX3EvWnGh9/ztG+1PHKXP/FG52nFtHHXpw1Mv2o7dPP/L5wP2mkM/8t8iR1/TZ38O7Fw7cWV7fO+aSJfl2oJ+YcfJc2cHts7SbkT2fbrGkV3LvwGFkfMc/REAAA==";
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
