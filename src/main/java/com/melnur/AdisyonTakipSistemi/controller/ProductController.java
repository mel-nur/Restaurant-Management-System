package com.melnur.AdisyonTakipSistemi.controller;

import com.melnur.AdisyonTakipSistemi.entity.ProductEntity;
import com.melnur.AdisyonTakipSistemi.service.impl.ProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Ürün yönetimi işlemleri")
public class ProductController {
    private final ProductServiceImpl productServiceImpl;

    // Ürün ekle
    @PostMapping
    @Operation(summary = "Yeni ürün ekle")
    public ProductEntity addProduct(@RequestBody ProductEntity product){
        return productServiceImpl.addProduct(product);
    }

    // Tüm ürünleri listele
    @GetMapping("/listele")
    @Operation(summary = "Tüm ürünleri listele")
    public List<ProductEntity> getAllProducts() {
        return productServiceImpl.getAllProducts();
    }

}

