package com.example.demo.mapper;

import com.example.demo.dao.model.Metric;
import com.example.demo.dto.MetricDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-26T22:30:13+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
public class MetricMapperImpl implements MetricMapper {

    @Override
    public Metric metricDtoToMetric(MetricDto metricDto) {
        if ( metricDto == null ) {
            return null;
        }

        Metric metric = new Metric();

        metric.setName( metricDto.name() );
        if ( metricDto.value() != null ) {
            metric.setValue( metricDto.value() );
        }

        metric.setTimestamp( System.currentTimeMillis() );

        return metric;
    }

    @Override
    public MetricDto metricToMetricDto(Metric metric) {
        if ( metric == null ) {
            return null;
        }

        String name = null;
        Long value = null;

        name = metric.getName();
        value = (long) metric.getValue();

        MetricDto metricDto = new MetricDto( name, value );

        return metricDto;
    }

    @Override
    public List<MetricDto> metricsToMetricDtos(List<Metric> metrics) {
        if ( metrics == null ) {
            return null;
        }

        List<MetricDto> list = new ArrayList<MetricDto>( metrics.size() );
        for ( Metric metric : metrics ) {
            list.add( metricToMetricDto( metric ) );
        }

        return list;
    }

    @Override
    public List<Metric> metricDtosToMetrics(List<MetricDto> metricDtos) {
        if ( metricDtos == null ) {
            return null;
        }

        List<Metric> list = new ArrayList<Metric>( metricDtos.size() );
        for ( MetricDto metricDto : metricDtos ) {
            list.add( metricDtoToMetric( metricDto ) );
        }

        return list;
    }
}
