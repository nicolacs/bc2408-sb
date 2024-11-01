package com.bootcamp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.demo.entity.StockPriceEntity;


@Repository
public interface StockPriceRepository
    extends JpaRepository<StockPriceEntity, Long> {

}