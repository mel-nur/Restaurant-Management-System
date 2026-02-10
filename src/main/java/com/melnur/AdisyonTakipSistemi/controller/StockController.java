package com.melnur.AdisyonTakipSistemi.controller;

import com.melnur.AdisyonTakipSistemi.entity.StockEntity;
import com.melnur.AdisyonTakipSistemi.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stocks")
public class StockController {
    private final StockService stockService;

    //Stock oluştur
    @PostMapping("/create")
    public StockEntity createStock(@RequestBody StockEntity stock){
        return stockService.createStock(stock);
    }
}




