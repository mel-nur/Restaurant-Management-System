package com.melnur.AdisyonTakipSistemi.dto.request.orderitem;

import lombok.Data;
import lombok.Getter;

@Data
public class OrderItemCreateRequest {
    private Long productId;
    private Integer quantity;
}
