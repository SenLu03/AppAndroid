package com.example.projectbase.repository;

import com.example.projectbase.domain.dto.response.ComboResponseDTO;
import com.example.projectbase.domain.entity.ComboEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComboRepository extends JpaRepository<ComboEntity,Long> {
    List<ComboEntity> findComboEntityByCategoryEntity_Id(Long category_id);
}
