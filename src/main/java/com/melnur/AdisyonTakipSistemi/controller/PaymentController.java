package com.melnur.AdisyonTakipSistemi.controller;

import com.melnur.AdisyonTakipSistemi.dto.request.payment.PaymentCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.payment.PaymentResponse;
import com.melnur.AdisyonTakipSistemi.service.PaymentService;
import com.melnur.AdisyonTakipSistemi.service.impl.PaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentServiceImpl paymentService;

    // 💳 Ödeme oluştur
    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(
            @RequestBody PaymentCreateRequest request) {

        return ResponseEntity.ok(paymentService.makePayment(request));
    }

    // 🔎 ID ile ödeme getir
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPaymentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    // 📋 Tüm ödemeleri getir
    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAllPayments() {

        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    // 🗑️ Ödeme sil
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(
            @PathVariable Long id) {

        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<PaymentResponse>> getPaymentsByOrder(
            @PathVariable Long orderId) {

        return ResponseEntity.ok(paymentService.getPaymentsByOrderId(orderId));
    }

}

