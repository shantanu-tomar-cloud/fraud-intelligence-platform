package com.shantanu.fraud.controller;

import com.shantanu.fraud.dto.FraudEmbeddingRequest;
import com.shantanu.fraud.dto.FraudEmbeddingResponse;
import com.shantanu.fraud.service.FraudEmbeddingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/embeddings")
@RequiredArgsConstructor
public class FraudEmbeddingController {

    private final FraudEmbeddingService fraudEmbeddingService;

    @PostMapping
    public void create(@Valid @RequestBody FraudEmbeddingRequest request){
//        fraudEmbeddingService.create(request);
    }

    @GetMapping("/case/{caseId}")
    public List<FraudEmbeddingResponse> getEmbeddingsForCase(@PathVariable Long caseId){
        return fraudEmbeddingService.getByCaseId(caseId);
    }

    @GetMapping("/search")
    public List<FraudEmbeddingResponse> searchVectors(@RequestParam(value = "query") String query,
                                                      @RequestParam(value = "limit") int limit){
        return fraudEmbeddingService.search(query,limit);
    }
}
