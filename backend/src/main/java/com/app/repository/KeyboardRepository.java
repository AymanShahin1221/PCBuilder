package com.app.repository;

import com.app.entity.model.Keyboard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KeyboardRepository extends JpaRepository<Keyboard, UUID> {
}