package com.example.demo.service.impl;

import com.example.demo.dao.model.Journal;
import com.example.demo.dao.model.Payment;
import com.example.demo.dao.repo.JournalRepository;
import com.example.demo.service.JournalService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerJournalService implements JournalService {

    private static final String CUSTOMER = "Customer";
    private static final String PAYMENT = "Payment";

    private final JournalRepository journalRepository;

    @Override
    public void journalize(List<Payment> payments) {
        var journals = payments.stream()
                .map(p -> {
                    var journal = new Journal();
                    journal.setEntityName(CUSTOMER);
                    journal.setEntityId(p.getCustomerId());
                    journal.setOperationType(PAYMENT);

                    return journal;
                })
                .toList();

        journalRepository.saveAll(journals);
        log.debug("journaled {} journals", journals.size());
    }

    @Override
    public List<Journal> getByEntityName(String entityName) {
        return journalRepository.findByEntityName(entityName);
    }

    @Override
    public List<Journal> getJournals() {
        return journalRepository.findAll();
    }
}
