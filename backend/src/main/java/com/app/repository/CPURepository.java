package com.app.repository;

import com.app.entity.model.CPU;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CPURepository extends JpaRepository<CPU, UUID> {
}