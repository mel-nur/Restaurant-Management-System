package com.melnur.AdisyonTakipSistemi.mapper;

import com.melnur.AdisyonTakipSistemi.dto.request.expense.ExpenseCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.expense.ExpenseItemResponse;
import com.melnur.AdisyonTakipSistemi.dto.response.expense.ExpenseResponse;
import com.melnur.AdisyonTakipSistemi.entity.ExpenseEntity;
import com.melnur.AdisyonTakipSistemi.entity.ExpenseItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
    ExpenseEntity toEntity(ExpenseCreateRequest request);
    ExpenseResponse toResponse(ExpenseEntity entity);
    ExpenseItemResponse toItemResponse(ExpenseItemEntity item);
}
