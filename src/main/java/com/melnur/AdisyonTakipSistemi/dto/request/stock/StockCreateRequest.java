package com.melnur.AdisyonTakipSistemi.dto.request.stock;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StockCreateRequest {

    @NotNull(message = "Product ID boş olamaz")
    private Long productId;

    @Min(value = 0, message = "Stok miktarı negatif olamaz")
    private Integer quantity;

    @Min(value = 0, message = "Critical level negatif olamaz")
    private Integer criticalLevel;
}
