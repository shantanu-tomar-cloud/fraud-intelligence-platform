package com.shantanu.fraud.controller;

import com.shantanu.fraud.dto.FraudCaseRequest;
import com.shantanu.fraud.dto.FraudCaseResponse;
import com.shantanu.fraud.service.FraudCaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fraud-cases")
@RequiredArgsConstructor
public class FraudCaseController {

    private final FraudCaseService service;

    @PostMapping
    public FraudCaseResponse create(@Valid @RequestBody FraudCaseRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<FraudCaseResponse> getAll() {
        return service.getAllCases();
    }

    @GetMapping("/{id}")
    public FraudCaseResponse getCase(@PathVariable Long id) {
        return service.getCase(id);
    }
}