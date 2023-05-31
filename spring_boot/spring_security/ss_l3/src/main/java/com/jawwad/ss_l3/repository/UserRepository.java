package com.jawwad.ss_l3.repository;

import com.jawwad.ss_l3.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsernameAndEnabled(String userName, int enabled);

    void deleteByUsername(String userName);
}
