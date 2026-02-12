package com.melnur.AdisyonTakipSistemi.entity;

import com.melnur.AdisyonTakipSistemi.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.melnur.AdisyonTakipSistemi.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private UserEntity userRole;

}
