package com.shantanu.fraud.service;

import com.shantanu.fraud.dto.FraudEmbeddingRequest;
import com.shantanu.fraud.dto.FraudEmbeddingResponse;
import com.shantanu.fraud.entity.FraudCase;
import com.shantanu.fraud.entity.FraudEmbedding;
import com.shantanu.fraud.exception.ResourceNotFoundException;
import com.shantanu.fraud.mapper.FraudEmbeddingMapper;
import com.shantanu.fraud.repository.FraudCaseRepository;
import com.shantanu.fraud.repository.FraudEmbeddingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FraudEmbeddingService {

    private final FraudEmbeddingRepository fraudEmbeddingRepository;
    private final FraudCaseRepository fraudCaseRepository;

    @Transactional
    public FraudEmbeddingResponse create(FraudEmbeddingRequest request){
        log.info("Fetching fraudCase for id {}", request.caseId());
        FraudCase fraudCase =
                fraudCaseRepository.findById(request.caseId())
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Fraud case not found : "
                                                + request.caseId()));
        log.info("Saving embedding for caseId {}", request.caseId());
        FraudEmbedding embedding = fraudEmbeddingRepository.save(
                FraudEmbeddingMapper.toEntity(request, fraudCase));

        return FraudEmbeddingMapper.toResponse(embedding);
    }

    public List<FraudEmbeddingResponse> getByCaseId(Long caseId){

        log.info("Fetching embeddings for fraudCase with id {}", caseId);
        return fraudEmbeddingRepository.findByFraudCaseCaseId(caseId)
                .stream().map(FraudEmbeddingMapper::toResponse).toList();

    }

    public List<FraudEmbeddingResponse> search(
            String queryVector,
            int limit) {

        return fraudEmbeddingRepository
                .findSimilarEmbeddings(queryVector, Math.min(limit, 20))
                .stream()
                .map(FraudEmbeddingMapper::toResponse)
                .toList();
    }
}
