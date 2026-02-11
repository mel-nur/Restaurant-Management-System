package com.melnur.AdisyonTakipSistemi.dto.response.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderItemResponse {

    private Long id;

    private Long productId;
    private String productName;

    @NotNull
    @PositiveOrZero
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}
