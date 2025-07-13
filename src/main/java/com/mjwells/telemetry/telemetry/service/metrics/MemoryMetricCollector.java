package com.mjwells.telemetry.telemetry.service.metrics;

import com.mjwells.telemetry.telemetry.strategy.MetricCollector;
import com.mjwells.telemetry.telemetry.builder.SystemMetricsBuilder;
import org.springframework.stereotype.Component;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;

@Component
public class MemoryMetricCollector implements MetricCollector {

    private final GlobalMemory memory = new SystemInfo().getHardware().getMemory();

    @Override
    public void collect(SystemMetricsBuilder builder) {
        long total = memory.getTotal();
        long available = memory.getAvailable();
        long used = total - available;

        builder.getBuilder()
                .setMemoryTotal(builder.formatBytes(total))
                .setMemoryAvailable(builder.formatBytes(available))
                .setMemoryUsed(builder.formatBytes(used));

    }
}
