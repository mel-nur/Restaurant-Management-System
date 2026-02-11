package com.melnur.AdisyonTakipSistemi.service;

import com.melnur.AdisyonTakipSistemi.entity.StockEntity;

public interface StockService {

    StockEntity createStock(StockEntity stock);
    void decreaseStock(Long productId, int quantity);
    void increaseStock(Long productId, int quantity);
}
