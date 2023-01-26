package com.diy.entity;

import com.diy.enums.Roles;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", unique = true, nullable = false)
    Long userId;

    @Column(name = "email", unique = true, nullable = false)
    String email;

    @Column(name = "user_password", nullable = false)
    String password;

    @Column(name = "store_id", nullable = false)
    Long storeId;

    @Column(name = "customer_id")
    Long customerId;

    @Column(name = "user_role", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    Roles role;

    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;
    @Column(name = "updated_at")
    LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    LocalDateTime deletedAt;


}
