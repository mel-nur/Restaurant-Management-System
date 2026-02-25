package com.melnur.AdisyonTakipSistemi.dto.request.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateRequest {
    private String name;

    private Long categoryId;

    @Positive(message = "Satış fiyatı 0'dan büyük olmalıdır")
    private BigDecimal salePrice;

    @Positive(message = "Maliyet fiyatı 0'dan büyük olmalıdır")
    private BigDecimal costPrice;

    @NotNull(message = "Stok takibi boş olamaz")
    private Boolean trackStock;
}
