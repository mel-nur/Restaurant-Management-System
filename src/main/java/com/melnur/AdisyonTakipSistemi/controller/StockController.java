package com.melnur.AdisyonTakipSistemi.controller;

import com.melnur.AdisyonTakipSistemi.dto.response.stock.StockResponse;
import com.melnur.AdisyonTakipSistemi.entity.StockEntity;
import com.melnur.AdisyonTakipSistemi.service.impl.StockServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stocks")
@Tag(name = "Stocks", description = "Stok yönetim işlemleri için API uç noktaları")
public class StockController {
    private final StockServiceImpl stockServiceImpl;

    @GetMapping
    public ResponseEntity<List<StockResponse>> getAllStocks(){

        return ResponseEntity.ok(stockServiceImpl.getAllStocks());
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<StockResponse> getStockByProductId(@PathVariable Long productId){
        return ResponseEntity.ok(stockServiceImpl.getStockByProductId(productId));
    }

    @PutMapping("/increase/{productId}")
    public ResponseEntity<StockResponse> increaseStock(
            @PathVariable Long productId,
            @RequestParam Integer quantity) {

        return ResponseEntity.ok(
                stockServiceImpl.increaseStock(productId, quantity)
        );
    }

    @PutMapping("/decrease/{productId}")
    public ResponseEntity<StockResponse> decreaseStock(
            @PathVariable Long productId,
            @RequestParam Integer quantity) {
        return ResponseEntity.ok(stockServiceImpl.decreaseStock(productId,quantity));
    }

    @PutMapping("/critical/{productId}")
    public ResponseEntity<StockResponse> updateCriticalLevel(
            @PathVariable Long productId,
            @RequestParam Integer criticalLevel) {

        return ResponseEntity.ok(
                stockServiceImpl.updateCriticalLevel(productId, criticalLevel)
        );

    }


    /*
    //Stock oluştur
    @PostMapping("/create")
    @Operation(summary = "Yeni stok oluştur")
    public StockEntity createStock(@RequestBody StockEntity stock){
        return stockServiceImpl.createStock(stock);
    }
     */
}




