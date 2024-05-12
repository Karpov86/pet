package com.example.demo.dao.repo;

import com.example.demo.dao.model.Journal;
import java.util.List;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepository extends MongoRepository<Journal, String> {

    List<Journal> findByEntityName(String entityName);

    @Aggregation(pipeline = {
            "{ '$group': { '_id': '$entityName' } }"
    })
    List<String> findDistinctEntityNames();

}
