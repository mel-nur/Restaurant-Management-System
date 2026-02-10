package com.melnur.AdisyonTakipSistemi.controller;

import com.melnur.AdisyonTakipSistemi.entity.ProductEntity;
import com.melnur.AdisyonTakipSistemi.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    // Ürün ekle
    @PostMapping
    public ProductEntity addProduct(@RequestBody ProductEntity product){
        return productService.addProduct(product);
    }

    // Tüm ürünleri listele
    @GetMapping("/listele")
    public List<ProductEntity> getAllProducts() {
        return productService.getAllProducts();
    }

}

