package com.diy.model;

import com.diy.entity.CategoryEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductModel {
    Long productId;
    String name;
    Float price;
    String description;
    Float weight;
    Float height ;
    String photo;
    Boolean availableInStore;
    Long storeId;
    CategoryModel category;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime deletedAt;
}
