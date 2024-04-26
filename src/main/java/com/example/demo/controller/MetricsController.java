package com.example.demo.controller;

import com.example.demo.dao.model.Metric;
import com.example.demo.dao.repo.MetricRepository;
import com.example.demo.dto.MetricDto;
import com.example.demo.mapper.MetricMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/metrics")
@RequiredArgsConstructor
@Slf4j
public class MetricsController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final MetricRepository metricRepository;

    @PostMapping
    public ResponseEntity<String> receiveMetrics(@RequestBody MetricDto metricDto) {
        try {
            var metric = MetricMapper.INSTANCE.metricDtoToMetric(metricDto);
            metricRepository.save(metric);
            kafkaTemplate.send("metrics", metric.toString());
            return ResponseEntity.ok("Data received");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing request");
        }
    }

    @GetMapping
    public ResponseEntity<List<MetricDto>> getMetrics() {
        var metrics = metricRepository.findAll();
        var metricDtos = MetricMapper.INSTANCE.metricsToMetricDtos(metrics);
        return ResponseEntity.ok(metricDtos);
    }

}
