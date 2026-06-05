package com.shantanu.fraud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

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
        String outcome,

        @NotBlank
        @Size(min = 20, max = 5000)
        String caseDescription,

        @NotBlank
        @Size(min = 10, max = 5000)
        String analystNotes,

        @NotBlank
        @Size(max = 2000)
        String fraudReason


) {
}