package com.app.repository;

import com.app.entity.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StorageRepository extends JpaRepository<Storage, UUID> {
}