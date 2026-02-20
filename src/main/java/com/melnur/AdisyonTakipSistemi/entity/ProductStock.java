package com.melnur.AdisyonTakipSistemi.entity;

import jakarta.persistence.Column;

public class ProductStock {

    @Column(name = "product_id")
    private ProductEntity product;
    @Column(name = "stock")
    private StockEntity stock;
}
