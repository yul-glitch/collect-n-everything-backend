package com.diy.mapper;

import com.diy.entity.UserEntity;
import com.diy.generated.model.UserDto;
import com.diy.model.UserModel;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN,
        typeConversionPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserModelMapper {
    UserDto toDto(UserModel userModel);
    List<UserDto> toDtos(Collection<UserDto> userModels);
    UserModel entityToModel(UserEntity userEntity, @Context CycleAvoidingMappingContext c);
    UserEntity toEntity(UserModel model);
    UserModel dtoToModel(UserDto userDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStoreFromModel(UserModel model, @MappingTarget UserEntity entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

}
