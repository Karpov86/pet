package com.example.demo.controller;

import com.example.demo.dao.model.Journal;
import com.example.demo.dao.model.Payment;
import com.example.demo.dao.repo.JournalRepository;
import com.example.demo.service.JournalService;
import com.example.demo.util.GenerateTestDataUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("v1/api/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final JournalService journalService;
    private final ObjectMapper objectMapper;

    @GetMapping("/generate")
    @Operation(summary = "Generate test payment data")
    @ResponseStatus(HttpStatus.OK)
    public void generateAndSendData() {
        var payments = GenerateTestDataUtils.generatePayments();

        journalService.journalize(payments);

        payments.forEach(p -> {
            try {
                kafkaTemplate.send("payments", objectMapper.writeValueAsString(p));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

}

