package com.edu.bankapplication.internalService.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class NotificationMessage {
    private final String message;

    @Override
    public String toString() {
        return "Notification message: " + message;
    }
}
