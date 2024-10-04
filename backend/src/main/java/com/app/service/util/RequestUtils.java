package com.app.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Utility class used for making HTTP requests
 */

public class RequestUtils
{
    private static final Logger logger = LoggerFactory.getLogger(RequestUtils.class);

    public static ResponseEntity<String> makeGetRequest(String endpoint, HttpHeaders httpHeaders, RestTemplate restTemplate)
    {
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        try
        {
            return restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity, String.class);
        } catch (RestClientException rce)
        {
            logger.error("Failed to send HTTP request to {}", endpoint);
            throw rce;
        }
    }
}
