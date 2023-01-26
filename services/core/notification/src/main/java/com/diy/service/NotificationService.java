package com.diy.service;

import com.diy.entity.NotificationEntity;
import com.diy.mapper.NotificationModelMapper;
import com.diy.model.NotificationModel;
import com.diy.repository.NotificationRepository;
import com.diy.twilio.TwilioSmsSender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import com.diy.rabbitMQ.RabbitMQMessageProducer;
import com.diy.rabbitMQ.RabbitMQConfig;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationService {

    NotificationRepository notificationRepository;
    TwilioSmsSender smsSender;
    NotificationModelMapper modelMapper;

    public void send(NotificationModel request) {
        smsSender.sendSms(request);
        NotificationEntity entity = modelMapper.modelToEntity(request);
        entity.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(entity);
    }
}
