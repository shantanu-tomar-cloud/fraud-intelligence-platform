package com.shantanu.fraud.repository;

import com.shantanu.fraud.entity.FraudEmbedding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying
    @Query(value = """
            INSERT INTO fraud_embeddings
            (
                case_id,
                source_type,
                content,
                embedding,
                created_at
            )
            VALUES
            (
                :caseId,
                :sourceType,
                :content,
                CAST(:embedding AS vector),
                CURRENT_TIMESTAMP
            )
            """,
            nativeQuery = true)
    void insertEmbedding(
            @Param("caseId") Long caseId,
            @Param("sourceType") String sourceType,
            @Param("content") String content,
            @Param("embedding") String embedding);
}