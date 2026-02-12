package com.melnur.AdisyonTakipSistemi.service;

import com.melnur.AdisyonTakipSistemi.dto.request.order.OrderCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.order.OrderResponse;
import com.melnur.AdisyonTakipSistemi.entity.OrderEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(OrderCreateRequest request);

    void cancelOrder(Long orderId);

    void pay(Long orderId);

    OrderResponse getOrderById(Long id);

    OrderResponse updateOrderStatus(Long id, String status);

    void deleteOrder(Long id);

    List<OrderResponse> getAllOrders();
}
