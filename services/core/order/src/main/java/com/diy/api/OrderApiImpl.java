package com.diy.api;

import com.diy.generated.api.OrderApi;
import com.diy.generated.model.OrderDto;
import com.diy.generated.model.OrderStatusDto;
import com.diy.generated.model.OrderWithoutPurchaseDto;
import com.diy.generated.model.PurchaseWithoutOrderDto;
import com.diy.mapper.OrderModelMapper;
import com.diy.service.OrderService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderApiImpl implements OrderApi {

    OrderModelMapper modelMapper;
    OrderService orderService;

    @Override
    public ResponseEntity<OrderWithoutPurchaseDto> createOrder(Long storeId, Long customerId, List<PurchaseWithoutOrderDto> purchaseWithoutOrderDto) {
        return ResponseEntity.ok(
                modelMapper.modelToOrderWithoutPurchaseDto(
                        orderService.createOrder(
                                modelMapper.purchaseWithoutOrderDtoToPurchaseModel(purchaseWithoutOrderDto), storeId, customerId)
                )
        );
    }

    @Override
    public ResponseEntity<String> deleteOrderById(Long orderId) {
        return ResponseEntity.ok(orderService.deleteOrderById(orderId));
    }

    @Override
    public ResponseEntity<OrderWithoutPurchaseDto> findOrderById(Long orderId) {
        return ResponseEntity.ok(modelMapper.modelToOrderWithoutPurchaseDto(orderService.findOrderById(orderId)));
    }

    @Override
    public ResponseEntity<List<OrderWithoutPurchaseDto>> findOrdersByCustomerId(Long customerId) {
        return ResponseEntity.ok(modelMapper.modelToOrderWithoutPurchaseDto(orderService.findOrdersByCustomerId(customerId)));
    }

    @Override
    public ResponseEntity<List<OrderWithoutPurchaseDto>> findOrdersByStoreId(Long storeid) {
        return ResponseEntity.ok(modelMapper.modelToOrderWithoutPurchaseDto(orderService.findOrdersByStoreId(storeid)));
    }

    @Override
    public ResponseEntity<OrderDto> updateOrder(OrderDto orderDto) {
        return ResponseEntity.ok(modelMapper.modelToDto(orderService.updateOrder(modelMapper.dtoToModel(orderDto))));
    }

    @Override
    public ResponseEntity<String> updateOrderStatus(OrderStatusDto orderStatusDto) {
        return ResponseEntity.ok(orderService.changeOrderStatus(orderStatusDto));
    }

}
