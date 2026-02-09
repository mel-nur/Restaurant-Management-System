package com.melnur.AdisyonTakipSistemi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "expenses")
public class ExpenseEntity extends BaseEntity{

    private String description;

    private BigDecimal amount;

    private LocalDate expenseDate;

    @Enumerated(EnumType.STRING)
    private ExpenseCategory expenseCategory;

    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;

    public enum ExpenseCategory {
        MEAT,
        VEGETABLE,
        DRINK,
        CLEANING,
        RENT,
        ELECTRICITY,
        WATER,
        STAFF,
        ACCOUNTING,
        FUEL,
        VEHICLE,
        WOOD,
        OTHER
    }

    public enum ExpenseType {
        STOCK,   // Et, sebze, içecek
        FIXED    // Kira, elektrik, personel
    }

}
