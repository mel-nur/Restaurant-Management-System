package com.melnur.AdisyonTakipSistemi.service;

import com.melnur.AdisyonTakipSistemi.entity.OrderEntity;

public interface OrderService {

    OrderEntity openOrder(Long tableId);

    void addItemOrder(Long orderId, Long productId, int quantity);

    void closeOrder(Long orderId, Long tableId);
}
