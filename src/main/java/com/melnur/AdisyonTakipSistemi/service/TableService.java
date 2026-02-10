package com.melnur.AdisyonTakipSistemi.service;

import com.melnur.AdisyonTakipSistemi.entity.TableEntity;
import com.melnur.AdisyonTakipSistemi.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TableService {
    private final TableRepository tableRepository;

    public TableEntity createTable(int tableNumber) {
        if (tableRepository.existsByTableNumber(tableNumber)) {
            throw new RuntimeException("Bu masa numarası zaten mevcut");
        }
        TableEntity table = new TableEntity();
        table.setTableNumber(tableNumber);
        table.setTableStatus(TableEntity.TableStatus.OPEN);

        return tableRepository.save(table);
    }

    public List<TableEntity> getAllTables() {
        return tableRepository.findAll();
    }


    public TableEntity getById(Long id) {
        return tableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Masa bulunamadı"));
    }

    public TableEntity getByTableNumber(int tableNumber) {
        return tableRepository.findByTableNumber(tableNumber)
                .orElseThrow(() -> new RuntimeException("Masa bulunamadı"));
    }

    public List<TableEntity> getOpenTables() {
        return tableRepository.findByTableStatus(TableEntity.TableStatus.OPEN);
    }

    public List<TableEntity> getClosedTables() {
        return tableRepository.findByTableStatus(TableEntity.TableStatus.CLOSED);
    }

    public void openTable(Long tableId) {
        TableEntity table = getById(tableId);
        table.setTableStatus(TableEntity.TableStatus.OPEN);
        tableRepository.save(table);
    }

    public void closeTable(Long tableId) {
        TableEntity table = getById(tableId);
        table.setTableStatus(TableEntity.TableStatus.CLOSED);
        tableRepository.save(table);
    }

    public void updateTableStatus(Long tableId, TableEntity.TableStatus status) {
        TableEntity table = getById(tableId);
        table.setTableStatus(status);
        tableRepository.save(table);
    }
}


