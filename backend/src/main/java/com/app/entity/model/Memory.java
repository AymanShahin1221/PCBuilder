package com.app.entity.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table
public class Memory extends PCPart {

    @Id
    private UUID pid;

    @Column(unique = true)
    private String name;

    private Double price;
    private Integer speed;
    private List<Integer> modules;
    private Double price_per_gb;
    private String color;
    private Double first_word_latency;
    private Integer cas_latency;
    private String imageLocation;

    public Memory() {  }

    public Memory(UUID pid, String name,
                  Double price, Integer speed,
                  List<Integer> modules, Double price_per_gb,
                  String color, Double first_word_latency,
                  Integer cas_latency) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.speed = speed;
        this.modules = modules;
        this.price_per_gb = price_per_gb;
        this.color = color;
        this.first_word_latency = first_word_latency;
        this.cas_latency = cas_latency;
    }

    public Memory(String name, Double price,
                  Integer speed, List<Integer> modules,
                  Double price_per_gb, String color,
                  Double first_word_latency, Integer cas_latency) {
        this.name = name;
        this.price = price;
        this.speed = speed;
        this.modules = modules;
        this.price_per_gb = price_per_gb;
        this.color = color;
        this.first_word_latency = first_word_latency;
        this.cas_latency = cas_latency;
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

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public List<Integer> getModules() {
        return modules;
    }

    public void setModules(List<Integer> modules) {
        this.modules = modules;
    }

    public Double getPrice_per_gb() {
        return price_per_gb;
    }

    public void setPrice_per_gb(Double price_per_gb) {
        this.price_per_gb = price_per_gb;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getFirst_word_latency() {
        return first_word_latency;
    }

    public void setFirst_word_latency(Double first_word_latency) {
        this.first_word_latency = first_word_latency;
    }

    public Integer getCas_latency() {
        return cas_latency;
    }

    public void setCas_latency(Integer cas_latency) {
        this.cas_latency = cas_latency;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    @Override
    public String toString() {
        return "Memory{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", speed=" + speed +
                ", modules=" + modules +
                ", price_per_gb=" + price_per_gb +
                ", color='" + color + '\'' +
                ", first_word_latency=" + first_word_latency +
                ", cas_latency=" + cas_latency +
                '}';
    }
}
