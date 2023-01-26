package com.diy.model;

import com.diy.entity.StoreEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressModel {
    Long addressId;
    String city;
    String postalCode;
    String complement;
    String streetNumber;
    String streetName;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime deletedAt;
    StoreModel store;
}
