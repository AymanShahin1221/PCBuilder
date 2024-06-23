package com.app.repository;

import com.app.entity.model.Cooler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CoolerRepository extends JpaRepository<Cooler, UUID> {
}