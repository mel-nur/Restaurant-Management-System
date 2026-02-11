package com.melnur.AdisyonTakipSistemi.repository;

import com.melnur.AdisyonTakipSistemi.entity.ProductEntity;
import com.melnur.AdisyonTakipSistemi.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IStockRepository extends JpaRepository<StockEntity, Long> {

    Optional<StockEntity> findByProduct(ProductEntity product);

    Optional<StockEntity> findByProductId(Long productId);

    @Query("SELECT s FROM StockEntity s WHERE s.quantity <= s.criticalLevel")
    List<StockEntity> findCriticalStocks();
}
