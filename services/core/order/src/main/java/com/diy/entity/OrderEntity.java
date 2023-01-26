package com.diy.entity;

import com.diy.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", unique = true, nullable = false)
    Long orderId;

    @Column(name = "total_price")
    Float totalPrice;

    @Column(name = "assigned_to")
    Long assignedTo;

    @Column(name = "order_payed")
    Boolean orderPayed;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    Status status;

    @Column(name = "store_id")
    Long storeId;

    @Column(name = "customer_id")
    Long customerId;

    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;
    @Column(name = "updated_at")
    LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    LocalDateTime deletedAt;

    @OneToMany(mappedBy = "order")
    List<PurchaseEntity> purchases;
}
