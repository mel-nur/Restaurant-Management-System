package com.melnur.AdisyonTakipSistemi.dto.request.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderCreateRequest {

    @NotNull(message = "Masa ID'si boş bırakılamaz.")
    private Long tableId;

    private Long userId;

    @NotNull(message = "Sipariş kalemleri boş bırakılamaz.")
    @Valid
    private List<OrderItemRequest> items;
}
