package com.melnur.AdisyonTakipSistemi.service;

import com.melnur.AdisyonTakipSistemi.dto.response.stock.StockResponse;
import com.melnur.AdisyonTakipSistemi.entity.StockEntity;

import java.util.List;

public interface StockService {

    StockResponse getStockByProductId(Long productId);
    List<StockResponse> getAllStocks();
    StockEntity createStock(StockEntity stock);
    StockResponse updateCriticalLevel(Long productId, Integer criticalLevel);
    List<StockResponse> getCriticalStocks();
    StockResponse decreaseStock(Long productId, Integer quantity);
    StockResponse increaseStock(Long productId, Integer quantity);
}
