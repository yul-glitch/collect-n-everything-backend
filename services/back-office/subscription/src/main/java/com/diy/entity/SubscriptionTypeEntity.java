package com.diy.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subscription_type")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubscriptionTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subscription_type_id", nullable = false, unique = true)
    Long subscriptionTypeId;
}
