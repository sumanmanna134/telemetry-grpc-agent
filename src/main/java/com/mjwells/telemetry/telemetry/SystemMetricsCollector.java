package com.mjwells.telemetry.telemetry;

import com.example.grpc.system.SystemMetrics;

public interface SystemMetricsCollector {
    SystemMetrics collect();
}
