package com.app.entity.data;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table
public class PCBuild
{

    @Id
    @Column(updatable = false, nullable = false)
    private UUID buildId;

    @Column(length = 50)
    private String buildName;

    @Column(precision = 10, scale = 2)
    private BigDecimal budget;

    @Column(length = 400)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "pcBuild", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BuildParts> buildParts;

    public PCBuild()
    {
    }

    public PCBuild(UUID buildId, String buildName, BigDecimal budget, String description, User user, List<BuildParts> buildParts)
    {
        this.buildId = buildId;
        this.buildName = buildName;
        this.budget = budget;
        this.description = description;
        this.user = user;
        this.buildParts = buildParts;
    }

    @Override
    public String toString()
    {
        return "PCBuild{" +
                "buildId=" + buildId +
                ", buildName='" + buildName + '\'' +
                ", budget=" + budget +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", buildParts=" + buildParts +
                '}';
    }

    public UUID getBuildId()
    {
        return buildId;
    }

    public void setBuildId(UUID buildId)
    {
        this.buildId = buildId;
    }

    public String getBuildName()
    {
        return buildName;
    }

    public void setBuildName(String buildName)
    {
        this.buildName = buildName;
    }

    public BigDecimal getBudget()
    {
        return budget;
    }

    public void setBudget(BigDecimal budget)
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

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public List<BuildParts> getBuildParts()
    {
        return buildParts;
    }

    public void setBuildParts(List<BuildParts> buildParts)
    {
        this.buildParts = buildParts;
    }
}
