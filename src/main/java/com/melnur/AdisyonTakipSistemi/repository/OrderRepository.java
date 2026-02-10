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

    Optional<OrderEntity> findByTableIdAndOrderStatus(Long tableId, OrderEntity.OrderStatus orderStatus);

    List<OrderEntity> findByOrderStatus(OrderEntity.OrderStatus orderStatus);

    @Query("""
    SELECT COALESCE(SUM(o.totalPrice), 0)
    FROM OrderEntity o
    WHERE o.orderStatus = com.melnur.AdisyonTakipSistemi.entity.OrderEntity$OrderStatus.PAID
""")
    BigDecimal getTotalRevenue();

}
