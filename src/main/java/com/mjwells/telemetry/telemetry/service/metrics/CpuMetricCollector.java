package com.mjwells.telemetry.telemetry.service.metrics;

import com.mjwells.telemetry.telemetry.strategy.MetricCollector;
import com.mjwells.telemetry.telemetry.builder.SystemMetricsBuilder;
import org.springframework.stereotype.Component;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

import java.util.Arrays;

@Component
public class CpuMetricCollector implements MetricCollector {
    private final CentralProcessor processor = new SystemInfo().getHardware().getProcessor();

    /**
     * store previous ticks for delta computation
     */
    private long[] prevSystemTicks = processor.getSystemCpuLoadTicks();
    private long[][] prevProcTicks = processor.getProcessorCpuLoadTicks();
    @Override
    public void collect(SystemMetricsBuilder builder) {
        double cpuLoad = processor.getSystemCpuLoadBetweenTicks(prevSystemTicks) * 100;
        double[] perCore = processor.getProcessorCpuLoadBetweenTicks(prevProcTicks);

        /* update tick snapshots for next call */

        prevSystemTicks = processor.getSystemCpuLoadTicks();
        prevProcTicks = processor.getProcessorCpuLoadTicks();

        builder.getBuilder()
                .setCpuUsage(String.format("%.2f%%", cpuLoad))
                .setLogicalCpuCount(processor.getLogicalProcessorCount())
                .setPhysicalCpuCount(processor.getPhysicalProcessorCount())
                .addAllPerCoreCpuLoad(Arrays.stream(perCore)
                        .mapToObj(p->String.format("%.2f%%", p*100)).toList()

                )
                .setCpuModel(processor.toString())
                .setCpuFreq(String.format("%.2f GHz", processor.getMaxFreq()/1_000_000_000.0));

    }
}
