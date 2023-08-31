package com.example.projectbase.repository;


import com.example.projectbase.domain.entity.CategoryEntity;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query("SELECT p FROM CategoryEntity p WHERE p.name LIKE %?1%")
    List<CategoryEntity> searchByName(String name, Pageable pageable);

    @Query("SELECT p FROM CategoryEntity p WHERE p.name LIKE %?1%")
    List<CategoryEntity> findByCategoryName(String name);
}
