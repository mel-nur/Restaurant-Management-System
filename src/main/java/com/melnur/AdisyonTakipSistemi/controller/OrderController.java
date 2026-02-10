package com.melnur.AdisyonTakipSistemi.controller;

import com.melnur.AdisyonTakipSistemi.entity.OrderEntity;
import com.melnur.AdisyonTakipSistemi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/orders")
public class OrderController {
    private final OrderService orderService;

    // Adisyon aç (Belirli bir masa için)
    @PostMapping("/open/{tableId}")
    public OrderEntity openOrder(@PathVariable Long tableId){
        return orderService.openOrder(tableId);
    }

    // Adisyona ürün ekle
    @PostMapping("/{orderId}/add-item")
    public void addItem(@PathVariable Long orderId,
                        @RequestParam Long productId,
                        @RequestParam int quantity){
        orderService.addItemOrder(orderId, productId, quantity);
    }

    // Adisyonu kapat (Hem adisyon ID hem masa ID gönderiyoruz)
    @PostMapping("/{orderId}/close/{tableId}")
    public void closeOrder(@PathVariable Long orderId, @PathVariable Long tableId){
        orderService.closeOrder(orderId, tableId);
    }
}