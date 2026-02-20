package com.melnur.AdisyonTakipSistemi.mapper;

import com.melnur.AdisyonTakipSistemi.dto.request.product.ProductCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.product.ProductResponse;
import com.melnur.AdisyonTakipSistemi.entity.CategoryEntity;
import com.melnur.AdisyonTakipSistemi.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category", target = "category", qualifiedByName = "stringToCategoryEntity")
    ProductEntity toEntity(ProductCreateRequest request);

    @Mapping(source = "category.name", target = "category")
    ProductResponse toResponse(ProductEntity entity);

    @Named("stringToCategoryEntity")
    default CategoryEntity stringToCategoryEntity(String categoryName) {
        if (categoryName == null || categoryName.isEmpty()) {
            return null;
        }
        CategoryEntity category = new CategoryEntity();
        category.setName(categoryName);
        return category;
    }
}
