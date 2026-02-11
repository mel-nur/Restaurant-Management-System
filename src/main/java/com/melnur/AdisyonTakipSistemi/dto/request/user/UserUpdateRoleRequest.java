package com.melnur.AdisyonTakipSistemi.dto.request.user;

import com.melnur.AdisyonTakipSistemi.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserUpdateRoleRequest {
    @NotNull(message = "UserRole boş bırakılamaz.")
    private UserRole userRole;
}
