package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.OrderEntity;
import com.example.projectbase.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
    List<OrderEntity> findAllByUserEntity(UserEntity userEntity);
}
