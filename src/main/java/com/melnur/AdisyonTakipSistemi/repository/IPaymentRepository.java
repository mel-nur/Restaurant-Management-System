package com.melnur.AdisyonTakipSistemi.repository;

import com.melnur.AdisyonTakipSistemi.entity.PaymentEntity;
import com.melnur.AdisyonTakipSistemi.enums.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPaymentRepository extends JpaRepository<PaymentEntity, Long> {

    List<PaymentEntity> findByOrderId(Long orderId);
    List<PaymentEntity> findByPaymentType(PaymentType paymentType);
}
