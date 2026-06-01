package com.shantanu.fraud.dto;

import com.shantanu.fraud.constant.SourceType;

public record FraudEmbeddingResponse(

        Long embeddingId,
        Long caseId,
        SourceType sourceType,
        String content,
        String embedding
) {
}