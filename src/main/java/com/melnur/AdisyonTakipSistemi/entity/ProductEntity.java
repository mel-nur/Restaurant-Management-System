package com.melnur.AdisyonTakipSistemi.entity;

import com.melnur.AdisyonTakipSistemi.enums.StockTypeStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "products")
public class ProductEntity extends BaseEntity{

    @NotBlank
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull
    private CategoryEntity category;

    @NotNull
    private BigDecimal salePrice;

    @NotNull
    private BigDecimal costPrice;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private StockEntity stock;

    @Enumerated(EnumType.STRING)
    private StockTypeStatus stockTypeStatus;
}
