package com.melnur.AdisyonTakipSistemi.service.impl;

import com.melnur.AdisyonTakipSistemi.dto.request.category.CategoryCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.category.CategoryResponse;
import com.melnur.AdisyonTakipSistemi.entity.CategoryEntity;
import com.melnur.AdisyonTakipSistemi.mapper.CategoryMapper;
import com.melnur.AdisyonTakipSistemi.repository.ICategoryRepository;
import com.melnur.AdisyonTakipSistemi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final ICategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse getCatgoryByName(String name) {
        CategoryEntity category = categoryRepository.findByName(name);
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    @Override
    public CategoryResponse createCategory(CategoryCreateRequest request) {
        CategoryEntity category = categoryMapper.toEntity(request);
        CategoryEntity savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(savedCategory);
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryCreateRequest request) {
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı: " + id));
        category.setName(request.getName());
        CategoryEntity updatedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        CategoryEntity entity = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı: " + id));
        categoryRepository.delete(entity);
    }

    public List<CategoryResponse> getAllCategories(){
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
    }
}
