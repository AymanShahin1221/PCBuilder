package com.app.service.api;

import java.io.IOException;
import java.time.LocalTime;

public interface ApiService {
    String getImgUrl(String keywords) throws IOException;
    int getRateLimit();
    LocalTime getResetTime();
}
