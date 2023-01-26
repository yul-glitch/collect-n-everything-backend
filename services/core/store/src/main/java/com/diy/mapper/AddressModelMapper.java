package com.diy.mapper;

import com.diy.entity.AddressEntity;
import com.diy.generated.model.AddressDto;
import com.diy.model.AddressModel;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE,
        typeConversionPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface AddressModelMapper {
    AddressDto modelToDto(AddressModel addressModel);
    List<AddressDto> modelsToDtos(List<AddressModel> addressModels);

    AddressModel dtoToModel(AddressDto addressDto);
    List<AddressModel> dtosToModels(List<AddressDto> addressDtos);

    AddressEntity modelToEntity(AddressModel storeModel);
    List<AddressEntity> modelsToEntities(List<AddressEntity> storeEntities);
    AddressModel entityToModel(AddressEntity storeEntity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
    List<AddressModel> entitiesToModels(List<AddressEntity> storeEntities, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStoreFromModel(AddressModel model, @MappingTarget AddressEntity entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
