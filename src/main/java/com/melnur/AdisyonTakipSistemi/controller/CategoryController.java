package com.melnur.AdisyonTakipSistemi.controller;

import com.melnur.AdisyonTakipSistemi.dto.request.category.CategoryCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.category.CategoryResponse;
import com.melnur.AdisyonTakipSistemi.service.CategoryService;
import com.melnur.AdisyonTakipSistemi.service.impl.CategoryServiceImpl;
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
@RequestMapping("/api/category")
@Tag(name = "Categories", description = "Kategori yönetimi işlemleri")
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    @GetMapping("/all")
    @Operation(summary = "Tüm kategorileri getir")
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{name}")
    @Operation(summary = "Kategori adına göre getir")
    public ResponseEntity<CategoryResponse> getCategoryByName(@PathVariable String name){
        return ResponseEntity.ok(categoryService.getCatgoryByName(name));
    }

    @PostMapping
    @Operation(summary = "Yeni kategori oluştur")
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryCreateRequest request){
        CategoryResponse response = categoryService.createCategory(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Kategoriyi güncelle")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryCreateRequest request){
        return ResponseEntity.ok(categoryService.updateCategory(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Kategoriyi sil")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
