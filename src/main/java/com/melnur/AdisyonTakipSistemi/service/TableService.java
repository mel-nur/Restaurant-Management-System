package com.melnur.AdisyonTakipSistemi.service;

import com.melnur.AdisyonTakipSistemi.dto.request.table.TableCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.table.TableResponse;
import com.melnur.AdisyonTakipSistemi.entity.TableEntity;
import com.melnur.AdisyonTakipSistemi.enums.TableStatus;

import java.util.List;

public interface TableService {

    TableResponse createTable(TableCreateRequest request);
    List<TableResponse> getAllTables();
    TableResponse getById(Long id);
    TableResponse getByTableNumber(int tableNumber);
    List<TableResponse> getOpenTables();
    List<TableResponse> getClosedTables();
    void openTable(Long tableId);
    void closeTable(Long tableId);
    void updateTableStatus(Long tableId, TableStatus status);
    long getActiveTableCount();
}
