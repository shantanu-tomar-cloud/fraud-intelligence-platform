package com.shantanu.fraud.entity;

import com.shantanu.fraud.constant.SourceType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_embeddings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FraudEmbedding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long embeddingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_id", nullable = false)
    private FraudCase fraudCase;

    @Enumerated(EnumType.STRING)
    private SourceType sourceType;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(
            name = "embedding",
            columnDefinition = "vector(768)"
    )
    private String embedding;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}