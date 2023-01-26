package com.diy.entity;

import com.diy.enums.PaymentState;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id", unique = true, nullable = false)
    Long paymentId;

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    PaymentState state;

    @Column(name = "transaction_price", nullable = false)
    Double transactionPrice;

    @Column(name = "order_id")
    Long orderId;

    @Column(name = "customer_id")
    Long customerId;

    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;
    @Column(name = "updated_at")
    LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    LocalDateTime deletedAt;
}
