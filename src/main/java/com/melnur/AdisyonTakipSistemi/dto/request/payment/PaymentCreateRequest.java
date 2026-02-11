package com.melnur.AdisyonTakipSistemi.dto.request.payment;

import com.melnur.AdisyonTakipSistemi.enums.PaymentType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentCreateRequest {

    @NotNull(message = "Order ID boş olamaz")
    private Long orderId;

    @NotNull(message = "Payment type boş olamaz")
    private PaymentType paymentType;

    @NotNull(message = "Amount boş olamaz")
    @Positive(message = "Amount 0 veya daha büyük olmalıdır")
    private BigDecimal amount;;
}
