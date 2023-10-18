package com.itau.system.infrastructure.adapters.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itau.system.infrastructure.adapters.output.persistence.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
    
}
