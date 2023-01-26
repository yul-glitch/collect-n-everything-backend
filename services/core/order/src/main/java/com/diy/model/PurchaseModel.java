package com.diy.model;

import com.diy.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseModel {
    Long purchaseId;
    Float price;
    Long productId;
    OrderModel order;
}
