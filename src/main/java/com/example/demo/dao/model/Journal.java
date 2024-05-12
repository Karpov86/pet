package com.example.demo.dao.model;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
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

    @CreatedDate
    private LocalDateTime createTime;
}
