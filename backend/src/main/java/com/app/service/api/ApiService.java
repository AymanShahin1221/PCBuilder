package com.app.service.api;

import java.io.IOException;
import java.time.LocalTime;

/**
 * Interface defining methods for interacting with an external API.
 */
public interface ApiService {

    /**
     *
     * @param keywords search term to use when looking for corresponding image url
     * @return string representing url of image
     */
    String getImgUrl(String keywords) throws IOException;

    /**
     *
     * @return an integer representing the api's api call limit
     */
    int getRateLimit();

    /**
     *
     * @return a LocalTime object representing the time of rate limit reset
     */
    LocalTime getResetTime();
}
