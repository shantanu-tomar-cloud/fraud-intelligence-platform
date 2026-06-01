package com.shantanu.fraud.dto;

import com.shantanu.fraud.constant.SourceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FraudEmbeddingRequest(

        @NotNull
        Long caseId,

        @NotNull
        SourceType sourceType,

        @NotBlank
        String content

) {
}