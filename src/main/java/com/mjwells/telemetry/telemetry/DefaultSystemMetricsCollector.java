package com.mjwells.telemetry.telemetry;

import com.example.grpc.system.SystemMetrics;
import com.mjwells.telemetry.telemetry.strategy.collector.CpuMetricCollector;
import com.mjwells.telemetry.telemetry.strategy.collector.MemoryMetricCollector;
import com.mjwells.telemetry.telemetry.strategy.MetricCollector;
import com.mjwells.telemetry.telemetry.strategy.SystemMetricsBuilder;

import java.util.List;

public class DefaultSystemMetricsCollector implements SystemMetricsCollector {
    private final List<MetricCollector> collectors = List.of(
            new CpuMetricCollector(),
            new MemoryMetricCollector()
    );

    @Override
    public SystemMetrics collect() {
        SystemMetricsBuilder builder = new SystemMetricsBuilder();
        collectors.forEach(c-> c.collect(builder));
        return builder.build();
    }
}
