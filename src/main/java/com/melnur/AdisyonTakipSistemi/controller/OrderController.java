package com.melnur.AdisyonTakipSistemi.controller;

import com.melnur.AdisyonTakipSistemi.dto.request.order.OrderCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.order.OrderResponse;
import com.melnur.AdisyonTakipSistemi.entity.OrderEntity;
import com.melnur.AdisyonTakipSistemi.service.impl.OrderServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.ToStringSummary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/orders")
@Tag(name = "Orders", description = "Adisyon yönetimi işlemleri")
public class OrderController {
    private final OrderServiceImpl orderServiceImpl;

    @Operation(summary = "Sipariş Oluştur")
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderCreateRequest request){
        OrderResponse response = orderServiceImpl.createOrder(request);
        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }

    @Operation(summary = "Id'ye göre sipariş getir")
    @GetMapping
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long orderId){
        OrderResponse response = orderServiceImpl.getOrderById(orderId);
        return ResponseEntity.ok(orderServiceImpl.getOrderById(orderId));
    }

    @Operation(summary = "Tüm siparişleri getir")
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders(){
        return ResponseEntity.ok(orderServiceImpl.getAllOrders());
    }

    @Operation(summary = "Siparişi iptal et")
    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId){
        orderServiceImpl.cancelOrder(orderId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Siparişi öde")
    @PutMapping("/{id}/pay")
    public ResponseEntity<Void> payOrder(@PathVariable Long orderId){
        orderServiceImpl.pay(orderId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Sipariş durumunu güncelle")
    @PutMapping("/{id}/status")
    public ResponseEntity<OrderResponse> updateStatus(@PathVariable Long id,
                                                      @RequestParam String status){
        return ResponseEntity.ok(orderServiceImpl.updateOrderStatus(id,status));
    }

    @Operation(summary = "Siparişi sil")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(
            @PathVariable Long id) {

        orderServiceImpl.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }










    /*
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

     */
}