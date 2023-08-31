package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.ProductDetailEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductSizeRepository extends JpaRepository<ProductDetailEntity, Long> {

    @Query("SELECT p.price FROM ProductDetailEntity p WHERE p.productEntity.id = ?1 AND p.sizeEntity.id = ?2")
    Long findPriceByProductIdAndSizeId(Long productId,Long sizeId);
}
