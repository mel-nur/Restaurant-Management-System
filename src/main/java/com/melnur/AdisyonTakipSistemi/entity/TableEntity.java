package com.melnur.AdisyonTakipSistemi.entity;

import com.melnur.AdisyonTakipSistemi.enums.TableStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tables")
public class TableEntity extends BaseEntity{

    @NotNull
    @Column(unique = true, nullable = false)
    private int tableNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TableStatus tableStatus = TableStatus.valueOf("CLOSED");

}
