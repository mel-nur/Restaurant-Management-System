package com.melnur.AdisyonTakipSistemi.service.impl;

import com.melnur.AdisyonTakipSistemi.entity.ExpenseEntity;
import com.melnur.AdisyonTakipSistemi.entity.ExpenseItemEntity;
import com.melnur.AdisyonTakipSistemi.entity.ProductEntity;
import com.melnur.AdisyonTakipSistemi.exception.NotFoundException;
import com.melnur.AdisyonTakipSistemi.repository.IExpenseItemRepository;
import com.melnur.AdisyonTakipSistemi.repository.IExpenseRepository;
import com.melnur.AdisyonTakipSistemi.repository.IProductRepository;
import com.melnur.AdisyonTakipSistemi.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final IExpenseRepository _IExpenseRepository;
    private final IExpenseItemRepository _IExpenseItemRepository;
    private final IProductRepository _IProductRepository;
    private final StockServiceImpl stockServiceImpl;

    @Transactional
    @Override
    public ExpenseEntity createExpense(String description) {
        ExpenseEntity expense = new ExpenseEntity();
        expense.setDescription(description);
        expense.setAmount(BigDecimal.ZERO);
        return _IExpenseRepository.save(expense);
    }

    @Transactional
    @Override
    public void addExpenseItem(Long expenseId, Long productId, int quantity, BigDecimal unitCost){
        ExpenseEntity expense = _IExpenseRepository.findById(expenseId)
                .orElseThrow(() -> new NotFoundException("Gider bulunamadı"));

        ProductEntity product = _IProductRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Ürün bulunamadı"));

        ExpenseItemEntity item = new ExpenseItemEntity();
        item.setQuantity(quantity);
        item.setExpense(expense);
        item.setProduct(product);
        item.setUnitCost(unitCost);
        _IExpenseItemRepository.save(item);

        stockServiceImpl.increaseStock(productId, quantity);

        BigDecimal currentAmount = Optional.ofNullable(expense.getAmount())
                .orElse(BigDecimal.ZERO);

        expense.setAmount(currentAmount.add(unitCost.multiply(BigDecimal.valueOf(quantity))));

        _IExpenseRepository.save(expense);
    }

}
