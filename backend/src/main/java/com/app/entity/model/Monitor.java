package com.app.entity.model;

import com.app.service.db.DBUpdateService;
import jakarta.persistence.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Entity
@Table
public class Monitor extends PCPart implements Upsertable {

    @Id
    private UUID pid;

    @Column(unique = true)
    private String name;

    private Double price;
    private Double screen_size;
    private List<Integer> resolution;
    private Integer refresh_rate;
    private Double response_time;
    private String panel_type;
    private String aspect_ratio;
    private String imageLocation;

    public Monitor() {  }

    public Monitor(UUID pid, String name,
                   Double price, Double screen_size,
                   List<Integer> resolution, Integer refresh_rate,
                   Double response_time, String panel_type,
                   String aspect_ratio) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.screen_size = screen_size;
        this.resolution = resolution;
        this.refresh_rate = refresh_rate;
        this.response_time = response_time;
        this.panel_type = panel_type;
        this.aspect_ratio = aspect_ratio;
    }

    public Monitor(String name, Double price,
                   Double screen_size, List<Integer> resolution,
                   Integer refresh_rate, Double response_time,
                   String panel_type, String aspect_ratio) {
        this.name = name;
        this.price = price;
        this.screen_size = screen_size;
        this.resolution = resolution;
        this.refresh_rate = refresh_rate;
        this.response_time = response_time;
        this.panel_type = panel_type;
        this.aspect_ratio = aspect_ratio;
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

    public Double getScreen_size() {
        return screen_size;
    }

    public void setScreen_size(Double screen_size) {
        this.screen_size = screen_size;
    }

    public List<Integer> getResolution() {
        return resolution;
    }

    public void setResolution(List<Integer> resolution) {
        this.resolution = resolution;
    }

    public Integer getRefresh_rate() {
        return refresh_rate;
    }

    public void setRefresh_rate(Integer refresh_rate) {
        this.refresh_rate = refresh_rate;
    }

    public Double getResponse_time() {
        return response_time;
    }

    public void setResponse_time(Double response_time) {
        this.response_time = response_time;
    }

    public String getPanel_type() {
        return panel_type;
    }

    public void setPanel_type(String panel_type) {
        this.panel_type = panel_type;
    }

    public String getAspect_ratio() {
        return aspect_ratio;
    }

    public void setAspect_ratio(String aspect_ratio) {
        this.aspect_ratio = aspect_ratio;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", screen_size=" + screen_size +
                ", resolution=" + resolution +
                ", refresh_rate=" + refresh_rate +
                ", response_time=" + response_time +
                ", panel_type='" + panel_type + '\'' +
                ", aspect_ratio='" + aspect_ratio + '\'' +
                '}';
    }

    @Override
    public void insertPart(DBUpdateService dbUpdateService) throws SQLException {
        dbUpdateService.upsertMonitorTable(this);
    }
}
