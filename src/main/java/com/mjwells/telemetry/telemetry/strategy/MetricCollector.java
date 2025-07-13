package com.mjwells.telemetry.telemetry.strategy;

import com.mjwells.telemetry.telemetry.builder.SystemMetricsBuilder;

public interface MetricCollector {
    void collect(SystemMetricsBuilder builder);
}
