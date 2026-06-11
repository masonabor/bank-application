package com.edu.bankapplication.internalService.notification;

import com.edu.bankapplication.internalService.notification.dto.NotificationMessage;

public interface NotificationService {
    void sendMessage(NotificationMessage message);
}
