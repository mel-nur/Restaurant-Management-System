package com.melnur.AdisyonTakipSistemi.mapper;

import com.melnur.AdisyonTakipSistemi.dto.request.order.OrderCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.order.OrderResponse;
import com.melnur.AdisyonTakipSistemi.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "table", ignore = true)
    @Mapping(target = "user", ignore = true)
    OrderEntity toEntity(OrderCreateRequest request);

    @Mapping(source = "table.id", target = "tableId")
    OrderResponse toResponse(OrderEntity order);
}
