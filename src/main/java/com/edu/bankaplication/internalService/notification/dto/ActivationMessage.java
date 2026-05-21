package com.edu.bankaplication.internalService.notification.dto;

import com.edu.bankaplication.internalService.notification.shared.enums.MessageType;

public record ActivationMessage(
        MessageType type
) {}
