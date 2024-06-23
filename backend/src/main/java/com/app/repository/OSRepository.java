package com.app.repository;

import com.app.entity.model.OS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OSRepository extends JpaRepository<OS, UUID> {
}