package com.melnur.AdisyonTakipSistemi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.melnur.AdisyonTakipSistemi.enums.ExpenseCategory;
import com.melnur.AdisyonTakipSistemi.enums.ExpenseType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "expenses")
public class ExpenseEntity extends BaseEntity{

    @NotBlank
    private String description;

    @NotNull
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount = BigDecimal.ZERO;

    private LocalDate expenseDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ExpenseCategory expenseCategory;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;

    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExpenseItemEntity> items = new ArrayList<>();

    public void addItem(ExpenseItemEntity item){
        items.add(item);
        item.setExpense(this);
        addAmount(item.calculateTotal());
    }

    public void addAmount(BigDecimal value){
        if(amount == null)
            amount = BigDecimal.ZERO;
        amount = amount.add(value);
    }

}
