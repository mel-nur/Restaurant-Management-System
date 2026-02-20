package com.melnur.AdisyonTakipSistemi.mapper;

import com.melnur.AdisyonTakipSistemi.dto.request.stock.StockCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.stock.StockResponse;
import com.melnur.AdisyonTakipSistemi.entity.StockEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StockMapper {
    @Mapping(target="product", ignore = true)
    StockEntity toEntity(StockCreateRequest request);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(target = "critical", source = "critical")
    StockResponse toResponse(StockEntity stockEntity);
}
