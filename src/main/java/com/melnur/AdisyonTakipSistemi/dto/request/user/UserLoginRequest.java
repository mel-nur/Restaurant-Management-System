package com.melnur.AdisyonTakipSistemi.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginRequest {

    @NotBlank(message = "UserName boş bırakılamaz.")
    private String userName;

    @NotBlank(message = "Password boş bırakılamaz.")
    private String password;
}
