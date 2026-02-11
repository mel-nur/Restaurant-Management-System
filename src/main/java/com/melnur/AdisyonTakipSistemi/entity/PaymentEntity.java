package com.melnur.AdisyonTakipSistemi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.melnur.AdisyonTakipSistemi.enums.PaymentType;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
public class PaymentEntity extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

}
