package com.app.entity.model;

import com.app.service.db.DBUpdateService;
import jakarta.persistence.*;

import java.sql.SQLException;
import java.util.UUID;

@Entity
@Table
public class Motherboard extends PCPart implements Upsertable
{

    @Id
    private UUID pid;

    @Column(unique = true)
    private String name;

    private Double price;
    private String socket;
    private String form_factor;
    private Integer max_memory;
    private Integer memory_slots;
    private String color;
    private String imageLocation;

    public Motherboard()
    {
    }

    public Motherboard(UUID pid, String name,
                       Double price, String socket,
                       String form_factor, Integer max_memory,
                       Integer memory_slots, String color)
    {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.socket = socket;
        this.form_factor = form_factor;
        this.max_memory = max_memory;
        this.memory_slots = memory_slots;
        this.color = color;
    }

    public Motherboard(String name, Double price,
                       String socket, String form_factor,
                       Integer max_memory, Integer memory_slots,
                       String color)
    {
        this.name = name;
        this.price = price;
        this.socket = socket;
        this.form_factor = form_factor;
        this.max_memory = max_memory;
        this.memory_slots = memory_slots;
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

    public String getSocket()
    {
        return socket;
    }

    public void setSocket(String socket)
    {
        this.socket = socket;
    }

    public String getForm_factor()
    {
        return form_factor;
    }

    public void setForm_factor(String form_factor)
    {
        this.form_factor = form_factor;
    }

    public Integer getMax_memory()
    {
        return max_memory;
    }

    public void setMax_memory(Integer max_memory)
    {
        this.max_memory = max_memory;
    }

    public Integer getMemory_slots()
    {
        return memory_slots;
    }

    public void setMemory_slots(Integer memory_slots)
    {
        this.memory_slots = memory_slots;
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
        return "Motherboard{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", socket='" + socket + '\'' +
                ", form_factor='" + form_factor + '\'' +
                ", max_memory=" + max_memory +
                ", memory_slots=" + memory_slots +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public void insertPart(DBUpdateService dbUpdateService) throws SQLException
    {
        dbUpdateService.upsertMotherboardTable(this);
    }
}
