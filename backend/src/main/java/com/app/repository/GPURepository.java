package com.app.repository;

import com.app.entity.model.GPU;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GPURepository extends JpaRepository<GPU, UUID> {
}