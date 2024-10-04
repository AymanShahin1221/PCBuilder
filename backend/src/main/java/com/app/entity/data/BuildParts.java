package com.app.entity.data;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table
public class BuildParts
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "component_id", updatable = false, nullable = false)
    private UUID componentId;

    @ManyToOne
    @JoinColumn(name = "build_id", nullable = false)
    private PCBuild pcBuild;

    @Column(name = "component_type", length = 50, nullable = false)
    private String partType;

    public BuildParts()
    {
    }

    public BuildParts(UUID componentId, PCBuild pcBuild, String partType)
    {
        this.componentId = componentId;
        this.pcBuild = pcBuild;
        this.partType = partType;
    }

    public UUID getComponentId()
    {
        return componentId;
    }

    public void setComponentId(UUID componentId)
    {
        this.componentId = componentId;
    }

    public PCBuild getPcBuild()
    {
        return pcBuild;
    }

    public void setPcBuild(PCBuild pcBuild)
    {
        this.pcBuild = pcBuild;
    }

    public String getPartType()
    {
        return partType;
    }

    public void setPartType(String partType)
    {
        this.partType = partType;
    }
}
