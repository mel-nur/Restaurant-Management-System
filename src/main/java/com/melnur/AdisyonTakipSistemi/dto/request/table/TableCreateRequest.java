package com.melnur.AdisyonTakipSistemi.dto.request.table;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TableCreateRequest {

    @NotNull(message = "Masa numarası boş olamaz")
    @Min(value = 1, message = "Masa numarası 1'den küçük olamaz")
    private Integer tableNumber;
}
