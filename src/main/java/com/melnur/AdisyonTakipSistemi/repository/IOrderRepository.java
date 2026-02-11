package com.melnur.AdisyonTakipSistemi.repository;

import com.melnur.AdisyonTakipSistemi.entity.OrderEntity;
import com.melnur.AdisyonTakipSistemi.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findByTableIdAndOrderStatus(Long tableId, OrderStatus orderStatus);

    List<OrderEntity> findByOrderStatus(OrderStatus orderStatus);

    @Query("""
    SELECT COALESCE(SUM(o.totalPrice), 0)
    FROM OrderEntity o
    WHERE o.orderStatus = com.melnur.AdisyonTakipSistemi.enums.OrderStatus.PAID
""")
    BigDecimal getTotalRevenue();

}
