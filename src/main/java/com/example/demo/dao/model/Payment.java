package com.example.demo.dao.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Payment {

    @Id
    private String id;

    private String customerId;
    private double amount;
    private long timestamp;
}
