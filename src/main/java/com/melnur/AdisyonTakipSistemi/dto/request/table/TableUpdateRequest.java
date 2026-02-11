package com.melnur.AdisyonTakipSistemi.dto.request.table;

import com.melnur.AdisyonTakipSistemi.enums.TableStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TableUpdateRequest {
    @NotNull(message = "Masa durumu boş olamaz")
    private TableStatus tableStatus;
}
