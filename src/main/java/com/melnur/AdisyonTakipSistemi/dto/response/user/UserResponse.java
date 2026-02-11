package com.melnur.AdisyonTakipSistemi.dto.response.user;

import com.melnur.AdisyonTakipSistemi.enums.UserRole;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponse {
    private Long id;

    private String username;

    private UserRole role;

    private LocalDateTime createdDate;
}
