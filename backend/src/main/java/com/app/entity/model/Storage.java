package com.app.entity.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table
public class Storage extends PCPart {

    @Id
    private UUID pid;

    @Column(unique = true)
    private String name;

    private Double price;
    private Integer capacity;
    private Double price_per_gb;
    private String type;
    private Integer cache;
    private String form_factor;
    private String interface_;
    private String imageLocation;

    public Storage() {  }

    public Storage(UUID pid, String name,
                   Double price, Integer capacity,
                   Double price_per_gb, String type,
                   Integer cache, String form_factor,
                   String interface_) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.capacity = capacity;
        this.price_per_gb = price_per_gb;
        this.type = type;
        this.cache = cache;
        this.form_factor = form_factor;
        this.interface_ = interface_;
    }

    public Storage(String name,
                   Double price, Integer capacity,
                   Double price_per_gb, String type,
                   Integer cache, String form_factor,
                   String interface_) {
        this.name = name;
        this.price = price;
        this.capacity = capacity;
        this.price_per_gb = price_per_gb;
        this.type = type;
        this.cache = cache;
        this.form_factor = form_factor;
        this.interface_ = interface_;
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Double getPrice_per_gb() {
        return price_per_gb;
    }

    public void setPrice_per_gb(Double price_per_gb) {
        this.price_per_gb = price_per_gb;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCache() {
        return cache;
    }

    public void setCache(Integer cache) {
        this.cache = cache;
    }

    public String getForm_factor() {
        return form_factor;
    }

    public void setForm_factor(String form_factor) {
        this.form_factor = form_factor;
    }

    public String getInterface_() {
        return interface_;
    }

    public void setInterface_(String interface_) {
        this.interface_ = interface_;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", capacity=" + capacity +
                ", price_per_gb=" + price_per_gb +
                ", type='" + type + '\'' +
                ", cache=" + cache +
                ", form_factor='" + form_factor + '\'' +
                ", interface_='" + interface_ + '\'' +
                '}';
    }
}
