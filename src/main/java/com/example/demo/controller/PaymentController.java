package com.example.demo.controller;

import com.example.demo.dao.model.Payment;
import com.example.demo.service.JournalService;
import com.example.demo.service.KafkaService;
import com.example.demo.util.GenerateTestDataUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final KafkaService<Payment> kafkaService;
    private final JournalService journalService;

    @GetMapping("/generate")
    @Operation(summary = "Generate test payment data")
    @ResponseStatus(HttpStatus.OK)
    public void generateAndSendData() {
        var payments = GenerateTestDataUtils.generatePayments();

        journalService.journalize(payments);
        kafkaService.send(payments);
    }

}

