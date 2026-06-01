package com.shantanu.fraud.service.embedding;

import dev.langchain4j.model.ollama.OllamaEmbeddingModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OllamaEmbeddingService implements EmbeddingService {

    private final OllamaEmbeddingModel ollamaEmbeddingModel;

    @Override
    public float[] generateEmbedding(String text) {
        return ollamaEmbeddingModel
                .embed(text)
                .content()
                .vector();
    }
}