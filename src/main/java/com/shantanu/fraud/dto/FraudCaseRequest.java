package com.shantanu.fraud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record FraudCaseRequest(

        @NotNull
        Long customerId,

        @NotNull
        @Positive
        BigDecimal amount,

        @NotBlank
        String country,

        @NotBlank
        String status,

        @NotBlank
        String outcome
) {
}