package com.melnur.AdisyonTakipSistemi.service;

import com.melnur.AdisyonTakipSistemi.entity.OrderEntity;
import com.melnur.AdisyonTakipSistemi.entity.OrderItemEntity;
import com.melnur.AdisyonTakipSistemi.entity.ProductEntity;
import com.melnur.AdisyonTakipSistemi.entity.TableEntity;
import com.melnur.AdisyonTakipSistemi.repository.OrderRepository;
import com.melnur.AdisyonTakipSistemi.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final TableRepository tableRepository;
    private final StockService stockService;
    private final TableService tableService;

    @Transactional
    public OrderEntity openOrder(Long tableId) {
        // 1. Masayı veritabanından bul
        TableEntity table = tableRepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Masa bulunamadı: " + tableId));

        // 2. Masayı 'OPEN' durumuna getir
        table.setTableStatus(TableEntity.TableStatus.OPEN);
        tableRepository.save(table);

        // 3. Yeni adisyonu oluştur ve masayı içine setle
        OrderEntity order = new OrderEntity();

        // HATA BURADAYDI: Entity içindeki masa alanının ismi neyse onu setlemelisin.
        // Eğer değişken ismi tableId ise onu, table ise onu kullan.
        order.setTableId(table);

        return orderRepository.save(order);
    }

    @Transactional
    public void addItemOrder(Long orderId, Long productId, int quantity) {
        // 1. İlgili adisyonu (Order) bul
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Adisyon bulunamadı"));

        // 2. Ürünü stoktan düş (Hata varsa burada fırlatılır ve işlem iptal olur)
        stockService.decreaseStock(productId, quantity);

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
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Adisyon bulunamadı"));

        orderRepository.save(order);
    }
}