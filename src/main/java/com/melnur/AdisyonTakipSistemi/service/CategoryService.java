package com.melnur.AdisyonTakipSistemi.service;

import com.melnur.AdisyonTakipSistemi.dto.request.category.CategoryCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.category.CategoryResponse;

public interface CategoryService {
    CategoryResponse getCatgoryByName(String name);
    CategoryResponse createCategory(CategoryCreateRequest name);
    CategoryResponse updateCategory(Long id, CategoryCreateRequest name);
    void deleteCategory(Long id);
}
