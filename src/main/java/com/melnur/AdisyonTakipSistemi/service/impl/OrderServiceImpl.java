package com.melnur.AdisyonTakipSistemi.service.impl;

import com.melnur.AdisyonTakipSistemi.dto.request.order.OrderCreateRequest;
import com.melnur.AdisyonTakipSistemi.dto.request.order.OrderItemRequest;
import com.melnur.AdisyonTakipSistemi.dto.response.order.OrderResponse;
import com.melnur.AdisyonTakipSistemi.entity.*;
import com.melnur.AdisyonTakipSistemi.enums.OrderStatus;
import com.melnur.AdisyonTakipSistemi.enums.TableStatus;
import com.melnur.AdisyonTakipSistemi.exception.BusinessException;
import com.melnur.AdisyonTakipSistemi.exception.NotFoundException;
import com.melnur.AdisyonTakipSistemi.mapper.OrderMapper;
import com.melnur.AdisyonTakipSistemi.repository.*;
import com.melnur.AdisyonTakipSistemi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    /*
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
    */

    private final IOrderRepository _IOrderRepository;
    private final ITableRepository _ITableRepository;
    private final IProductRepository _IProductRepository;
    private final IStockRepository _IStockRepository;
    private final IUserRepository _IUserRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderResponse createOrder(OrderCreateRequest request) {
        TableEntity table = _ITableRepository.findById(request.getTableId())
                .orElseThrow(() -> new NotFoundException("Masa bulunamadı")
                );

        if (table.getTableStatus() == TableStatus.OCCUPIED) {
            throw new BusinessException("Masa zaten dolu");
        }

        OrderEntity order = new OrderEntity();
        order.setTable(table);
        order.setOrderStatus(OrderStatus.OPEN);

        List<OrderItemEntity> orderItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (OrderItemRequest itemRequest : request.getItems()) {
            ProductEntity product = _IProductRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new NotFoundException("Ürün bulunamadı"));

            StockEntity stock = _IStockRepository.findByProductId(product.getId())
                    .orElseThrow(() -> new NotFoundException("Stok bulunamadı"));

            if (stock.getQuantity() < itemRequest.getQuantity()) {
                throw new BusinessException("Yetersiz stok : " + product.getName());
            }

            stock.setQuantity(stock.getQuantity() - itemRequest.getQuantity());
            _IStockRepository.save(stock);

            BigDecimal itemTotal = product.getSalePrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity()));

            OrderItemEntity orderItem = new OrderItemEntity();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setTotalPrice(itemTotal);
            orderItem.setUnitPrice(product.getSalePrice());

            totalPrice = totalPrice.add(itemTotal);
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        order.setTotalPrice(totalPrice);

        table.setTableStatus(TableStatus.OCCUPIED);

        OrderEntity savedOrder = _IOrderRepository.save(order);

        return orderMapper.toResponse(savedOrder);
    }

    @Transactional
    @Override
    public void cancelOrder(Long orderId){
        OrderEntity order = _IOrderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Sipariş bulunamadı"));

        if (order.getOrderStatus() == OrderStatus.CANCELLED) {
            throw new BusinessException("Sipariş zaten iptal edilmiş");
        }

        if (order.getOrderStatus() == OrderStatus.PAID) {
            throw new BusinessException("Ödenmiş sipariş iptal edilemez");
        }
        for (OrderItemEntity item : order.getOrderItems()) {

            StockEntity stock = _IStockRepository.findByProductId(
                            item.getProduct().getId())
                    .orElseThrow(() -> new NotFoundException("Stok bulunamadı"));

            // stok geri ekle
            stock.setQuantity(stock.getQuantity() + item.getQuantity());
        }

        order.setOrderStatus(OrderStatus.CANCELLED);

        // masa boşalt
        TableEntity table = order.getTable();
        table.setTableStatus(TableStatus.AVAILABLE);

    }

    @Transactional
    @Override
    public void pay(Long orderId) {

        OrderEntity order = _IOrderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Sipariş bulunamadı"));

        if (order.getOrderStatus() == OrderStatus.PAID) {
            throw new BusinessException("Sipariş zaten ödenmiş");
        }
        if (order.getOrderStatus() == OrderStatus.CANCELLED) {
            throw new BusinessException("İptal edilmiş sipariş ödenemez");
        }

        order.setOrderStatus(OrderStatus.PAID);

        // masa boşalt
        TableEntity table = order.getTable();
        table.setTableStatus(TableStatus.AVAILABLE);
    }

    @Override
    public OrderResponse getOrderById(Long id){
        OrderEntity order = _IOrderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sipariş bulunamadı"));

        return orderMapper.toResponse(order);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return _IOrderRepository.findAll()
                .stream()
                .map(orderMapper::toResponse)
                .toList();
    }


    @Override
    public OrderResponse updateOrderStatus(Long id, String status){
        OrderEntity order = _IOrderRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Sipariş Bulunamadı"));

        order.setOrderStatus(OrderStatus.valueOf(status.toUpperCase()));
        return  orderMapper.toResponse(_IOrderRepository.save(order));

    }

    @Override
    public void deleteOrder(Long id) {
        OrderEntity order = _IOrderRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Sipariş bulunamadı"));
        _IOrderRepository.delete(order);
    }



}