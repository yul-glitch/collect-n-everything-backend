package com.diy.api;

import com.diy.generated.api.NotificationApi;
import com.diy.generated.model.SMSDto;
import com.diy.mapper.NotificationModelMapper;
import com.diy.service.NotificationService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationApiImpl implements NotificationApi {
    NotificationService notificationService;
    NotificationModelMapper modelMapper;
    @Override
    public ResponseEntity<Void> sendNotification(SMSDto smSDto) {
        notificationService.send(modelMapper.dtoToModel(smSDto));
        return null;
    }
}
