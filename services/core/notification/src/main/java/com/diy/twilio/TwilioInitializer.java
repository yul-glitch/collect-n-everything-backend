package com.diy.twilio;

import com.twilio.Twilio;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class TwilioInitializer {

    @Autowired
    public TwilioInitializer(TwilioConfiguration twilioConfiguration) {
        log.warn(twilioConfiguration.getAccountSid());
        log.warn(twilioConfiguration.getAuthToken());
        Twilio.init(
                twilioConfiguration.getAccountSid(),
                twilioConfiguration.getAuthToken()
        );
    }
}
