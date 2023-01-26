package com.diy.mapper;

import com.diy.entity.ProductEntity;
import com.diy.generated.model.CategoryWithoutProductDto;
import com.diy.generated.model.ProductDto;
import com.diy.generated.model.ProductWithoutCategoryDto;
import com.diy.model.ProductModel;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE,
        typeConversionPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface ProductModelMapper {
    ProductDto toProductDto(ProductModel productModel);
    List<ProductDto> modelsToProductDtos(List<ProductModel> productModels);

    ProductWithoutCategoryDto toProductWOCategoryDto(ProductModel productModel);
    List<ProductWithoutCategoryDto> toProductWOCategoryDto(List<ProductModel> productModel);

    ProductModel dtoToModel(ProductDto productDto);
    List<ProductModel> dtosToModels(List<ProductDto> productDtos);

    ProductEntity modelToEntity(ProductModel productModel);
    List<ProductEntity> modelsToEntities(List<ProductModel> productModels);
    ProductModel entityToModel(ProductEntity productEntity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
    List<ProductModel> entitiesToModels(List<ProductEntity> productEntities, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStoreFromModel(ProductModel model, @MappingTarget ProductEntity entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

}
