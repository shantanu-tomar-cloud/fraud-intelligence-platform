package com.shantanu.fraud.mapper;

import com.shantanu.fraud.constant.SourceType;
import com.shantanu.fraud.dto.FraudEmbeddingRequest;
import com.shantanu.fraud.dto.FraudEmbeddingResponse;
import com.shantanu.fraud.entity.FraudCase;
import com.shantanu.fraud.entity.FraudEmbedding;

public class FraudEmbeddingMapper {

    public static FraudEmbedding toEntity(
            FraudEmbeddingRequest request, FraudCase fraudCase) {

        return FraudEmbedding.builder()
                .sourceType(request.sourceType())
                .content(request.content())
                .fraudCase(fraudCase)
                .build();


    }
    public static FraudEmbeddingResponse toResponse(
            FraudEmbedding entity) {

        return new FraudEmbeddingResponse(
                entity.getEmbeddingId(),
                entity.getFraudCase().getCaseId(),
                entity.getSourceType(),
                entity.getContent(),
                entity.getEmbedding()
        );
    }
}