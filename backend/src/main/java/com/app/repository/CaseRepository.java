package com.app.repository;

import com.app.entity.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CaseRepository extends JpaRepository<Case, UUID> {
}