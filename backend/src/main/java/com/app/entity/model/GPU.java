package com.app.entity.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table
public class GPU {

    @Id
    private UUID pid;

    @Column(unique = true)
    private String name;

    private Double price;
    private String chipset;
    private Integer memory;
    private Integer core_clock;
    private Integer boost_clock;
    private String color;
    private Integer length;
    private String imageLocation;

    public GPU() {  }

    public GPU(UUID pid, String name,
               Double price, String chipset,
               Integer memory, Integer core_clock,
               Integer boost_clock, String color,
               Integer length) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.chipset = chipset;
        this.memory = memory;
        this.core_clock = core_clock;
        this.boost_clock = boost_clock;
        this.color = color;
        this.length = length;
    }

    public GPU(String name, Double price,
               String chipset, Integer memory,
               Integer core_clock, Integer boost_clock,
               String color, Integer length) {
        this.name = name;
        this.price = price;
        this.chipset = chipset;
        this.memory = memory;
        this.core_clock = core_clock;
        this.boost_clock = boost_clock;
        this.color = color;
        this.length = length;
    }

    public UUID getPid() {
        return pid;
    }

    public void setPid(UUID pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public Integer getCore_clock() {
        return core_clock;
    }

    public void setCore_clock(Integer core_clock) {
        this.core_clock = core_clock;
    }

    public Integer getBoost_clock() {
        return boost_clock;
    }

    public void setBoost_clock(Integer boost_clock) {
        this.boost_clock = boost_clock;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    @Override
    public String toString() {
        return "GPU{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", chipset='" + chipset + '\'' +
                ", memory=" + memory +
                ", core_clock=" + core_clock +
                ", boost_clock=" + boost_clock +
                ", color='" + color + '\'' +
                ", length=" + length +
                '}';
    }
}
