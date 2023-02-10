package com.jawwad.ss_l3.repository;

import com.jawwad.ss_l3.entity.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserClass,Integer> {
}
