package com.melnur.AdisyonTakipSistemi.dto.request.expense;

import com.melnur.AdisyonTakipSistemi.enums.ExpenseCategory;
import com.melnur.AdisyonTakipSistemi.enums.ExpenseType;
import lombok.Data;

import java.time.LocalDate;
import java.util.Locale;

@Data
public class ExpenseUpdateRequest {

    private String description;
    private LocalDate expenseDate;

    private ExpenseCategory expenseCategory;

    private ExpenseType expenseType;
}
