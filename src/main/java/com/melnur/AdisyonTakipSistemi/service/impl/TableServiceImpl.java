package com.melnur.AdisyonTakipSistemi.service.impl;

import com.melnur.AdisyonTakipSistemi.entity.TableEntity;
import com.melnur.AdisyonTakipSistemi.enums.TableStatus;
import com.melnur.AdisyonTakipSistemi.exception.BusinessException;
import com.melnur.AdisyonTakipSistemi.exception.NotFoundException;
import com.melnur.AdisyonTakipSistemi.repository.ITableRepository;
import com.melnur.AdisyonTakipSistemi.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {
    private final ITableRepository _ITableRepository;

    @Override
    public TableEntity createTable(int tableNumber) {
        if (_ITableRepository.existsByTableNumber(tableNumber)) {
            throw new BusinessException("Bu masa numarası zaten mevcut");
        }
        TableEntity table = new TableEntity();
        table.setTableNumber(tableNumber);
        table.setTableStatus(TableStatus.OPEN);

        return _ITableRepository.save(table);
    }

    @Override
    public List<TableEntity> getAllTables() {
        return _ITableRepository.findAll();
    }

    @Override
    public TableEntity getById(Long id) {
        return _ITableRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Masa bulunamadı"));
    }
    @Override
    public TableEntity getByTableNumber(int tableNumber) {
        return _ITableRepository.findByTableNumber(tableNumber)
                .orElseThrow(() -> new NotFoundException("Masa bulunamadı"));
    }

    @Override
    public List<TableEntity> getOpenTables() {
        return _ITableRepository.findByTableStatus(TableStatus.OPEN);
    }

    @Override
    public List<TableEntity> getClosedTables() {
        return _ITableRepository.findByTableStatus(TableStatus.CLOSED);
    }

    @Override
    public void openTable(Long tableId) {
        TableEntity table = getById(tableId);
        table.setTableStatus(TableStatus.OPEN);
        _ITableRepository.save(table);
    }

    @Override
    public void closeTable(Long tableId) {
        TableEntity table = getById(tableId);
        table.setTableStatus(TableStatus.CLOSED);
        _ITableRepository.save(table);
    }

    @Override
    public void updateTableStatus(Long tableId, TableStatus status) {
        TableEntity table = getById(tableId);
        table.setTableStatus(status);
        _ITableRepository.save(table);
    }
}


