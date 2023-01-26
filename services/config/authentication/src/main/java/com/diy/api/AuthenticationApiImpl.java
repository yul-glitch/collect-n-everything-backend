package com.diy.api;

import com.diy.generated.api.AuthenticationApi;

import com.diy.generated.model.LoggingDto;
import com.diy.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationApiImpl implements AuthenticationApi {
    UserService userService;

    @Override
    public ResponseEntity<String> login(LoggingDto loggingDto) {
        System.out.println(loggingDto.getEmail());
        System.out.println(loggingDto.getPassword());
        return ResponseEntity.ok(userService.login(loggingDto.getEmail(), loggingDto.getPassword()));
    }

    @Override
    public ResponseEntity<String> validate(String jwt) {
        return ResponseEntity.ok(userService.getRoleAccordingToJWT(jwt));
    }
}
