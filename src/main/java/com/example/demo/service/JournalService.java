package com.example.demo.service;

import com.example.demo.dao.model.Journal;
import com.example.demo.dao.model.Payment;
import java.util.List;

public interface JournalService {

    void journalize(List<Payment> payments);

    List<Journal> getJournals();

    List<Journal> getByEntityName(String entityName);
}
