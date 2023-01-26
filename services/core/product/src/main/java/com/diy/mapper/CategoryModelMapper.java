package com.diy.mapper;

import com.diy.entity.CategoryEntity;
import com.diy.entity.ProductEntity;
import com.diy.generated.model.CategoryWithProductDto;
import com.diy.generated.model.CategoryWithoutProductDto;
import com.diy.model.CategoryModel;
import com.diy.model.ProductModel;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE,
        typeConversionPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface CategoryModelMapper {
    CategoryModel categoryWithoutProductDtoToModel(CategoryWithoutProductDto categoryWithoutProductDto);
    CategoryModel entityToModel(CategoryEntity categoryEntity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
    List<CategoryModel> entitiesToModels(List<CategoryEntity> categoryEntities, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    CategoryEntity modelToEntity(CategoryModel model);
    List<CategoryEntity> modelsToEntities(List<CategoryModel> categoryModels);

    List<CategoryWithoutProductDto> modelsToCategoryWoProductDtos(List<CategoryModel> models);
    CategoryWithoutProductDto modelToCategoryWoPDto(CategoryModel models);
    CategoryWithProductDto modelToCategoryWPDto(CategoryModel model);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategoryFromModel(CategoryModel model, @MappingTarget CategoryEntity entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

}
