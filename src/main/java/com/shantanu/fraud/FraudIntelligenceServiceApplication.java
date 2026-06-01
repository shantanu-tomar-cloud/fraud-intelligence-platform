package com.shantanu.fraud;

import com.shantanu.fraud.config.OllamaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(OllamaProperties.class)
public class FraudIntelligenceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FraudIntelligenceServiceApplication.class, args);
	}

}
