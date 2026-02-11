package com.melnur.AdisyonTakipSistemi.service;

import com.melnur.AdisyonTakipSistemi.entity.ExpenseEntity;

import java.math.BigDecimal;

public interface ExpenseService {

    ExpenseEntity createExpense(String description);

    void addExpenseItem(Long expenseId, Long productId, int quantity, BigDecimal unitCost);


}
