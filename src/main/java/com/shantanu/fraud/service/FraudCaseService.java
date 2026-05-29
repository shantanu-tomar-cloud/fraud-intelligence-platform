package com.shantanu.fraud.service;

import com.shantanu.fraud.dto.FraudCaseRequest;
import com.shantanu.fraud.dto.FraudCaseResponse;
import com.shantanu.fraud.exception.ResourceNotFoundException;
import com.shantanu.fraud.mapper.FraudCaseMapper;
import com.shantanu.fraud.repository.FraudCaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FraudCaseService {

    private final FraudCaseRepository repository;

    public FraudCaseResponse create(FraudCaseRequest request) {
        log.info("Creating fraud case for customer {}", request.customerId());
        return FraudCaseMapper.toResponse(repository.save(FraudCaseMapper.toEntity(request)));
    }

    public List<FraudCaseResponse> getAllCases() {
        return repository.findAll().stream().map(FraudCaseMapper::toResponse).toList();
    }

    public FraudCaseResponse getCase(Long id) {
        log.info("Fetching fraud case {}", id);
        return FraudCaseMapper.toResponse(repository.findById(id)
                .orElseThrow(() -> {
                        log.warn("Fraud case {} not found", id);
                    return new ResourceNotFoundException(
                            "Fraud case not found with id : " + id);
                }));
    }
}