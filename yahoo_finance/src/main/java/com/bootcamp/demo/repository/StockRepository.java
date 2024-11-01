package com.bootcamp.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.demo.entity.StockEntity;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {
  Optional<StockEntity> findBySymbol(String symbol);
}