package com.melnur.AdisyonTakipSistemi.service;

import com.melnur.AdisyonTakipSistemi.dto.request.expense.ExpenseCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.expense.ExpenseResponse;
import com.melnur.AdisyonTakipSistemi.entity.ExpenseEntity;

import java.math.BigDecimal;
import java.util.List;

public interface ExpenseService {

    ExpenseResponse createExpense(ExpenseCreateRequest request);

    ExpenseResponse getExpenseById(Long id);

    List<ExpenseResponse> getAllExpenses();

    ExpenseResponse updateExpense(Long id, ExpenseCreateRequest request);

    void deleteExpense(Long id);


}
