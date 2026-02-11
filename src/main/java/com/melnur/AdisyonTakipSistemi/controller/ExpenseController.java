package com.melnur.AdisyonTakipSistemi.controller;

import com.melnur.AdisyonTakipSistemi.entity.ExpenseEntity;
import com.melnur.AdisyonTakipSistemi.service.impl.ExpenseServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expenses")
@Tag(name = "Expense", description = "Gider işlemleri için API uç noktaları")

public class ExpenseController {
    private final ExpenseServiceImpl expenseServiceImpl;

    // Gider oluştur
    @PostMapping
    @Operation(summary = "Gider oluştur")
    public ExpenseEntity createExpense(@RequestParam String description) {

        return expenseServiceImpl.createExpense(description);
    }

    // Gider kalemi ekle
    @PostMapping("/{expenseId}/add-item")
    @Operation(summary = "Gider kalemi ekle")
    public void addExpenseItem(@PathVariable Long expenseId,
                               @RequestParam Long productId,
                               @RequestParam int quantity,
                               @RequestParam BigDecimal unitCost){
        expenseServiceImpl.addExpenseItem(expenseId,productId,quantity,unitCost);

    }
}

