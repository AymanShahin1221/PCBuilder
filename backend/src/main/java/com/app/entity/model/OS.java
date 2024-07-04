package com.app.entity.model;

import com.app.service.db.DBUpdateService;
import jakarta.persistence.*;

import java.sql.SQLException;
import java.util.UUID;

@Entity
@Table
public class OS extends PCPart implements Upsertable {

    @Id
    private UUID pid;

    @Column(unique = true)
    private String name;

    private Double price;
    private Integer max_memory;
    private String imageLocation;

    public OS() {  }

    public OS(UUID pid, String name,
              Double price,
              Integer max_memory) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.max_memory = max_memory;
    }

    public OS(String name, Double price, Integer max_memory) {
        this.name = name;
        this.price = price;
        this.max_memory = max_memory;
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

    public Integer getMax_memory() {
        return max_memory;
    }

    public void setMax_memory(Integer max_memory) {
        this.max_memory = max_memory;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    @Override
    public String toString() {
        return "OS{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", max_memory=" + max_memory +
                '}';
    }

    @Override
    public void insertPart(DBUpdateService dbUpdateService) throws SQLException {
        dbUpdateService.upsertOSTable(this);
    }
}
