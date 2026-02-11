package com.melnur.AdisyonTakipSistemi.dto.request.stock;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class StockUpdateRequest {

    @Min(value = 0, message = "Stok miktarı negatif olamaz")
    private Integer quantity;

    @Min(value = 0, message = "Critical level negatif olamaz")
    private Integer criticalLevel;
}
