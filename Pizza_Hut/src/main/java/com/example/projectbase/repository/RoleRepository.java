package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

  @Query("SELECT r FROM RoleEntity r WHERE r.id = ?1")
  Optional<RoleEntity> findById(Long id);

  @Query("SELECT r FROM RoleEntity r WHERE r.name = ?1")
  RoleEntity findByRoleName(String roleName);

}
