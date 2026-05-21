package com.edu.bankaplication.internalService.notification;

import com.edu.bankaplication.internalService.notification.dto.NotificationMessage;

public interface NotificationService {
    void sendMessage(NotificationMessage message);
}
