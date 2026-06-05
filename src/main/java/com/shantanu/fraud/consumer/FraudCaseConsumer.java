package com.shantanu.fraud.consumer;

import com.shantanu.fraud.dto.FraudCaseEvent;
import com.shantanu.fraud.service.FraudEmbeddingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FraudCaseConsumer {

    private final FraudEmbeddingService fraudEmbeddingService;

    @KafkaListener(
            topics = "${app.kafka.fraud-case-topic}",
            groupId = "${app.kafka.fraud-group}"
    )
    public void consume(FraudCaseEvent event) {
        log.info(
                "Received fraud case event. CaseId={}, Country={}, Amount={}, Status={}",
                event.caseId(),
                event.country(),
                event.amount(),
                event.status()
        );
        try {
            fraudEmbeddingService.create(event);
        } catch (Exception ex) {
            log.error(
                    "Failed to generate embedding. CaseId={}",
                    event.caseId(),
                    ex
            );
            throw ex;
        }
    }
}