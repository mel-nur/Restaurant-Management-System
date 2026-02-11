package com.melnur.AdisyonTakipSistemi.repository;

import com.melnur.AdisyonTakipSistemi.entity.ExpenseEntity;
import com.melnur.AdisyonTakipSistemi.enums.ExpenseCategory;
import com.melnur.AdisyonTakipSistemi.enums.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

    List<ExpenseEntity> findByExpenseCategory(ExpenseCategory expenseCategory);

    List<ExpenseEntity> findByExpenseType(ExpenseType expenseType);

    @Query("SELECT COALESCE(SUM(e.amount),0) FROM ExpenseEntity e")
    BigDecimal getTotalExpense();
}

