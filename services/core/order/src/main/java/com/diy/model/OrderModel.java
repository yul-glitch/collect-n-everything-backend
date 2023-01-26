package com.diy.model;

import com.diy.entity.PurchaseEntity;
import com.diy.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {
    Long orderId;
    Float totalPrice;
    Boolean orderPayed;
    Status status;

    Long assignedTo;
    Long storeId;
    Long customerId;
    List<PurchaseModel> purchases;

}
