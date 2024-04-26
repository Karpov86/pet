package com.example.demo.dao.repo;

import com.example.demo.dao.model.Metric;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MetricRepository extends MongoRepository<Metric, String> {

}
