package com.melnur.AdisyonTakipSistemi.dto.response.stock;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockResponse {

    private Long id;

    private Long productId;

    private String productName;

    private Integer quantity;

    private Integer criticalLevel;

    private Boolean critical;
}
