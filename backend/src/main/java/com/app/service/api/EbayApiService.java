package com.app.service.api;

import com.app.service.db.PartImageService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.time.LocalTime;
import com.app.service.util.JsonUtils;

/**
 * This service class is responsible for using the Ebay Shopping API to fetch product images.
 */

@Service
@Qualifier("ebayApiService")
public class EbayApiService implements ApiService {

    private static final String API_TOKEN = "v^1.1#i^1#I^3#r^0#p^1#f^0#t^H4sIAAAAAAAAAOVYa2wUVRTe3W5BhEoCiFj8sQwaTWFn587sc9Jd3T6wCwtburUpFUPmcbcdO49l7h22izZpyiPBd8LrB0hAo0RMiPrDSCgQio/0BwSMiaCGkAgBGxNIo4FEI97ZlrKtBJBuYhP3z2bOPffc833nnHvPvUzPlGlVmxs2X69wTnXt7WF6XE4nmM5Mm1K+6JEyV2W5gylScO7tebLH3Vt2pRoJmprlmyDKGjqCni5N1RFfEEYpy9R5Q0AK4nVBg4jHEp+OL0/yLM3wWdPAhmSolCdRF6VE0e8HYVEGQIww/ghLpPotm81GlAoHRRiRg6EAkEVOAgIZR8iCCR1hQcdRimVYv5cJelm2mQnxXIDnAB2KsG2UpwWaSDF0okIzVKzgLl+Yaxb5endXBYSgiYkRKpaIL0mn4om6+hXN1b4iW7ERHtJYwBYa+1VryNDTIqgWvPsyqKDNpy1JgghRvtjwCmON8vFbzjyA+wWqZb9AKBQBGwrKQAKgJFQuMUxNwHf3w5YosjdTUOWhjhWcvxejhA3xZSjhka8VxESizmP/rbQEVcko0IxS9TXxVfHGRioWz2uCnu4QvI21NZaiytDb2FTnDcBASI7I4YCXEyWJBX5hZKFhayM0j1up1tBlxSYNeVYYuAYSr+F4btgibohSSk+Z8Qy2PSrWi9ziMBxus4M6HEULd+h2XKFGiPAUPu8dgdHZGJuKaGE4amH8QIGiKCVks4pMjR8s5OJI+nShKNWBcZb3+XK5HJ3jaMNs97EMA3yty5NpqQNqpNi6NLvWh/WVe0/wKgUoEiQzkcLjfJb40kVylTigt1MxfyQMAmCE97FuxcZL/yEowuwbWxGlqhABhDMRMZMhYWCg7C9JhcRGktRn+wFFIe/VBLMT4qwqSNArkTyzNGgqMjGXYblwBnrlYCTj9UcyGa8YkINekIGQgVAUpUj4/1Qo95vqaSiZEJck10uW5yvXt4s18fomsynPdsE6BbXAvK9e8LeFlq5btnZVetmq9XgtSoJgAkXvtxruCL5WVQgzzWT9UhBg13rpSGgwEIbyhOClJSMLGw1VkfKTK8CcKTcKJs6noaoSwYRAxrPZRGn26pLB+5fbxIPhLt0Z9R+dT3dEheyUnVyo7PmIGBCyCm2fQLRkaD671g2BtB+2eE3B6wnhVkjnOqlQE5DDaBV5uOWkC3BptE6iTYgMyyTdNp2yO7BmoxPq5DzDpqGq0GyZWAbY9axpFhZEFU62wi5BgivCJDtsQQiQqyMIcRPDJRWO0jWTbUsqxVbsfv4B22rf2Et+zFH4gV5nP9PrPOpyOplq5imwkFkwpewFd9mMSqRgSCtChkZKu07uriakO2E+Kyima7Zj6L3tDbWV9akdVa8050/v+sYxo+iNYe9LzLzRV4ZpZWB60ZMD88TtkXIw87EK0p4HWZYJcQEOtDELb4+6wVz3nHP7/ohx4KHq+ZtF5VDqh8FWru9npmJUyeksd7h7nY5HN+xJKAfOybt2Un3VA29/der0GwPW4orano+OHequGhjY3+g7PHTT813ZiZ4byZ3J1p/Y7jPah/KuVy/MO3t4w0VuS/8zn3Xntq0uP/77b8bnQ2eObnnc+X26Hi14jjs2c7G0g6u9HHh63eLkO/veki5vv2JtmQmuft19dNPSnPPSj1vxLDO/aOoHg9tSFxY5n7XK4jeGVr+5hzt4NZDqH/x41vsHXqt8ff4vZ6uvDc65KVoHXtxgQLVzfn9f1SPJk5s+9Xhav8xfaLrY29v/7vVK7fpW9/mHO37NHA9+ccq47NAWutiOrhNB18nzsw/u1uhv++b+tXvj3EsNFUeO/Ll/weyNV9RPruWGY/k3WT83jP0RAAA=";
    private final RestTemplate restTemplate;

