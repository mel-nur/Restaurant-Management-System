package com.melnur.AdisyonTakipSistemi.dto.response.category;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CategoryResponse {
    private Long id;
    private String name;
}
