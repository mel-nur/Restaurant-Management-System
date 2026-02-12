package com.melnur.AdisyonTakipSistemi.controller;

import com.melnur.AdisyonTakipSistemi.dto.request.product.ProductCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.request.product.ProductUpdateRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.product.ProductResponse;
import com.melnur.AdisyonTakipSistemi.entity.ProductEntity;
import com.melnur.AdisyonTakipSistemi.service.impl.ProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ProductResponse> createProduct(
            @Valid @RequestBody ProductCreateRequest request){

        ProductResponse response = productServiceImpl.createProduct(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductUpdateRequest request) {

        return ResponseEntity.ok(productServiceImpl.updateProduct(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable Long id) {

        return ResponseEntity.ok(productServiceImpl.getProductById(id));
    }

    // Tüm ürünleri listele
    @GetMapping("/listele")
    @Operation(summary = "Tüm ürünleri listele")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productServiceImpl.getAllProducts());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponse>> getByCategory(@PathVariable Long categoryId){
        return ResponseEntity.ok(productServiceImpl.getProductsByCategory(categoryId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productServiceImpl.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }



}

