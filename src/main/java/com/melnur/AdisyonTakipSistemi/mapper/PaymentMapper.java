package com.melnur.AdisyonTakipSistemi.mapper;

import com.melnur.AdisyonTakipSistemi.dto.request.payment.PaymentCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.payment.PaymentResponse;
import com.melnur.AdisyonTakipSistemi.entity.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "order", ignore = true)
    PaymentEntity toEntity(PaymentCreateRequest request);

    @Mapping(source = "order.id", target = "orderId")
    PaymentResponse toResponse(PaymentEntity paymentEntity);
}
