package com.melnur.AdisyonTakipSistemi.controller;

import com.melnur.AdisyonTakipSistemi.dto.request.table.TableCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.table.TableResponse;
import com.melnur.AdisyonTakipSistemi.entity.TableEntity;
import com.melnur.AdisyonTakipSistemi.enums.TableStatus;
import com.melnur.AdisyonTakipSistemi.service.impl.TableServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
@RequiredArgsConstructor
@Tag(name = "Tables", description = "Masa yönetimi işlemleri")
public class TableController {

    private final TableServiceImpl tableService;

    @PostMapping
    public ResponseEntity<TableResponse> createTable(
            @RequestBody TableCreateRequest request) {

        return ResponseEntity.ok(tableService.createTable(request));
    }

    // 📋 Tüm masaları getir
    @GetMapping
    public ResponseEntity<List<TableResponse>> getAllTables() {
        return ResponseEntity.ok(tableService.getAllTables());
    }

    // 🔎 ID ile masa getir
    @GetMapping("/{id}")
    public ResponseEntity<TableResponse> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(tableService.getById(id));
    }

    // 🔎 Masa numarasına göre getir
    @GetMapping("/number/{tableNumber}")
    public ResponseEntity<TableResponse> getByTableNumber(
            @PathVariable int tableNumber) {

        return ResponseEntity.ok(tableService.getByTableNumber(tableNumber));
    }

    // 🟢 Açık masalar
    @GetMapping("/open")
    public ResponseEntity<List<TableResponse>> getOpenTables() {
        return ResponseEntity.ok(tableService.getOpenTables());
    }

    // 🔴 Kapalı masalar
    @GetMapping("/closed")
    public ResponseEntity<List<TableResponse>> getClosedTables() {
        return ResponseEntity.ok(tableService.getClosedTables());
    }

    // 🟢 Masa aç
    @PatchMapping("/{id}/open")
    public ResponseEntity<Void> openTable(
            @PathVariable Long id) {

        tableService.openTable(id);
        return ResponseEntity.ok().build();
    }

    // 🔴 Masa kapat
    @PatchMapping("/{id}/close")
    public ResponseEntity<Void> closeTable(
            @PathVariable Long id) {

        tableService.closeTable(id);
        return ResponseEntity.ok().build();
    }

    // 🔄 Masa status güncelle
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateTableStatus(
            @PathVariable Long id,
            @RequestParam TableStatus status) {

        tableService.updateTableStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/active-count")
    public ResponseEntity<Long> getActiveTableCount(){
        return ResponseEntity.ok(tableService.getActiveTableCount());
    }
    /*
    @PostMapping
    @Operation(summary = "Yeni masa oluştur")
    public ResponseEntity<TableEntity> createTable(@RequestParam int tableNumber) {
        return ResponseEntity.ok(tableServiceImpl.createTable(tableNumber));
    }

    @GetMapping
    @Operation(summary = "Tüm masaları getir")
    public ResponseEntity<List<TableEntity>> getAllTables() {
        return ResponseEntity.ok(tableServiceImpl.getAllTables());
    }

    @GetMapping("/{id}")
    @Operation(summary = "ID ile masa getir")
    public ResponseEntity<TableEntity> getTableById(@PathVariable Long id) {
        return ResponseEntity.ok(tableServiceImpl.getById(id));
    }

    @GetMapping("/number/{tableNumber}")
    @Operation(summary = "Masa numarasına göre getir")
    public ResponseEntity<TableEntity> getByTableNumber(@PathVariable int tableNumber) {
        return ResponseEntity.ok(tableServiceImpl.getByTableNumber(tableNumber));
    }

    @GetMapping("/open")
    @Operation(summary = "Açık masaları listele")
    public ResponseEntity<List<TableEntity>> getOpenTables() {
        return ResponseEntity.ok(tableServiceImpl.getOpenTables());
    }

    @GetMapping("/closed")
    @Operation(summary = "Kapalı masaları listele")
    public ResponseEntity<List<TableEntity>> getClosedTables() {
        return ResponseEntity.ok(tableServiceImpl.getClosedTables());
    }

    @PutMapping("/{id}/open")
    @Operation(summary = "Masayı aç")
    public ResponseEntity<Void> openTable(@PathVariable Long id) {
        tableServiceImpl.openTable(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/close")
    @Operation(summary = "Masayı kapat")
    public ResponseEntity<Void> closeTable(@PathVariable Long id) {
        tableServiceImpl.closeTable(id);
        return ResponseEntity.ok().build();
    }
     */
}
