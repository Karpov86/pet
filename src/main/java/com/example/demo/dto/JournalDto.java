package com.example.demo.dto;

public record JournalDto(
        String entityName,
        String entityId,
        String operationType,
        long timestamp) {

}
