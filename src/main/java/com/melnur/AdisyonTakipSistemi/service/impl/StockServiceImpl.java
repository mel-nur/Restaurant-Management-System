package com.melnur.AdisyonTakipSistemi.service.impl;

import com.melnur.AdisyonTakipSistemi.entity.StockEntity;
import com.melnur.AdisyonTakipSistemi.exception.BusinessException;
import com.melnur.AdisyonTakipSistemi.exception.NotFoundException;
import com.melnur.AdisyonTakipSistemi.repository.IStockRepository;
import com.melnur.AdisyonTakipSistemi.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final IStockRepository _IStockRepository;

    public StockEntity createStock(StockEntity stock){
        return _IStockRepository.save(stock);
    }

    //Stock miktarını azaltan metot
    public void decreaseStock(Long productId, int quantity){
        StockEntity stock = _IStockRepository.findByProductId(productId).
                orElseThrow(() -> new NotFoundException("Stok bulunamadı"));

        if (stock.getQuantity() < quantity){
            throw new BusinessException("Yetersiz stok");
        }

        stock.setQuantity(stock.getQuantity() - quantity);
        _IStockRepository.save(stock);
    }

    //Stock miktarını artıran metot
    public void increaseStock(Long productId, int quantity){
        StockEntity stock = _IStockRepository.findByProductId(productId).
                orElseThrow(() -> new NotFoundException("Stok bulunamadı"));
        stock.setQuantity(stock.getQuantity() + quantity);
        _IStockRepository.save(stock);
    }


}
