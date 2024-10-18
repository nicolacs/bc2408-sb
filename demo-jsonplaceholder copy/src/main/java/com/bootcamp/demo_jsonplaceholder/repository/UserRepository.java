package com.bootcamp.demo_jsonplaceholder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.demo_jsonplaceholder.entity.UserEntity;
import java.util.List;
import java.util.Optional;


//!!! Hibernate 2重點
// Hibernate (many PL to many DB products), JPA (java to many DB products)
  // 1. During compile time, the implementation class will be generated for this interface
    // i.e. insert, update, delete, select, etc.
  // 2. Auto-integrate with the diff. product of Database driver (maven dependency)
    // i.e. PostgreSQL we have "distinct on"
    // 負責gen死物 
  // 3. JPA is java layer for developer to interact with Database/SQL/Tables
@Repository // One of the component for Component Scan
            // Extend左入面成套方法
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  // 1. save(): UserEntity & saveAll()  <包括INSERT & UPDATE 都係用黎個>
  // 2. findAll
  // 3. deleteById
  
  //!!! JPA (可以用JAVA method name to generate code to interact with DB by Hibernate)
  // 有好多method 例如findBy<以上幾個1-3>, 已經做到唔同function
  // JPA 用habinate 同DATA溝通到
  // 打黎個: findByWebsite , 已經出到下面, 可自己改type: Optional(冇多個1個一樣既OBJ)
  Optional<UserEntity> findByWebsite(String website);
  Optional<UserEntity> findByWebsiteAndPhone(String website, String phone);
}

// ! Concept of Hibernate:
  // 1. Hibernate generate ABC.class, which is implementing interface UserRepository
  // ABC.class implement UserRepository
    

