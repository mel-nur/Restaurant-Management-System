package com.melnur.AdisyonTakipSistemi.service;

import com.melnur.AdisyonTakipSistemi.dto.request.payment.PaymentCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.payment.PaymentResponse;

import java.util.List;

public interface PaymentService {
    PaymentResponse makePayment(PaymentCreateRequest request);

    List<PaymentResponse> getPaymentsByOrderId(Long orderId);

    PaymentResponse getPaymentById(Long id);

    List<PaymentResponse> getAllPayments();

    void deletePayment(Long id);
}
