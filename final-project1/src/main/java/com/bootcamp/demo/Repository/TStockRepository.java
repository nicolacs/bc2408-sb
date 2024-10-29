package com.bootcamp.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.demo.entity.StockEntity;

@Repository
public interface TStockRepository extends JpaRepository <StockEntity, Long> {
    
}
