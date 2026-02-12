package com.melnur.AdisyonTakipSistemi.service.impl;

import com.melnur.AdisyonTakipSistemi.dto.request.payment.PaymentCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.payment.PaymentResponse;
import com.melnur.AdisyonTakipSistemi.entity.OrderEntity;
import com.melnur.AdisyonTakipSistemi.entity.PaymentEntity;
import com.melnur.AdisyonTakipSistemi.enums.OrderStatus;
import com.melnur.AdisyonTakipSistemi.enums.PaymentStatus;
import com.melnur.AdisyonTakipSistemi.exception.BusinessException;
import com.melnur.AdisyonTakipSistemi.exception.NotFoundException;
import com.melnur.AdisyonTakipSistemi.mapper.PaymentMapper;
import com.melnur.AdisyonTakipSistemi.repository.IOrderRepository;
import com.melnur.AdisyonTakipSistemi.repository.IPaymentRepository;
import com.melnur.AdisyonTakipSistemi.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final IPaymentRepository paymentRepository;
    private final IOrderRepository orderRepository;
    private final PaymentMapper paymentMapper;

    @Transactional
    @Override
    public PaymentResponse makePayment(PaymentCreateRequest request) {
        OrderEntity order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Sipariş bulunamadı"));

        if(order.getOrderStatus() == OrderStatus.PAID){
            throw new RuntimeException("Sipariş zaten ödenmiş");
        }

        if(order.getOrderStatus() == OrderStatus.PAID){
            throw new RuntimeException("Sipariş zaten ödenmiş");
        }

        if(order.getOrderStatus() == OrderStatus.CANCELLED){
            throw new BusinessException("Sipariş iptal edilmiş");
        }

        BigDecimal totalPaid = paymentRepository
                .findByOrderId(order.getId())
                .stream()
                .map(PaymentEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal remaningAmount = order.getTotalPrice().subtract(totalPaid);

        if(request.getAmount().compareTo(remaningAmount) > 0){
            throw new BusinessException("Ödenecek tutar kalan tutardan fazla olamaz");
        }

        PaymentEntity payment = new PaymentEntity();
        payment.setOrder(order);
        payment.setAmount(request.getAmount());
        payment.setPaymentType(request.getPaymentType());
        payment.setPaymentStatus(PaymentStatus.SUCCESS);

        PaymentEntity saved = paymentRepository.save(payment);

        if(request.getAmount().compareTo(remaningAmount) == 0){
            order.setOrderStatus(OrderStatus.PAID);
        }
        return paymentMapper.toResponse(saved);
    }

    @Override
    public List<PaymentResponse> getPaymentsByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId)
                .stream()
                .map(paymentMapper::toResponse)
                .toList();
    }

    @Override
    public PaymentResponse getPaymentById(Long id) {
        PaymentEntity payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ödeme bulunamadı"));
        return paymentMapper.toResponse(payment);
    }

    @Override
    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(paymentMapper::toResponse)
                .toList();
    }

    @Transactional
    @Override
    public void deletePayment(Long id){
        PaymentEntity payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ödeme bulunamadı"));
        paymentRepository.delete(payment);
    }
}

