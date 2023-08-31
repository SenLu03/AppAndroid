package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductDetailRepository extends JpaRepository<ProductDetailEntity,Long> {
    Optional<ProductDetailEntity> findProductDetailEntitiesByProductEntityAndSizeEntityAndCakeBaseEntityAndCartEntity(ProductEntity productEntity, SizeEntity sizeEntity, CakeBaseEntity cakeBaseEntity, CartEntity cartEntity);
}
