package com.melnur.AdisyonTakipSistemi.dto.request.expense;

import com.melnur.AdisyonTakipSistemi.enums.ExpenseCategory;
import com.melnur.AdisyonTakipSistemi.enums.ExpenseType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ExpenseCreateRequest {

    @NotBlank
    private String description;

    private LocalDate expenseDate;

    @NotNull
    private ExpenseCategory expenseCategory;

    @NotNull
    private ExpenseType expenseType;

    @Valid
    private List<ExpenseItemRequest> items;
}
