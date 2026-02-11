package com.melnur.AdisyonTakipSistemi.service;

import com.melnur.AdisyonTakipSistemi.entity.ProductEntity;

import java.util.List;

public interface ProductService {

    ProductEntity addProduct(ProductEntity product);

    List<ProductEntity> getAllProducts();
}
