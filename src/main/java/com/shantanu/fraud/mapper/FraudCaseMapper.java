package com.shantanu.fraud.mapper;

import com.shantanu.fraud.dto.FraudCaseRequest;
import com.shantanu.fraud.dto.FraudCaseResponse;
import com.shantanu.fraud.entity.FraudCase;


public class FraudCaseMapper {

    public static FraudCase toEntity(FraudCaseRequest request) {

        return FraudCase.builder()
                .customerId(request.customerId())
                .amount(request.amount())
                .country(request.country())
                .status(request.status())
                .outcome(request.outcome())
                .build();
    }

    public static FraudCaseResponse toResponse(FraudCase entity) {

        return new FraudCaseResponse(
                entity.getCaseId(),
                entity.getCustomerId(),
                entity.getAmount(),
                entity.getCountry(),
                entity.getStatus(),
                entity.getOutcome()
        );
    }
}