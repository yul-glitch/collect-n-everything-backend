    package com.diy.mapper;

    import com.diy.entity.CustomisationEntity;
    import com.diy.generated.model.CustomisationDto;
    import com.diy.model.CustomisationModel;
    import org.mapstruct.*;

    import java.util.List;

    @Mapper(unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN,
            typeConversionPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
    public interface CustomisationModelMapper {
        CustomisationDto modelToDto(CustomisationModel model);
        CustomisationModel dtoToModel(CustomisationDto dto);
        List<CustomisationDto> modelsToDtos(List<CustomisationModel> model);
        List<CustomisationModel> dtosToModels(List<CustomisationDto> dto);

        CustomisationModel entityToModel(CustomisationEntity entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
        List<CustomisationModel> entitiesToModels(List<CustomisationEntity> entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

        CustomisationEntity modelToEntity(CustomisationModel model);
        @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        void updateStoreFromModel(CustomisationModel model, @MappingTarget CustomisationEntity entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
    }
