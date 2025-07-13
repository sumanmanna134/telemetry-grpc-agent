package com.mjwells.telemetry.telemetry.service.system;

import com.example.grpc.system.SystemMetrics;
import com.mjwells.telemetry.telemetry.service.metrics.*;
import com.mjwells.telemetry.telemetry.strategy.MetricCollector;
import com.mjwells.telemetry.telemetry.builder.SystemMetricsBuilder;

import java.util.List;

public class DefaultSystemMetricsCollector implements SystemMetricsCollector {
    private final List<MetricCollector> collectors = List.of(
            new CpuMetricCollector(),
            new MemoryMetricCollector(),
            new AdvancedNetworkMetricCollector(),
            new IdentityMetricCollector(),
            new JVMMetricCollector(),
            new DiskMetricCollector(),
            new BasicNetworkMetricCollector(),
            new OsMetricCollector()
    );

    @Override
    public SystemMetrics collect() {
        SystemMetricsBuilder builder = new SystemMetricsBuilder();
        collectors.forEach(c-> c.collect(builder));
        return builder.build();
    }
}
