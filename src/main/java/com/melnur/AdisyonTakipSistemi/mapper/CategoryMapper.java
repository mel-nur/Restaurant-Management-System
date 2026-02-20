package com.melnur.AdisyonTakipSistemi.mapper;

import com.melnur.AdisyonTakipSistemi.dto.request.category.CategoryCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.category.CategoryResponse;
import com.melnur.AdisyonTakipSistemi.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    public CategoryEntity toEntity(CategoryCreateRequest request);
    public CategoryResponse toResponse(CategoryEntity entity);
}
