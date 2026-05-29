package com.shantanu.fraud.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
public record ErrorResponse(
        String code,
        String message,
        LocalDateTime timestamp,
        Map<String, String> errors
) {
}