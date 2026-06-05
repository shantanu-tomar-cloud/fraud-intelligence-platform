package com.shantanu.fraud.service;

import com.shantanu.fraud.dto.FraudCaseEvent;
import com.shantanu.fraud.dto.FraudEmbeddingRequest;
import com.shantanu.fraud.dto.FraudEmbeddingResponse;
import com.shantanu.fraud.entity.FraudCase;
import com.shantanu.fraud.exception.ResourceNotFoundException;
import com.shantanu.fraud.mapper.FraudEmbeddingMapper;
import com.shantanu.fraud.repository.FraudCaseRepository;
import com.shantanu.fraud.repository.FraudEmbeddingRepository;
import com.shantanu.fraud.service.embedding.EmbeddingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FraudEmbeddingService {

    private final FraudEmbeddingRepository fraudEmbeddingRepository;
    private final FraudCaseRepository fraudCaseRepository;
    private final EmbeddingService embeddingService;

    @Transactional
    public void create(FraudCaseEvent event){
        log.info("Fetching fraudCase for id {}", event.caseId());
        FraudCase fraudCase =
                fraudCaseRepository.findById(event.caseId())
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Fraud case not found : "
                                                + event.caseId()));

        log.info("Generating embeddings for casiId {}", event.caseId());
        String content = event.toEmbeddingText();
        float[] vector = embeddingService.generateEmbedding(content);

        log.info("Saving embedding for caseId {}", event.caseId());
        String pgVector =
                toPgVector(vector);

        fraudEmbeddingRepository.insertEmbedding(
                event.caseId(),
                event.status(),
                content,
                pgVector);

    }

    public List<FraudEmbeddingResponse> getByCaseId(Long caseId){

        log.info("Fetching embeddings for fraudCase with id {}", caseId);
        return fraudEmbeddingRepository.findByFraudCaseCaseId(caseId)
                .stream().map(FraudEmbeddingMapper::toResponse).toList();

    }

    public List<FraudEmbeddingResponse> search(
            String query,
            int limit) {

        float[] vector = embeddingService.generateEmbedding(query);

        return fraudEmbeddingRepository
                .findSimilarEmbeddings(toPgVector(vector), Math.min(limit, 20))
                .stream()
                .map(FraudEmbeddingMapper::toResponse)
                .toList();
    }

    private String toPgVector(float[] vector){
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < vector.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(vector[i]);
        }
        sb.append("]");
        return sb.toString();
    }

}
