package com.app.entity.data;

import java.io.Serializable;
import java.util.UUID;

public class BuildSetupData implements Serializable
{
    private UUID buildId;
    private String buildName;
    private Double budget;
    private String description;

    public BuildSetupData()
    {
    }

    public BuildSetupData(String buildName, Double budget, String description)
    {
        this.buildName = buildName;
        this.budget = budget;
        this.description = description;
    }

    public BuildSetupData(UUID buildId, String buildName, Double budget, String description)
    {
        this.buildId = buildId;
        this.buildName = buildName;
        this.budget = budget;
        this.description = description;
    }

    public String getBuildName()
    {
        return buildName;
    }

    public void setBuildName(String buildName)
    {
        this.buildName = buildName;
    }

    public UUID getBuildId()
    {
        return buildId;
    }

    public void setBuildId(UUID buildId)
    {
        this.buildId = buildId;
    }

    public Double getBudget()
    {
        return budget;
    }

    public void setBudget(Double budget)
    {
        this.budget = budget;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
