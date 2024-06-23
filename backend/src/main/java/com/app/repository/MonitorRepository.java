package com.app.repository;

import com.app.entity.model.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MonitorRepository extends JpaRepository<Monitor, UUID> {
}