package com.melnur.AdisyonTakipSistemi.mapper;

import com.melnur.AdisyonTakipSistemi.dto.request.user.UserCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.user.UserResponse;
import com.melnur.AdisyonTakipSistemi.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(UserCreateRequest request);
    UserResponse toResponse(UserEntity entity);
}
