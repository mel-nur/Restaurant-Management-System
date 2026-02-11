package com.melnur.AdisyonTakipSistemi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "expense_items")
public class ExpenseItemEntity extends BaseEntity{

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expense_id", nullable = false)
    private ExpenseEntity expense;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @NotNull
    @Positive
    private int quantity;

    @NotNull
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal unitCost;

    //private BigDecimal totalCost;

    public BigDecimal calculateTotal() {
        return unitCost.multiply(BigDecimal.valueOf(quantity));
    }

}
