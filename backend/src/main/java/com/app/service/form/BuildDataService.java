package com.app.service.form;

import com.app.entity.data.BuildSetupData;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class BuildDataService
{

    public void processBuildData(BuildSetupData buildSetupData)
    {
        if (buildSetupData.getBuildId() == null)
        {
            UUID buildId = UUID.randomUUID();
            buildSetupData.setBuildId(buildId);
        }
        if (buildSetupData.getBuildName() == null)
        {
            String defaultBuildName = generateDefaultBuildName();
            buildSetupData.setBuildName(defaultBuildName);
        }
    }

    private String generateDefaultBuildName()
    {
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-ss");

        return "Build " + currentDate.format(formatter);
    }
}
