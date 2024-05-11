package com.example.demo.dao.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Journal {

    @Id
    private String id;

    private String entityName;
    private String entityId;
    private String operationType;
    private long timestamp;
}