    private static final String BASE_URL = "https://open.api.ebay.com/shopping?";

    @Autowired
    public EbayApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public int getRateLimit() { return 5000; }


    private HttpHeaders createHeadersForRequest() {
        HttpHeaders headers = new HttpHeaders();

        headers.set("X-EBAY-API-IAF-TOKEN", "Bearer" + API_TOKEN);
        headers.set("X-EBAY-API-SITE-ID", "0");
        headers.set("X-EBAY-API-CALL-NAME", "FindProducts");
        headers.set("X-EBAY-API-VERSION", "863");
        headers.set("X-EBAY-API-REQUEST-ENCODING", "xml");

        return headers;
    }

    private boolean validateJSONResponse(JSONObject jsonObject, String keywords) {
        try
        {
            JSONArray errors = jsonObject.getJSONArray("Errors");
            JSONObject error = errors.getJSONObject(0);

            if(error.getString("ShortMessage").equals("No match found."))
            {
                System.out.println("No match found for " + keywords);
                return false;
            }
            else if(error.getString("ShortMessage").equals("IP limit exceeded."))
            {
                System.out.println("Max calls reached. Delaying...");
                return false;
            }

        }
        catch(JSONException ignored)
        {
            System.out.println("FOUND");
            return true;
        }
        return true;
    }

    /**
     * Returns a string url of a product image
     * @param keywords search term for product
     */
    public String getImgUrl(String keywords) {
        String encodedKeywords = keywords.replace(' ', '&');

        String endpoint = BASE_URL + "callname=FindProducts&" +
                                     "responseencoding=JSON&" +
                                     "siteid=0&" +
                                     "version=967&" +
                                     "QueryKeywords=" + encodedKeywords + "&" +
                                     "AvailableItemsOnly=false&" +
                                     "MaxEntries=1&";

        HttpHeaders headers = createHeadersForRequest();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try
        {
            ResponseEntity<String> response = restTemplate.exchange(endpoint, HttpMethod.GET, entity, String.class);
            if (response.getStatusCode().is2xxSuccessful())
            {
                JSONObject jsonData = JsonUtils.stringToJsonObject(response.getBody());
                if(!validateJSONResponse(jsonData, keywords))
                    return null;

                JSONArray products = jsonData.getJSONArray("Product");
                JSONObject product = products.getJSONObject(0);
                boolean hasImage = product.getBoolean("DisplayStockPhotos");

                if(hasImage)
                    return product.getString("StockPhotoURL");
            }
            else
                System.out.println("Request to " + endpoint + " failed");

            return null;
        }
        catch (RestClientException e)
        {
            System.out.println("Could not send request for keywords " + keywords);
            System.out.println("Reason: invalid request or max api call limit reached.");
            return null;
        }
        catch(JSONException e)
        {
            System.out.println("No results found for " + keywords);
            return null;
        }
    }

    /**
     * reset: T07:00:00.000Z
     */
    public LocalTime getResetTime() { return LocalTime.of(4, 0); }
}
