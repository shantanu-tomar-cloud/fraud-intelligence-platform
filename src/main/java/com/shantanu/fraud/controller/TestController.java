package com.shantanu.fraud.controller;

import com.shantanu.fraud.service.embedding.EmbeddingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    private final EmbeddingService embeddingService;

    @GetMapping("/embedding")
    public String testEmbedding(
            @RequestParam String text) {

        float[] vector =
                embeddingService.generateEmbedding(text);

        return "Dimensions: " + vector.length;
    }
}