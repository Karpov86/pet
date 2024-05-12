package com.example.demo.service;

import com.example.demo.dao.model.Payment;
import java.util.List;

public interface JournalService {

    void journalize(List<Payment> payments);

}
