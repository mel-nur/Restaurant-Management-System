package com.melnur.AdisyonTakipSistemi.service.impl;

import com.melnur.AdisyonTakipSistemi.dto.request.expense.ExpenseCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.expense.ExpenseResponse;
import com.melnur.AdisyonTakipSistemi.entity.ExpenseEntity;
import com.melnur.AdisyonTakipSistemi.entity.ExpenseItemEntity;
import com.melnur.AdisyonTakipSistemi.entity.ProductEntity;
import com.melnur.AdisyonTakipSistemi.exception.NotFoundException;
import com.melnur.AdisyonTakipSistemi.mapper.ExpenseMapper;
import com.melnur.AdisyonTakipSistemi.repository.IExpenseItemRepository;
import com.melnur.AdisyonTakipSistemi.repository.IExpenseRepository;
import com.melnur.AdisyonTakipSistemi.repository.IProductRepository;
import com.melnur.AdisyonTakipSistemi.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final IExpenseRepository _IExpenseRepository;
    private final IExpenseItemRepository _IExpenseItemRepository;
    private final IProductRepository _IProductRepository;
    private final StockServiceImpl stockServiceImpl;
    private final ExpenseMapper expenseMapper;

    @Transactional
    @Override
    public ExpenseResponse createExpense(ExpenseCreateRequest request) {
        ExpenseEntity entity = expenseMapper.toEntity(request);
        ExpenseEntity saved = _IExpenseRepository.save(entity);
        return expenseMapper.toResponse(saved);

    }

    @Override
    public ExpenseResponse getExpenseById(Long id) {
        ExpenseEntity expense = _IExpenseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Gider bulunamadı"));
        return expenseMapper.toResponse(expense);
    }

    @Override
    public List<ExpenseResponse> getAllExpenses() {
        return _IExpenseRepository.findAll()
                .stream()
                .map(expenseMapper::toResponse)
                .collect(toList());
    }

    @Transactional
    @Override
    public ExpenseResponse updateExpense(Long id, ExpenseCreateRequest request) {
        ExpenseEntity entity = _IExpenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        entity.setAmount(request.getAmount());
        entity.setDescription(request.getDescription());
        entity.setExpenseType(request.getExpenseType());

        ExpenseEntity updated = _IExpenseRepository.save(entity);

        return expenseMapper.toResponse(updated);    }

    @Transactional
    @Override
    public void deleteExpense(Long id) {
        ExpenseEntity entity = _IExpenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        _IExpenseRepository.delete(entity);

    }


    /*
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
     */

}
