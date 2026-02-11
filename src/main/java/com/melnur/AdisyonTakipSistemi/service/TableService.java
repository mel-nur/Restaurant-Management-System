package com.melnur.AdisyonTakipSistemi.service;

import com.melnur.AdisyonTakipSistemi.entity.TableEntity;
import com.melnur.AdisyonTakipSistemi.enums.TableStatus;

import java.util.List;

public interface TableService {

    TableEntity createTable(int tableNumber);
    List<TableEntity> getAllTables();
    TableEntity getById(Long id);
    TableEntity getByTableNumber(int tableNumber);
    List<TableEntity> getOpenTables();
    List<TableEntity> getClosedTables();
    void openTable(Long tableId);
    void closeTable(Long tableId);
    void updateTableStatus(Long tableId, TableStatus status);
}
