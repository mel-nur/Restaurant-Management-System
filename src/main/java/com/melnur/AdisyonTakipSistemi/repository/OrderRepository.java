package com.melnur.AdisyonTakipSistemi.repository;

import com.melnur.AdisyonTakipSistemi.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findByTableIdAndStatus(Long tableId, OrderEntity.OrderStatus status);

    List<OrderEntity> findByStatus(String status);

    @Query("SELECT SUM(o.totalPrice) FROM OrderEntity o WHERE o.status = 'PAID'")
    BigDecimal getTotalRevenue();
}
