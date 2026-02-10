package com.melnur.AdisyonTakipSistemi.controller;

import com.melnur.AdisyonTakipSistemi.entity.ExpenseEntity;
import com.melnur.AdisyonTakipSistemi.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    // Gider oluştur
    @PostMapping
    public ExpenseEntity createExpense(@RequestParam String description) {

        return expenseService.createExpense(description);
    }

    // Gider kalemi ekle
    @PostMapping("/{expenseId}/add-item")
    public void addExpenseItem(@PathVariable Long expenseId,
                               @RequestParam Long productId,
                               @RequestParam int quantity,
                               @RequestParam BigDecimal unitCost){
        expenseService.addExpenseItem(expenseId,productId,quantity,unitCost);

    }
}

