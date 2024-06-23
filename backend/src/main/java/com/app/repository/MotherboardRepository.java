package com.app.repository;

import com.app.entity.model.Motherboard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MotherboardRepository extends JpaRepository<Motherboard, UUID> {
}