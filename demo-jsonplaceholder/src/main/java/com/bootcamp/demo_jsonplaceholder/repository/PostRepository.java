package com.bootcamp.demo_jsonplaceholder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.demo_jsonplaceholder.entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long>{

    
    
}
