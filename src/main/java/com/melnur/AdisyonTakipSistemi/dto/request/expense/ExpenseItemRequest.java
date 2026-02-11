package com.melnur.AdisyonTakipSistemi.dto.request.expense;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ExpenseItemRequest {

    @NotNull(message = "Product ID boş olamaz")
    private Long productId;

    @NotNull(message = "Quantity boş olamaz")
    @Positive(message = "Quantity 0'dan büyük olmalıdır")
    private Integer quantity;

    @NotNull(message = "Unit cost boş olamaz")
    @Positive(message = "Unit cost 0'dan büyük olmalıdır")
    private java.math.BigDecimal unitCost;
}
