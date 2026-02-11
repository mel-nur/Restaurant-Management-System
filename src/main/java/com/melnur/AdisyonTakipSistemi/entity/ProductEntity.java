package com.melnur.AdisyonTakipSistemi.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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

    @NotBlank
    private String category;

    @NotNull
    private BigDecimal salePrice;

    @NotNull
    private BigDecimal costPrice;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private StockEntity stock;
}
