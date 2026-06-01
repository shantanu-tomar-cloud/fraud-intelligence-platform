package com.shantanu.fraud.service;

import com.shantanu.fraud.dto.FraudEmbeddingRequest;
import com.shantanu.fraud.dto.FraudEmbeddingResponse;
import com.shantanu.fraud.entity.FraudCase;
import com.shantanu.fraud.entity.FraudEmbedding;
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
    public void create(FraudEmbeddingRequest request){
        log.info("Fetching fraudCase for id {}", request.caseId());
        FraudCase fraudCase =
                fraudCaseRepository.findById(request.caseId())
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Fraud case not found : "
                                                + request.caseId()));

        log.info("Generating embeddings for casiId {}", request.caseId());
        float[] vector = embeddingService.generateEmbedding(request.content());

        log.info("Saving embedding for caseId {}", request.caseId());
        String pgVector =
                toPgVector(vector);

        fraudEmbeddingRepository.insertEmbedding(
                request.caseId(),
                request.sourceType().name(),
                request.content(),
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
