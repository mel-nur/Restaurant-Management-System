package com.melnur.AdisyonTakipSistemi.mapper;

import com.melnur.AdisyonTakipSistemi.dto.request.table.TableCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.table.TableResponse;
import com.melnur.AdisyonTakipSistemi.entity.TableEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TableMapper {
    TableEntity toEntity(TableCreateRequest request);
    TableResponse toResponse(TableEntity entity);
}
