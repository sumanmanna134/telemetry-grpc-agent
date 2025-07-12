package com.mjwells.telemetry.telemetry.strategy;

public interface MetricCollector {
    void collect(SystemMetricsBuilder builder);
}
