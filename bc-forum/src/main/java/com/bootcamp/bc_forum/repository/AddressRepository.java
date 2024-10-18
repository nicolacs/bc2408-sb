package com.bootcamp.bc_forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.bc_forum.entity.AddressEntity;

@Repository
public interface AddressRepository extends JpaRepository <AddressEntity, Long>{

    
}