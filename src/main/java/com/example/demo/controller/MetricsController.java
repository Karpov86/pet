package com.example.demo.controller;

import com.example.demo.dao.model.Metric;
import com.example.demo.dao.repo.MetricRepository;
import com.example.demo.dto.MetricDto;
import com.example.demo.mapper.MetricMapper;
import jakarta.validation.constraints.Min;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/metrics")
@RequiredArgsConstructor
@Slf4j
public class MetricsController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final MetricRepository metricRepository;

    @PostMapping
    public ResponseEntity<String> receiveMetric(@RequestBody MetricDto metricDto) {
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

    @PostMapping("/random")
    public ResponseEntity<String> receiveRandomMetrics(@RequestParam Integer amount) {
        var metrics = generateMetrics(amount);
        metricRepository.saveAll(metrics);

        metrics.forEach(metric -> kafkaTemplate.send("metrics", metric.toString()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing request");
    }

    @GetMapping
    public ResponseEntity<List<MetricDto>> getMetrics() {
        var metrics = metricRepository.findAll();
        var metricDtos = MetricMapper.INSTANCE.metricsToMetricDtos(metrics);
        return ResponseEntity.ok(metricDtos);
    }

    private List<Metric> generateMetrics(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> {
                    var metric = new Metric();
                    metric.setName("metric" + i);
                    metric.setTimestamp(System.currentTimeMillis());
                    metric.setValue(ThreadLocalRandom.current().nextDouble(0, 100500));
                    return metric;
                })
                .toList();
    }

}

