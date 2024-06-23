package com.app.entity.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table
public class Keyboard {

    @Id
    private UUID pid;

    @Column(unique = true)
    private String name;

    private Double price;
    private String style;
    private String switches;
    private String backlit;
    private Boolean tenkeyless;
    private String connection_type;
    private String color;
    private String imageLocation;

    public Keyboard() {  }

    public Keyboard(UUID pid, String name,
                    Double price, String style,
                    String switches, String backlit,
                    Boolean tenkeyless, String connection_type,
                    String color) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.style = style;
        this.switches = switches;
        this.backlit = backlit;
        this.tenkeyless = tenkeyless;
        this.connection_type = connection_type;
        this.color = color;
    }

    public Keyboard(String name, Double price,
                    String style, String switches,
                    String backlit, Boolean tenkeyless,
                    String connection_type, String color) {
        this.name = name;
        this.price = price;
        this.style = style;
        this.switches = switches;
        this.backlit = backlit;
        this.tenkeyless = tenkeyless;
        this.connection_type = connection_type;
        this.color = color;
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getSwitches() {
        return switches;
    }

    public void setSwitches(String switches) {
        this.switches = switches;
    }

    public String getBacklit() {
        return backlit;
    }

    public void setBacklit(String backlit) {
        this.backlit = backlit;
    }

    public Boolean isTenkeyless() {
        return tenkeyless;
    }

    public void setTenkeyless(Boolean tenkeyless) {
        this.tenkeyless = tenkeyless;
    }

    public String getConnection_type() {
        return connection_type;
    }

    public void setConnection_type(String connection_type) {
        this.connection_type = connection_type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    @Override
    public String toString() {
        return "Keyboard{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", style='" + style + '\'' +
                ", switches='" + switches + '\'' +
                ", backlit='" + backlit + '\'' +
                ", tenkeyless=" + tenkeyless +
                ", connection_type='" + connection_type + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
