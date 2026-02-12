package com.melnur.AdisyonTakipSistemi.service.impl;

import com.melnur.AdisyonTakipSistemi.dto.response.stock.StockResponse;
import com.melnur.AdisyonTakipSistemi.entity.StockEntity;
import com.melnur.AdisyonTakipSistemi.exception.BusinessException;
import com.melnur.AdisyonTakipSistemi.exception.NotFoundException;
import com.melnur.AdisyonTakipSistemi.mapper.StockMapper;
import com.melnur.AdisyonTakipSistemi.repository.IStockRepository;
import com.melnur.AdisyonTakipSistemi.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final IStockRepository _IStockRepository;
    private final StockMapper stockMapper;

    @Override
    public StockResponse getStockByProductId(Long productId) {
        StockEntity stock = _IStockRepository.findByProductId(productId)
                .orElseThrow(() -> new NotFoundException("Stok bulunamadı: "));
        return stockMapper.toResponse(stock);
    }

    @Override
    public List<StockResponse> getAllStocks() {
        return _IStockRepository.findAll()
                .stream()
                .map(stockMapper::toResponse)
                .toList();
    }

    public StockEntity createStock(StockEntity stock){
        return _IStockRepository.save(stock);
    }

    @Override
    @Transactional
    public StockResponse updateCriticalLevel(Long productId, Integer criticalLevel) {
        StockEntity stock = _IStockRepository.findByProductId(productId)
                .orElseThrow(() -> new NotFoundException("Stok bulunamadı"));
        stock.setCriticalLevel(criticalLevel);
        return stockMapper.toResponse(_IStockRepository.save(stock));
    }

    @Override
    public List<StockResponse> getCriticalStocks() {
        return _IStockRepository.findAll()
                .stream()
                .filter(stock -> stock.getQuantity() <= stock.getCriticalLevel())
                .map(stockMapper::toResponse)
                .toList();

    }

    @Transactional
    @Override
    public StockResponse decreaseStock(Long productId, Integer quantity) {
        if (quantity <= 0)
            throw new BusinessException("Azaltma miktarı 0'dan büyük olmalı");

        StockEntity stock = _IStockRepository.findByProductId(productId)
                .orElseThrow(() -> new NotFoundException("Stok bulunamadı"));

        if (stock.getQuantity() < quantity)
            throw new BusinessException("Yetersiz stok");

        stock.setQuantity(stock.getQuantity() - quantity);

        return stockMapper.toResponse(_IStockRepository.save(stock));
    }

    @Transactional
    @Override
    public StockResponse increaseStock(Long productId, Integer quantity) {
        if(quantity < 0){
            throw new BusinessException("Miktar negatif olamaz");
        }

         StockEntity stock = _IStockRepository.findByProductId(productId)
                .orElseThrow(() -> new NotFoundException("Stok bulunamadı: " + productId));
        stock.setQuantity(stock.getQuantity() + quantity);
        return stockMapper.toResponse(_IStockRepository.save(stock));
    }

    /*
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
     */

}
