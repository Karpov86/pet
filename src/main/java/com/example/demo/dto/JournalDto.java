package com.example.demo.dto;

import java.time.LocalDateTime;

public record JournalDto(
        String entityName,
        String entityId,
        String operationType,
        LocalDateTime createTime) {

}
