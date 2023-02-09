package com.jawwwad.ss_l2.repositories;

import com.jawwwad.ss_l2.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUserName(String username);
    boolean existsByUserName(String username);
}
