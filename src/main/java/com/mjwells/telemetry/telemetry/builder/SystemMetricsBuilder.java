package com.mjwells.telemetry.telemetry.builder;

import com.example.grpc.system.SystemMetrics;

public class SystemMetricsBuilder {
    private final SystemMetrics.Builder builder = SystemMetrics.newBuilder();


    public SystemMetrics.Builder getBuilder(){
        return builder;
    }

    public SystemMetrics build(){
        return builder.build();
    }

    public String formatBytes(long bytes){
        return String.format("%.2f GB", bytes/1_073_741_824.0);
    }
}
