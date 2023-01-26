package com.diy.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotificationModel {
    Long notificationId;
    Long customerId;
    String message;
    String phoneNumber;
}
