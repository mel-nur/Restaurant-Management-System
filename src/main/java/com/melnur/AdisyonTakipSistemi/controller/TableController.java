package com.melnur.AdisyonTakipSistemi.controller;

import com.melnur.AdisyonTakipSistemi.entity.TableEntity;
import com.melnur.AdisyonTakipSistemi.service.TableService;
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

    private final TableService tableService;

    @PostMapping
    @Operation(summary = "Yeni masa oluştur")
    public ResponseEntity<TableEntity> createTable(@RequestParam int tableNumber) {
        return ResponseEntity.ok(tableService.createTable(tableNumber));
    }

    @GetMapping
    @Operation(summary = "Tüm masaları getir")
    public ResponseEntity<List<TableEntity>> getAllTables() {
        return ResponseEntity.ok(tableService.getAllTables());
    }

    @GetMapping("/{id}")
    @Operation(summary = "ID ile masa getir")
    public ResponseEntity<TableEntity> getTableById(@PathVariable Long id) {
        return ResponseEntity.ok(tableService.getById(id));
    }

    @GetMapping("/number/{tableNumber}")
    @Operation(summary = "Masa numarasına göre getir")
    public ResponseEntity<TableEntity> getByTableNumber(@PathVariable int tableNumber) {
        return ResponseEntity.ok(tableService.getByTableNumber(tableNumber));
    }

    @GetMapping("/open")
    @Operation(summary = "Açık masaları listele")
    public ResponseEntity<List<TableEntity>> getOpenTables() {
        return ResponseEntity.ok(tableService.getOpenTables());
    }

    @GetMapping("/closed")
    @Operation(summary = "Kapalı masaları listele")
    public ResponseEntity<List<TableEntity>> getClosedTables() {
        return ResponseEntity.ok(tableService.getClosedTables());
    }

    @PutMapping("/{id}/open")
    @Operation(summary = "Masayı aç")
    public ResponseEntity<Void> openTable(@PathVariable Long id) {
        tableService.openTable(id);
        return ResponseEntity.ok().build();
    } 

    @PutMapping("/{id}/close")
    @Operation(summary = "Masayı kapat")
    public ResponseEntity<Void> closeTable(@PathVariable Long id) {
        tableService.closeTable(id);
        return ResponseEntity.ok().build();
    }
}
