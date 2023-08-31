package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query("SELECT p FROM ProductEntity p WHERE p.categoryEntity.id = :categoryId")
    List<ProductEntity> findByCategoryId(@Param("categoryId") Long categoryId);
    @Query(value = "select p.* FROM products p inner join combo_item cb ON p.id=cb.product_id where combo_id=?1 &&category_id=?2 ",nativeQuery = true)
    List<ProductEntity> findByCombo(Long comboId,Long categoryId);
    @Query(value = "select p.* FROM products p where p.point != 0",nativeQuery = true)
    List<ProductEntity> findProductHavePoint();
}
