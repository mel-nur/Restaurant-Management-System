package com.melnur.AdisyonTakipSistemi.entity;

import jakarta.persistence.*;
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

    @Column(unique = true, nullable = false)
    private int tableNumber;

    @Enumerated(EnumType.STRING)
    private TableStatus tableStatus;

    public enum TableStatus{
        OPEN,
        CLOSED
    }
}
