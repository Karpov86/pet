package com.example.demo.service.impl;

import com.example.demo.dao.model.Payment;
import com.example.demo.exception.PaymentSerializationException;
import com.example.demo.service.KafkaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentKafkaService implements KafkaService<Payment> {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${kafka.topic.payment}")
    private String topicName;

    @Override
    public void send(List<Payment> payments) {
        payments.forEach(this::sendPayment);
    }

    private void sendPayment(Payment payment) {
        try {
            kafkaTemplate.send(topicName, objectMapper.writeValueAsString(payment));
        } catch (JsonProcessingException e) {
            throw new PaymentSerializationException("Failed to serialize payment for Kafka", e);
        }
    }
}
