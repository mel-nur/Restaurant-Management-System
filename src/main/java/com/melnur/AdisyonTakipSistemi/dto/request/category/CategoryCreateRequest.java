package com.melnur.AdisyonTakipSistemi.dto.request.category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryCreateRequest {
    private String name;
}
