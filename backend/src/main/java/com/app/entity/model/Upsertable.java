package com.app.entity.model;

import com.app.service.db.DBUpdateService;

import java.sql.SQLException;

public interface Upsertable {
    void insertPart(DBUpdateService dbUpdateService) throws SQLException;
}