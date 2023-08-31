package com.example.projectbase.repository;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.domain.dto.common.UserDetailImp;
import com.example.projectbase.domain.entity.UserEntity;
import com.example.projectbase.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

  @Query("SELECT u FROM UserEntity u WHERE u.id = ?1")
  Optional<UserEntity> findById(String id);

  @Query("SELECT u FROM UserEntity u WHERE u.username = ?1")
  Optional<UserEntity> findByUsername(String username);

  @Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
  UserEntity findByEmail(String email);

  @Query("SELECT u FROM UserEntity u WHERE u.phoneNumber = ?1")
  UserEntity findByPhone(String phone);

  default UserEntity getUser(UserDetailImp currentUser) {
    return findByUsername(currentUser.getUsername())
        .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_USERNAME,
            new String[]{currentUser.getUsername()}));
  }

}
