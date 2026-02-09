package com.melnur.AdisyonTakipSistemi.repository;

import com.melnur.AdisyonTakipSistemi.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

    List<ExpenseEntity> findByCategory(ExpenseEntity.ExpenseCategory category);

    List<ExpenseEntity> findByExpenseType(ExpenseEntity.ExpenseType expenseType);

    @Query("SELECT SUM(e.amount) FROM ExpenseEntity e")
    BigDecimal getTotalExpense();
}

