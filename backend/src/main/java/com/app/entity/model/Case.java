package com.app.entity.model;

import com.app.service.db.DBUpdateService;
import jakarta.persistence.*;

import java.sql.SQLException;
import java.util.UUID;

@Entity
@Table(name = "cases")
public class Case extends PCPart implements Upsertable
{

    @Id
    private UUID pid;

    @Column(unique = true)
    private String name;

    private Double price;
    private String type;
    private String color;
    private Integer psu;
    private String sidePanel;
    private Double external_volume;
    private Integer internal_35_bays;
    private String imageLocation;

    public Case()
    {
    }

    public Case(UUID pid, String name,
                Double price, String type,
                String color, Integer psu,
                String sidePanel, Double external_volume,
                Integer internal_35_bays)
    {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.type = type;
        this.color = color;
        this.psu = psu;
        this.sidePanel = sidePanel;
        this.external_volume = external_volume;
        this.internal_35_bays = internal_35_bays;
    }

    public Case(String name, Double price,
                String type, String color,
                Integer psu, String sidePanel,
                Double external_volume, Integer internal_35_bays)
    {
        this.name = name;
        this.price = price;
        this.type = type;
        this.color = color;
        this.psu = psu;
        this.sidePanel = sidePanel;
        this.external_volume = external_volume;
        this.internal_35_bays = internal_35_bays;
    }

    public UUID getPid()
    {
        return pid;
    }

    public void setPid(UUID pid)
    {
        this.pid = pid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public Integer isPsu()
    {
        return psu;
    }

    public void setPsu(Integer psu)
    {
        this.psu = psu;
    }

    public String getSidePanel()
    {
        return sidePanel;
    }

    public void setSidePanel(String sidePanel)
    {
        this.sidePanel = sidePanel;
    }

    public Double getExternal_volume()
    {
        return external_volume;
    }

    public void setExternal_volume(Double external_volume)
    {
        this.external_volume = external_volume;
    }

    public Integer getInternal_35_bays()
    {
        return internal_35_bays;
    }

    public void setInternal_35_bays(Integer internal_35_bays)
    {
        this.internal_35_bays = internal_35_bays;
    }

    public void setImageLocation(String imageLocation)
    {
        this.imageLocation = imageLocation;
    }

    public String getImageLocation()
    {
        return imageLocation;
    }

    @Override
    public String toString()
    {
        return "Case{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", psu=" + psu +
                ", sidePanel='" + sidePanel + '\'' +
                ", external_volume=" + external_volume +
                ", internal_35_bays=" + internal_35_bays +
                '}';
    }

    @Override
    public void insertPart(DBUpdateService dbUpdateService) throws SQLException
    {
        dbUpdateService.upsertCaseTable(this);
    }
}
