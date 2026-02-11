package com.melnur.AdisyonTakipSistemi.dto.request.user;

import com.melnur.AdisyonTakipSistemi.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateRequest {
    @NotBlank(message = "Username boş olamaz")
    private String username;

    @NotBlank(message = "Password boş olamaz")
    @Size(min = 6, message = "Password en az 6 karakter olmalıdır")
    private String password;

    @NotNull(message = "Role boş olamaz")
    private UserRole role;
}
