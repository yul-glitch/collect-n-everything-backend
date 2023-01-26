package com.diy.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id", unique = true, nullable = false)
    Long productId;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "price", nullable = false)
    Float price;

    @Column(name = "description", nullable = false)
    String description;

    @Column(name = "weight")
    Float weight;

    @Column(name = "height")
    Float height ;

    @Column(name = "photo")
    String photo;

    @Column(name = "available_in_store", nullable = false)
    Boolean availableInStore;

    @Column(name = "store_id", nullable = false)
    Long storeId;

    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;
    @Column(name = "updated_at")
    LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    CategoryEntity category;

}
