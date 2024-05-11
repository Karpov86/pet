package com.example.demo.service;

import com.example.demo.dto.TestData;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, TestData> kafkaTemplate;

    public void send(TestData data) {
        kafkaTemplate.send("test-data-topic", data);
    }

}
