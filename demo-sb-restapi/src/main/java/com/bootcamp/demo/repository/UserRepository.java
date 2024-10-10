package com.bootcamp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bootcamp.demo.entity.UserEntity;

// Hibernate, JPA
    //!!! Hibernate 2重點
    // 1.
    // 2.Auto-integrate with the diff. product of Database driver(maven dependency)
        // 負責gen死物 

    public interface UserRepository extends JpaRepository<UserEntity, Long>{
    
}
