package com.melnur.AdisyonTakipSistemi.controller;

import com.melnur.AdisyonTakipSistemi.entity.OrderEntity;
import com.melnur.AdisyonTakipSistemi.service.impl.OrderServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.ToStringSummary;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/orders")
@Tag(name = "Orders", description = "Adisyon yönetimi işlemleri")
public class OrderController {
    private final OrderServiceImpl orderServiceImpl;

    // Adisyon aç (Belirli bir masa için)
    @PostMapping("/open/{tableId}")
    @Operation(summary = "Adisyon aç")
    public OrderEntity openOrder(@PathVariable Long tableId){
        return orderServiceImpl.openOrder(tableId);
    }

    // Adisyona ürün ekle
    @PostMapping("/{orderId}/add-item")
    @Operation(summary = "Adisyona ürün ekle")
    public void addItem(@PathVariable Long orderId,
                        @RequestParam Long productId,
                        @RequestParam int quantity){
        orderServiceImpl.addItemOrder(orderId, productId, quantity);
    }

    // Adisyonu kapat (Hem adisyon ID hem masa ID gönderiyoruz)
    @PostMapping("/{orderId}/close/{tableId}")
    @Operation(summary = "Adisyonu kapat")
    public void closeOrder(@PathVariable Long orderId, @PathVariable Long tableId){
        orderServiceImpl.closeOrder(orderId, tableId);
    }
}