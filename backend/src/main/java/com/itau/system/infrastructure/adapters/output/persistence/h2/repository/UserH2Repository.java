package com.itau.system.infrastructure.adapters.output.persistence.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itau.system.infrastructure.adapters.output.persistence.h2.entity.UserH2Entity;

@Repository
public interface UserH2Repository extends JpaRepository<UserH2Entity, Long>{
    
}
