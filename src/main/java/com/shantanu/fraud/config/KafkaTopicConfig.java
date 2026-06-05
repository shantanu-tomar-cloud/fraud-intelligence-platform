package com.shantanu.fraud.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@ConfigurationProperties(prefix = "app.kafka")
@Getter
@Setter
public class KafkaTopicConfig {

    private String fraudCaseTopic;
    private String fraudGroup;

    @Bean
    public NewTopic fraudCaseEventsTopic() {
        return TopicBuilder
                .name(fraudCaseTopic)
                .partitions(3)
                .replicas(1)
                .build();
    }

}