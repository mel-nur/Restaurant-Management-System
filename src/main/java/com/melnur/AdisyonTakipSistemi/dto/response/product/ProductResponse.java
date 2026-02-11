package com.melnur.AdisyonTakipSistemi.dto.response.product;

import com.melnur.AdisyonTakipSistemi.dto.response.stock.StockResponse;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ProductResponse {

    private Long id;

    private String name;

    private String category;

    private BigDecimal salePrice;

    private BigDecimal costPrice;

    private StockResponse stock;

    private LocalDateTime createdDate;
}
