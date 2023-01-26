package com.diy.entity;

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
@Table(name = "store")
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id", unique = true, nullable = false)
    Long storeId;
    @Column(name = "siret", unique = true, nullable = false)
    String siret;
    @Column(name = "store_name", unique = true, nullable = false)
    String storeName;
    @Column(name = "sector", nullable = false)
    String sector;
    @Column(name = "firstname", nullable = false)
    String firstname;
    @Column(name = "lastname", nullable = false)
    String lastname;
    @Column(name = "email", nullable = false, unique = true)
    String email;
    @Column(name = "phone_number", nullable = false)
    String phoneNumber;

    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;
    @Column(name = "updated_at")
    LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    LocalDateTime deletedAt;

    @Column(name = "subscription_id")
    Long subscriptionId;

    @OneToMany(mappedBy = "store")
    List<AddressEntity> addresses;
}
