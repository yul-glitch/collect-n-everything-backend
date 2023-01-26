package com.diy.mapper;

import com.diy.entity.OrderEntity;
import com.diy.generated.model.OrderDto;
import com.diy.generated.model.OrderWithoutPurchaseDto;
import com.diy.generated.model.PurchaseWithoutOrderDto;
import com.diy.model.OrderModel;
import com.diy.model.PurchaseModel;
import org.hibernate.criterion.Order;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN,
        typeConversionPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OrderModelMapper {
    List<OrderModel> entitiesToModels(List<OrderEntity> entities, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
    OrderModel entityToModel(OrderEntity entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    OrderEntity modelToEntity(OrderModel model);

    OrderWithoutPurchaseDto modelToOrderWithoutPurchaseDto(OrderModel model);
    List<OrderWithoutPurchaseDto> modelToOrderWithoutPurchaseDto(List<OrderModel> model);

    List<PurchaseModel> purchaseWithoutOrderDtoToPurchaseModel(List<PurchaseWithoutOrderDto> dto);

    OrderDto modelToDto(OrderModel orderModel);
    OrderModel dtoToModel(OrderDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateOrderFromModel(OrderModel model, @MappingTarget OrderEntity entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

}
