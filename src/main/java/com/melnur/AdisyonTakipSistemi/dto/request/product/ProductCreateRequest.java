package com.melnur.AdisyonTakipSistemi.dto.request.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductCreateRequest {
    @NotBlank(message = "Ürün adı boş olamaz")
    private String name;

    @NotBlank(message = "Kategori boş olamaz")
    private String category;

    @NotNull(message = "Satış fiyatı boş olamaz")
    @Positive(message = "Satış fiyatı 0'dan büyük olmalıdır")
    private BigDecimal salePrice;

    @NotNull(message = "Maliyet fiyatı boş olamaz")
    @Positive(message = "Maliyet fiyatı 0'dan büyük olmalıdır")
    private BigDecimal costPrice;
}
