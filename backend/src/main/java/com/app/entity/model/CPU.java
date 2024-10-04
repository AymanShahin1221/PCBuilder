package com.app.entity.model;

import com.app.service.db.DBUpdateService;
import jakarta.persistence.*;

import java.sql.SQLException;
import java.util.UUID;

@Entity
@Table
public class CPU extends PCPart implements Upsertable
{

    @Id
    private UUID pid;

    @Column(unique = true)
    private String name;

    private Double price;
    private Integer core_count;
    private Double core_clock;
    private Double boost_clock;
    private Integer tdp;
    private String graphics;
    private Boolean smt;
    private String imageLocation;

    public CPU()
    {
    }

    public CPU(UUID pid, String name,
               Double price, Integer core_count,
               Double core_clock, Double boost_clock,
               Integer tdp, String graphics,
               Boolean smt)
    {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.core_count = core_count;
        this.core_clock = core_clock;
        this.boost_clock = boost_clock;
        this.tdp = tdp;
        this.graphics = graphics;
        this.smt = smt;
    }

    public CPU(String name, Double price,
               Integer core_count, Double core_clock,
               Double boost_clock, Integer tdp,
               String graphics, Boolean smt)
    {
        this.name = name;
        this.price = price;
        this.core_count = core_count;
        this.core_clock = core_clock;
        this.boost_clock = boost_clock;
        this.tdp = tdp;
        this.graphics = graphics;
        this.smt = smt;
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

    public Integer getCore_count()
    {
        return core_count;
    }

    public void setCore_count(int core_count)
    {
        this.core_count = core_count;
    }

    public Double getCore_clock()
    {
        return core_clock;
    }

    public void setCore_clock(Double core_clock)
    {
        this.core_clock = core_clock;
    }

    public Double getBoost_clock()
    {
        return boost_clock;
    }

    public void setBoost_clock(Double boost_clock)
    {
        this.boost_clock = boost_clock;
    }

    public Integer getTdp()
    {
        return tdp;
    }

    public void setTdp(int tdp)
    {
        this.tdp = tdp;
    }

    public String getGraphics()
    {
        return graphics;
    }

    public void setGraphics(String graphics)
    {
        this.graphics = graphics;
    }

    public void setSmt(Boolean smt)
    {
        this.smt = smt;
    }

    public Boolean getSmt()
    {
        return smt;
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
        return "CPU{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", core_count=" + core_count +
                ", core_clock=" + core_clock +
                ", boost_clock=" + boost_clock +
                ", tdp=" + tdp +
                ", graphics='" + graphics + '\'' +
                ", smt=" + smt +
                '}';
    }

    @Override
    public void insertPart(DBUpdateService dbUpdateService) throws SQLException
    {
        dbUpdateService.upsertCPUTable(this);
    }
}
