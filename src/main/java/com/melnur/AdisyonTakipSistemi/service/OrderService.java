package com.melnur.AdisyonTakipSistemi.service;

import com.melnur.AdisyonTakipSistemi.entity.OrderEntity;
import com.melnur.AdisyonTakipSistemi.entity.OrderItemEntity;
import com.melnur.AdisyonTakipSistemi.entity.ProductEntity;
import com.melnur.AdisyonTakipSistemi.repository.OrderItemRepository;
import com.melnur.AdisyonTakipSistemi.repository.OrderRepository;
import com.melnur.AdisyonTakipSistemi.repository.ProductRepository;
import com.melnur.AdisyonTakipSistemi.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final StockService stockService;

    //  adisyon aç
    public OrderEntity openOrder(){
        OrderEntity order = new OrderEntity();
        order.setOrderStatus(OrderEntity.OrderStatus.valueOf("OPEN"));
        order.setTotalPrice(BigDecimal.ZERO);
        return orderRepository.save(order);
    }

    // adisyona ürün ekle
    public void addItemOrder(Long orderId, Long productId, int quantity){
        OrderEntity order = orderRepository.findById(orderId).
                orElseThrow(() -> new RuntimeException("Adisyon bulunamadı"));

        ProductEntity product = productRepository.findById(productId).
                orElseThrow(() -> new RuntimeException("Ürün bulunamadı"));

        stockService.decreaseStock(productId, quantity);

        OrderItemEntity item = new OrderItemEntity();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(quantity);
        item.setUnitPrice(product.getSalePrice());
        orderItemRepository.save(item);

        BigDecimal itemTotal = product.getSalePrice().multiply(BigDecimal.valueOf(quantity));

        order.setTotalPrice(order.getTotalPrice().add(itemTotal));
    }

    // adisyon kapat
    public void closeOrder(Long orderId){
        OrderEntity order = orderRepository.findById(orderId).
                orElseThrow(() -> new RuntimeException("Adisyon bulunamadı"));

        order.setOrderStatus(OrderEntity.OrderStatus.valueOf("CLOSED"));
        orderRepository.save(order);
    }
}

