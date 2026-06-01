package com.shantanu.fraud.repository;

import com.shantanu.fraud.entity.FraudEmbedding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FraudEmbeddingRepository
        extends JpaRepository<FraudEmbedding, Long> {

    List<FraudEmbedding> findByFraudCaseCaseId(Long caseId);

    @Query(value = """
        SELECT *
        FROM fraud_embeddings
        ORDER BY embedding <-> CAST(:queryVector AS vector)
        LIMIT :limit
        """,
            nativeQuery = true)
    List<FraudEmbedding> findSimilarEmbeddings(
            @Param("queryVector") String queryVector,
            @Param("limit") int limit);
}