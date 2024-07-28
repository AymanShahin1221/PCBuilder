package com.app.service.util;

import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

public class EbayApiUtils {

    private static final String CLIENT_ID = System.getenv("CLIENT_ID");
    private static final String CLIENT_SECRET = System.getenv("CLIENT_SECRET");
    private static final String TOKEN_URL = "https://api.ebay.com/identity/v1/oauth2/token";

    public static String getAccessToken() {

        RestTemplate restTemplate = new RestTemplate();

        String auth = CLIENT_ID + ":" + CLIENT_SECRET;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Basic " + encodedAuth);

        String requestBody = "grant_type=client_credentials&scope=https://api.ebay.com/oauth/api_scope";

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                TOKEN_URL,
                HttpMethod.POST,
                entity,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.OK)
        {
            String responseBody = response.getBody();
            JSONObject jsonObject = new JSONObject(responseBody);

            return jsonObject.getString("access_token");
        }
        else
            throw new RuntimeException("Failed to get access token: " + response.getStatusCode());
    }
}
