package com.melnur.AdisyonTakipSistemi.service;

import com.melnur.AdisyonTakipSistemi.entity.ExpenseEntity;
import com.melnur.AdisyonTakipSistemi.entity.ExpenseItemEntity;
import com.melnur.AdisyonTakipSistemi.entity.ProductEntity;
import com.melnur.AdisyonTakipSistemi.repository.ExpenseItemRepository;
import com.melnur.AdisyonTakipSistemi.repository.ExpenseRepository;
import com.melnur.AdisyonTakipSistemi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseItemRepository expenseItemRepository;
    private final ProductRepository productRepository;
    private final StockService stockService;

    public ExpenseEntity createExpense(String description) {
        ExpenseEntity expense = new ExpenseEntity();
        expense.setDescription(description);
        expense.setAmount(BigDecimal.ZERO);
        return expenseRepository.save(expense);
    }

    public void addExpenseItem(Long expenseId, Long productId, int quantity, BigDecimal unitCost){
        ExpenseEntity expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Gider bulunamadı"));

        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı"));

        ExpenseItemEntity item = new ExpenseItemEntity();
        item.setQuantity(quantity);
        item.setExpense(expense);
        item.setProduct(product);
        item.setUnitCost(unitCost);
        expenseItemRepository.save(item);

        stockService.increaseStock(productId, quantity);

        BigDecimal itemTotal = unitCost.multiply(BigDecimal.valueOf(quantity));
        expense.setAmount(expense.getAmount().add(itemTotal));
        expenseRepository.save(expense);
    }

}
