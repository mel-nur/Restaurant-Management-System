package com.melnur.AdisyonTakipSistemi.mapper;

import com.melnur.AdisyonTakipSistemi.dto.request.product.ProductCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.product.ProductResponse;
import com.melnur.AdisyonTakipSistemi.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductEntity toEntity(ProductCreateRequest request);
    ProductResponse toResponse(ProductEntity entity);
}
