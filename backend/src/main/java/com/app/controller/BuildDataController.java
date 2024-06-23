package com.app.controller;

import com.app.entity.data.BuildSetupData;
import com.app.service.redis.RedisService;
import com.app.service.form.BuildDataService;

import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BuildDataController {

    private final RedisService redisService;
    private final BuildDataService buildDataService;

    private static final String BUILD_DATA_KEY = "currentBuildInfo";

    @Autowired
    public BuildDataController(RedisService redisService, BuildDataService buildDataService) {
        this.redisService = redisService;
        this.buildDataService = buildDataService;
    }

    @PostMapping("/api/storeBuildData")
    public void storeBuildSetupData(@RequestBody BuildSetupData buildData, HttpServletResponse response) {
        buildDataService.processBuildData(buildData);
        redisService.saveBuildSetupData(BUILD_DATA_KEY, buildData);

        Cookie cookie = new Cookie("BuildData", BUILD_DATA_KEY);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @GetMapping("/api/getBuildData")
    public BuildSetupData getBuildSetupData() {
        return redisService.getBuildSetupData(BUILD_DATA_KEY);
    }
}
