package com.diy.mapper;

import com.diy.entity.NotificationEntity;
import com.diy.generated.model.SMSDto;
import com.diy.model.NotificationModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN,
        typeConversionPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface NotificationModelMapper {
    NotificationModel dtoToModel(SMSDto dto);
    NotificationEntity modelToEntity(NotificationModel model);
}
