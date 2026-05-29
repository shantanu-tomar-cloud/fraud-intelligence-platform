package com.shantanu.fraud.repository;

import com.shantanu.fraud.entity.FraudCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudCaseRepository
        extends JpaRepository<FraudCase, Long> {
}