package com.mjwells.telemetry.telemetry.service.system;

import com.example.grpc.system.SystemMetrics;

interface SystemMetricsCollector {
    SystemMetrics collect();
}
