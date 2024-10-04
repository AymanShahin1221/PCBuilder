package com.app.entity.model;

import com.app.service.db.DBUpdateService;
import jakarta.persistence.*;

import java.sql.SQLException;
import java.util.UUID;

@Entity
@Table
public class PowerSupply extends PCPart implements Upsertable
{

    @Id
    private UUID pid;

    @Column(unique = true)
    private String name;

    private Double price;
    private String type;
    private String efficiency;
    private Integer wattage;
    private String modular;
    private String color;
    private String imageLocation;

    public PowerSupply()
    {
    }

    public PowerSupply(UUID pid, String name,
                       Double price, String type,
                       String efficiency, Integer wattage,
                       String modular, String color)
    {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.type = type;
        this.efficiency = efficiency;
        this.wattage = wattage;
        this.modular = modular;
        this.color = color;
    }

    public PowerSupply(String name, Double price,
                       String type, String efficiency,
                       Integer wattage, String modular,
                       String color)
    {
        this.name = name;
        this.price = price;
        this.type = type;
        this.efficiency = efficiency;
        this.wattage = wattage;
        this.modular = modular;
        this.color = color;
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

    public String getEfficiency()
    {
        return efficiency;
    }

    public void setEfficiency(String efficiency)
    {
        this.efficiency = efficiency;
    }

    public Integer getWattage()
    {
        return wattage;
    }

    public void setWattage(Integer wattage)
    {
        this.wattage = wattage;
    }

    public String getModular()
    {
        return modular;
    }

    public void setModular(String modular)
    {
        this.modular = modular;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
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
        return "PowerSupply{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", efficiency='" + efficiency + '\'' +
                ", wattage=" + wattage +
                ", modular='" + modular + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public void insertPart(DBUpdateService dbUpdateService) throws SQLException
    {
        dbUpdateService.upsertPowerSupplyTable(this);
    }
}
