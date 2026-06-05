package com.shantanu.fraud.dto;

import java.math.BigDecimal;

public record FraudCaseResponse(
        Long caseId,
        Long customerId,
        BigDecimal amount,
        String country,
        String status,
        String outcome,
        String caseDescription,
        String fraudReason,
        String analystNotes
) {
}