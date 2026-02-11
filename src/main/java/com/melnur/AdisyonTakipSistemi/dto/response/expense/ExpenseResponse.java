package com.melnur.AdisyonTakipSistemi.dto.response.expense;

import com.melnur.AdisyonTakipSistemi.enums.ExpenseCategory;
import com.melnur.AdisyonTakipSistemi.enums.ExpenseType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
public class ExpenseResponse {

    private Long id;
    private String description;
    private BigDecimal amount;;
    private LocalDate expenseDate;
    private ExpenseCategory expenseCategory;
    private ExpenseType expenseType;
    private List<ExpenseItemResponse> items;
    private LocalDateTime createdDate;
}
