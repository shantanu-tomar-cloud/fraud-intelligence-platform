package com.shantanu.fraud.controller;

import com.shantanu.fraud.dto.FraudEmbeddingRequest;
import com.shantanu.fraud.dto.FraudEmbeddingResponse;
import com.shantanu.fraud.service.FraudEmbeddingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/embeddings")
@RequiredArgsConstructor
public class FraudEmbeddingController {

    private final FraudEmbeddingService fraudEmbeddingService;

    @PostMapping
    public FraudEmbeddingResponse create(@Valid @RequestBody FraudEmbeddingRequest request){
        return fraudEmbeddingService.create(request);
    }

    @GetMapping("/case/{caseId}")
    public List<FraudEmbeddingResponse> getEmbeddingsForCase(@PathVariable Long caseId){
        return fraudEmbeddingService.getByCaseId(caseId);
    }

    @GetMapping("/search")
    public List<FraudEmbeddingResponse> searchVectors(@RequestParam(value = "vector") String vector,
                                                      @RequestParam(value = "limit") int limit){
        return fraudEmbeddingService.search(vector,limit);
    }
}
