package com.jawwad.integrationtest.repository;

import com.jawwad.integrationtest.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {

    Optional<StudentEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
