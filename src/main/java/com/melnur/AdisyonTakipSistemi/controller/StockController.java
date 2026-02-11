package com.melnur.AdisyonTakipSistemi.controller;

import com.melnur.AdisyonTakipSistemi.entity.StockEntity;
import com.melnur.AdisyonTakipSistemi.service.impl.StockServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stocks")
@Tag(name = "Stocks", description = "Stok yönetim işlemleri için API uç noktaları")
public class StockController {
    private final StockServiceImpl stockServiceImpl;

    //Stock oluştur
    @PostMapping("/create")
    @Operation(summary = "Yeni stok oluştur")
    public StockEntity createStock(@RequestBody StockEntity stock){
        return stockServiceImpl.createStock(stock);
    }
}




