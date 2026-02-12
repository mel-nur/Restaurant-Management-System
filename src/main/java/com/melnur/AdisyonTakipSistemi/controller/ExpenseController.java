package com.melnur.AdisyonTakipSistemi.controller;

import com.melnur.AdisyonTakipSistemi.dto.request.expense.ExpenseCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.expense.ExpenseResponse;
import com.melnur.AdisyonTakipSistemi.entity.ExpenseEntity;
import com.melnur.AdisyonTakipSistemi.service.impl.ExpenseServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expenses")
@Tag(name = "Expense", description = "Gider işlemleri için API uç noktaları")

public class ExpenseController {
    private final ExpenseServiceImpl expenseService;


    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(
            @RequestBody ExpenseCreateRequest request) {

        return ResponseEntity.ok(expenseService.createExpense(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponse> getExpenseById(
            @PathVariable Long id) {

        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> getAllExpenses() {

        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponse> updateExpense(
            @PathVariable Long id,
            @RequestBody ExpenseCreateRequest request) {

        return ResponseEntity.ok(expenseService.updateExpense(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(
            @PathVariable Long id) {

        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }


    /*
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
     */
}

