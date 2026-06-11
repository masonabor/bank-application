package com.edu.bankapplication.internalService.notification.impl;

import com.edu.bankapplication.internalService.notification.NotificationService;
import com.edu.bankapplication.internalService.notification.dto.NotificationMessage;
import com.edu.bankapplication.internalService.notification.exception.EmptyNotificationMessageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StubNotificationService implements NotificationService {

    @Override
    public void sendMessage(NotificationMessage message) {
        if (message == null)
            throw new EmptyNotificationMessageException();

        log.info("Message: {}", message);
    }
}
