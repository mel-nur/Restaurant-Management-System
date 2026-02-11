package com.melnur.AdisyonTakipSistemi.dto.request.order;

import com.melnur.AdisyonTakipSistemi.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderUpdateStatusRequest {

    @NotNull(message = "Order status boş olamaz")
    private OrderStatus orderStatus;
}
