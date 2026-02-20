package com.melnur.AdisyonTakipSistemi.enums;

public enum TableStatus {
    OPEN("Açık"),
    OCCUPIED("Dolu"),
    AVAILABLE("Boş"),
    CLOSED("Kapatıldı");


    private String description;
    TableStatus(String description) {
        this.description=description;
    }
}
