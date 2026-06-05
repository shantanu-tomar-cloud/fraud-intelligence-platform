package com.shantanu.fraud.service;

import com.shantanu.fraud.config.KafkaTopicConfig;
import com.shantanu.fraud.dto.FraudCaseEvent;
import com.shantanu.fraud.entity.FraudCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.kafka.autoconfigure.KafkaProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaEventProducer {

    private final KafkaTemplate<String, FraudCaseEvent> kafkaTemplate;
    private final KafkaTopicConfig kafkaTopicConfig;

    public void publishCaseCreated(FraudCase fraudCase) {
        log.info("Publishing event. Case id {}", fraudCase.getCaseId());
        FraudCaseEvent event =
                FraudCaseEvent.builder()
                        .caseId(fraudCase.getCaseId())
                        .customerId(fraudCase.getCustomerId())
                        .amount(fraudCase.getAmount())
                        .country(fraudCase.getCountry())
                        .status(fraudCase.getStatus())
                        .outcome(fraudCase.getOutcome())
                        .createdAt(fraudCase.getCreatedAt())
                        .caseDescription(fraudCase.getCaseDescription())
                        .analystNotes(fraudCase.getAnalystNotes())
                        .fraudReason(fraudCase.getFraudReason())
                        .build();

        kafkaTemplate.send(kafkaTopicConfig.getFraudCaseTopic(),fraudCase.getCaseId().toString(), event);
        log.info("Event published. Case id {}", fraudCase.getCaseId());
    }
}