package com.app.repository;

import com.app.entity.model.PowerSupply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PowerSupplyRepository extends JpaRepository<PowerSupply, UUID> {
}