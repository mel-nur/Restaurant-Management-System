package com.melnur.AdisyonTakipSistemi.mapper;

import com.melnur.AdisyonTakipSistemi.dto.request.orderitem.OrderItemCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.order.OrderItemResponse;
import com.melnur.AdisyonTakipSistemi.entity.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "product", ignore = true)
    OrderItemEntity toEntity(OrderItemCreateRequest request);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    OrderItemResponse toResponse(OrderItemEntity entity);
}
