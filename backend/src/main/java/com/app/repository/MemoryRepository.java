package com.app.repository;

import com.app.entity.model.Memory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemoryRepository extends JpaRepository<Memory, UUID> {
}