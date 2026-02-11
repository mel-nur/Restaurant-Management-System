package com.melnur.AdisyonTakipSistemi.dto.response.payment;

import com.melnur.AdisyonTakipSistemi.enums.PaymentType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PaymentResponse {

    private Long id;

    private Long orderId;

    private PaymentType paymentType;

    private BigDecimal amount;

    private LocalDateTime createdDate;
}
