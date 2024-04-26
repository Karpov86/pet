package com.example.demo.mapper;

import com.example.demo.dao.model.Metric;
import com.example.demo.dto.MetricDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MetricMapper {

    MetricMapper INSTANCE = Mappers.getMapper(MetricMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "value", target = "value")
    @Mapping(target = "timestamp", expression = "java(System.currentTimeMillis())")
    Metric metricDtoToMetric(MetricDto metricDto);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "value", target = "value")
    MetricDto metricToMetricDto(Metric metric);

    List<MetricDto> metricsToMetricDtos(List<Metric> metrics);
    List<Metric> metricDtosToMetrics(List<MetricDto> metricDtos);

}
