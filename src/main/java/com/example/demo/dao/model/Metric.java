package com.example.demo.dao.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Metric {

    @Id
    private String id;

    private String name;
    private double value;
    private long timestamp;
}
