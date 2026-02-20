package com.melnur.AdisyonTakipSistemi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.melnur.AdisyonTakipSistemi.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.List;
import com.melnur.AdisyonTakipSistemi.entity.BaseEntity;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class OrderEntity extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    private TableEntity table;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<OrderItemEntity> orderItems;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user; // userRole yerine user demek kafa karışıklığını önler.
}