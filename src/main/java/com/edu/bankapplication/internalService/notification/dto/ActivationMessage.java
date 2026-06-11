package com.edu.bankapplication.internalService.notification.dto;

import com.edu.bankapplication.internalService.notification.shared.enums.MessageType;

public record ActivationMessage(
        MessageType type
) {}
