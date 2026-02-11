package com.melnur.AdisyonTakipSistemi.service.impl;

import com.melnur.AdisyonTakipSistemi.entity.OrderEntity;
import com.melnur.AdisyonTakipSistemi.entity.OrderItemEntity;
import com.melnur.AdisyonTakipSistemi.entity.ProductEntity;
import com.melnur.AdisyonTakipSistemi.entity.TableEntity;
import com.melnur.AdisyonTakipSistemi.enums.TableStatus;
import com.melnur.AdisyonTakipSistemi.exception.NotFoundException;
import com.melnur.AdisyonTakipSistemi.repository.IOrderRepository;
import com.melnur.AdisyonTakipSistemi.repository.ITableRepository;
import com.melnur.AdisyonTakipSistemi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final IOrderRepository _IOrderRepository;
    private final ITableRepository _ITableRepository;
    private final StockServiceImpl stockServiceImpl;
    private final TableServiceImpl tableServiceImpl;

    @Override
    @Transactional
    public OrderEntity openOrder(Long tableId) {
        // 1. Masayı veritabanından bul
        TableEntity table = _ITableRepository.findById(tableId)
                .orElseThrow(() -> new NotFoundException("Masa bulunamadı: " + tableId));

        // 2. Masayı 'OPEN' durumuna getir
        table.setTableStatus(TableStatus.OPEN);
        _ITableRepository.save(table);

        // 3. Yeni adisyonu oluştur ve masayı içine setle
        OrderEntity order = new OrderEntity();

        // HATA BURADAYDI: Entity içindeki masa alanının ismi neyse onu setlemelisin.
        // Eğer değişken ismi tableId ise onu, table ise onu kullan.
        order.setTableId(table);

        return _IOrderRepository.save(order);
    }

    @Override
    @Transactional
    public void addItemOrder(Long orderId, Long productId, int quantity) {
        // 1. İlgili adisyonu (Order) bul
        OrderEntity order = _IOrderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Adisyon bulunamadı"));

        // 2. Ürünü stoktan düş (Hata varsa burada fırlatılır ve işlem iptal olur)
        stockServiceImpl.decreaseStock(productId, quantity);

        ProductEntity product = new ProductEntity();
        product.setId(productId);
        // 3. Yeni bir sipariş kalemi (OrderItem) oluştur
        // Not: Bu adım için OrderItemEntity sınıfının olması gerekir.
        OrderItemEntity orderItem = new OrderItemEntity();
        orderItem.setOrder(order);
        orderItem.setProduct(product); // Veya doğrudan ProductEntity setlenebilir
        orderItem.setQuantity(quantity);

        // 4. Sipariş kalemini kaydet
        // orderItemRepository.save(orderItem);
    }

    @Transactional
    public void closeOrder(Long orderId, Long tableId) {
        OrderEntity order = _IOrderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Adisyon bulunamadı"));

        _IOrderRepository.save(order);
    }
}