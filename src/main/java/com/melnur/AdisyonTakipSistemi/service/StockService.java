package com.melnur.AdisyonTakipSistemi.service;

import com.melnur.AdisyonTakipSistemi.entity.StockEntity;
import com.melnur.AdisyonTakipSistemi.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    public StockEntity createStock(StockEntity stock){
        return stockRepository.save(stock);
    }

    //Stock miktarını azaltan metot
    public void decreaseStock(Long productId, int quantity){
        StockEntity stock = stockRepository.findByProductId(productId).
                orElseThrow(() -> new RuntimeException("Stok bulunamadı"));

        if (stock.getQuantity() < quantity){
            throw new RuntimeException("Yetersiz stok");
        }

        stock.setQuantity(stock.getQuantity() - quantity);
        stockRepository.save(stock);
    }

    //Stock miktarını artıran metot
    public void increaseStock(Long productId, int quantity){
        StockEntity stock = stockRepository.findByProductId(productId).
                orElseThrow(() -> new RuntimeException("Stok bulunamadı"));
        stock.setQuantity(stock.getQuantity() + quantity);
        stockRepository.save(stock);
    }


}
