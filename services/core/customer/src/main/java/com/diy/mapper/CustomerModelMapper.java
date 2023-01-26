package com.diy.mapper;

import com.diy.entity.CustomerEntity;
import com.diy.generated.model.CustomerDto;
import com.diy.model.CustomerModel;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN,
        typeConversionPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface CustomerModelMapper {

    CustomerDto modelToDto(CustomerModel model);
    List<CustomerDto> modelsToDtos(List<CustomerModel> models);

    CustomerModel dtoToModel(CustomerDto dto);
    List<CustomerModel> dtosToModels(List<CustomerDto> dtos);

    CustomerModel entityToModel(CustomerEntity entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
    List<CustomerModel> entitiesToModels(List<CustomerEntity> entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    CustomerEntity modelToEntity(CustomerModel model);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCustomerFromModel(CustomerModel model, @MappingTarget CustomerEntity entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

}
