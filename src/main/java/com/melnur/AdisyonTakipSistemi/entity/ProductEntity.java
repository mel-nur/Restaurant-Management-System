package com.melnur.AdisyonTakipSistemi.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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

    private String name;

    private String category;

    private BigDecimal salePrice;

    private BigDecimal costPrice;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private StockEntity stock;
}
