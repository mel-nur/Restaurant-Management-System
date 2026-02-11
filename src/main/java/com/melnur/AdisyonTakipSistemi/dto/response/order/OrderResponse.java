package com.melnur.AdisyonTakipSistemi.dto.response.order;

import com.melnur.AdisyonTakipSistemi.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderResponse {

    private Long id;

    private Long tableId;
    private String tableName;

    private OrderStatus orderStatus;

    private BigDecimal totalPrice;

    private List<OrderItemResponse> items;

    private LocalDateTime createdDate;
}
