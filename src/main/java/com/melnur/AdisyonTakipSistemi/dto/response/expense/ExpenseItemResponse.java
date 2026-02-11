package com.melnur.AdisyonTakipSistemi.dto.response.expense;

import java.math.BigDecimal;

public class ExpenseItemResponse {
    private Long id;

    private Long productId;
    private String productName;

    private Integer quantity;
    private BigDecimal unitCost;
    private BigDecimal totalCost;
}
