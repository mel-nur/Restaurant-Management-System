package com.melnur.AdisyonTakipSistemi.dto.response.table;

import com.melnur.AdisyonTakipSistemi.enums.TableStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TableResponse {
    private Long id;

    private Integer tableNumber;

    private TableStatus tableStatus;

    private LocalDateTime createdDate;
}
