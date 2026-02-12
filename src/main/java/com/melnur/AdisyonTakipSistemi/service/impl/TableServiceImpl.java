package com.melnur.AdisyonTakipSistemi.service.impl;

import com.melnur.AdisyonTakipSistemi.dto.request.table.TableCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.table.TableResponse;
import com.melnur.AdisyonTakipSistemi.entity.TableEntity;
import com.melnur.AdisyonTakipSistemi.enums.OrderStatus;
import com.melnur.AdisyonTakipSistemi.enums.TableStatus;
import com.melnur.AdisyonTakipSistemi.exception.BusinessException;
import com.melnur.AdisyonTakipSistemi.exception.NotFoundException;
import com.melnur.AdisyonTakipSistemi.mapper.TableMapper;
import com.melnur.AdisyonTakipSistemi.repository.IOrderRepository;
import com.melnur.AdisyonTakipSistemi.repository.ITableRepository;
import com.melnur.AdisyonTakipSistemi.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {
    private final ITableRepository _ITableRepository;
    private final TableMapper tableMapper;
    private final IOrderRepository _IOrderRepository;

    @Transactional
    @Override
    public TableResponse createTable(TableCreateRequest request) {
        if (_ITableRepository.existsByTableNumber(request.getTableNumber())) {
            throw new BusinessException("Bu masa numarası zaten mevcut");
        }

        TableEntity table = new TableEntity();
        table.setTableNumber(request.getTableNumber());
        table.setTableStatus(TableStatus.OPEN);

        return tableMapper.toResponse(_ITableRepository.save(table));
    }

    @Override
    public List<TableResponse> getAllTables() {
        return _ITableRepository.findAll()
                .stream()
                .map(tableMapper::toResponse)
                .toList();
    }

    @Override
    public TableResponse getById(Long id) {
        return tableMapper.toResponse(_ITableRepository.findByTableId(id));
    }

    @Override
    public TableResponse getByTableNumber(int tableNumber) {
        TableEntity table = _ITableRepository.findByTableNumber(tableNumber)
                .orElseThrow(() -> new NotFoundException("Masa bulunamadı"));

        return tableMapper.toResponse(table);
    }

    @Override
    public List<TableResponse> getOpenTables() {
        return _ITableRepository.findByTableStatus(TableStatus.OPEN)
                .stream()
                .map(tableMapper::toResponse)
                .toList();
    }

    @Override
    public List<TableResponse> getClosedTables() {
        return _ITableRepository.findByTableStatus(TableStatus.CLOSED)
                .stream()
                .map(tableMapper::toResponse)
                .toList();
    }
    @Transactional
    @Override
    public void openTable(Long tableId) {
        TableEntity table = _ITableRepository.findByTableId(tableId);

        if (table.getTableStatus() == TableStatus.OPEN) {
            throw new BusinessException("Masa zaten açık");
        }

        table.setTableStatus(TableStatus.OPEN);

    }

    @Transactional
    @Override
    public void closeTable(Long tableId) {
        TableEntity table = _ITableRepository.findByTableId(tableId);

        if(table.getTableStatus() == TableStatus.CLOSED){
            throw new BusinessException("Masa zaten kapalı");
        }
        boolean hasActiveOrders = _IOrderRepository
                .existsByTableIdAndOrderStatusIn(tableId,
                        List.of(OrderStatus.OPEN, OrderStatus.ACTIVE));
        if (hasActiveOrders) {
            throw new BusinessException("Bu masada açık adisyon var, ödemeyi tamamlayın");
        }
        table.setTableStatus(TableStatus.CLOSED);
    }

    @Transactional
    @Override
    public void updateTableStatus(Long tableId, TableStatus status) {
        TableEntity table = _ITableRepository.findByTableId(tableId);
        table.setTableStatus(status);
    }

    public long getActiveTableCount() {
        return _ITableRepository.countByTableStatus(TableStatus.OPEN);
    }


    /*
    @Transactional
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
     */
}


