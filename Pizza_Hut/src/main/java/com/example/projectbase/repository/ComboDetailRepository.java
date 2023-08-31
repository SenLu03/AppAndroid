package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.ComboDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComboDetailRepository extends JpaRepository<ComboDetailEntity,Long> {
    @Query(value = "select * from  combo_detail where combo_id=?1",nativeQuery = true)
    public List<ComboDetailEntity> findByComboId(Long id);


}
