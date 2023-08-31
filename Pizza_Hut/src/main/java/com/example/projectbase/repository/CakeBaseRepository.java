package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.CakeBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CakeBaseRepository extends JpaRepository<CakeBaseEntity,Long> {
}
