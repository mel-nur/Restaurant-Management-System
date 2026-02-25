package com.melnur.AdisyonTakipSistemi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "stocks")
public class StockEntity extends  BaseEntity{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @NotNull(message = "Stok miktarı boş olamaz")
    @Min(value = 0, message = "Stok miktarı negatif olamaz")
    private int quantity;

    @NotNull(message = "Critical level boş olamaz")
    @Min(value = 0, message = "Critical level negatif olamaz")
    private int criticalLevel;

    public boolean isCritical() {
        return quantity <= criticalLevel;
    }

}
