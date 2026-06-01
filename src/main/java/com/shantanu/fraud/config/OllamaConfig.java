package com.shantanu.fraud.config;

import dev.langchain4j.model.ollama.OllamaEmbeddingModel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OllamaConfig {

    private final OllamaProperties ollamaProperties;

    @Bean
    public OllamaEmbeddingModel ollamaEmbeddingModel() {
        return OllamaEmbeddingModel.builder()
                .baseUrl(ollamaProperties.baseUrl())
                .modelName(ollamaProperties.embeddingModel())
                .build();
    }
}