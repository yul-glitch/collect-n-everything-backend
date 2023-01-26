package com.diy.model;

import com.diy.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserModel {
    Long userId;
    String email;
    String password;
    String firstName;
    String lastName;
    String phoneNumber;
    Long storeId;
    Roles role;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime deletedAt;
}
