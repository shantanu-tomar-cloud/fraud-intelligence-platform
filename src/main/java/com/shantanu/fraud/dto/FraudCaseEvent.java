package com.shantanu.fraud.dto;

import com.shantanu.fraud.constant.SourceType;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record FraudCaseEvent(Long caseId,
                             Long customerId,
                             BigDecimal amount,
                             String country,
                             String status,
                             String outcome,
                             LocalDateTime createdAt,
                             String caseDescription,
                             String analystNotes,
                             String fraudReason
) {
    public String toEmbeddingText() {
        return """
                Case Description: %s
                Analyst Notes: %s
                Fraud Reason: %s
                """
                .formatted(
                        caseDescription,
                        analystNotes,
                        fraudReason
                );
    }
}