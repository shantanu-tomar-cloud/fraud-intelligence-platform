package com.shantanu.fraud.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_cases")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FraudCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long caseId;

    private Long customerId;

    private BigDecimal amount;

    private String country;

    private String status;

    private String outcome;

    private LocalDateTime createdAt;

    @Column(length = 5000)
    private String caseDescription;

    @Column(length = 5000)
    private String analystNotes;

    @Column(length = 2000)
    private String fraudReason;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}