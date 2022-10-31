package com.jawwad.spring_boot_async.repository;

import com.jawwad.spring_boot_async.entity.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GuardianRepository extends JpaRepository<Guardian, UUID> {
    List<Guardian> findByIdIn(List<UUID> ids);
}
