package com.example.demo.controller;

import com.example.demo.dao.model.Journal;
import com.example.demo.service.JournalService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/journals")
@RequiredArgsConstructor
@Slf4j
public class JournalController {

    private final JournalService journalService;

    @GetMapping
    @Operation(summary = "Gets all journals")
    public List<Journal> getAllJournals() {
        return journalService.getJournals();
    }

    @GetMapping("/{entityName}")
    public List<Journal> getJournalsByEntityName(@PathVariable String entityName) {
        return journalService.getByEntityName(entityName);
    }

}
