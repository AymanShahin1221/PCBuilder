package com.app.entity.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table
public class Cooler {

    @Id
    private UUID pid;

    @Column(unique = true)
    private String name;

    private Double price;
    private String color;
    private Integer size;
    private String imageLocation;

    public Cooler() {  }

    public Cooler(UUID pid, String name,
                  Double price, String color,
                  Integer size) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.color = color;
        this.size = size;
    }

    public Cooler(String name, Double price,
                  String color, Integer size, String imageLocation) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.size = size;
        this.imageLocation = imageLocation;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    @Override
    public String toString() {
        return "Cooler{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", size=" + size +
                '}';
    }
}
