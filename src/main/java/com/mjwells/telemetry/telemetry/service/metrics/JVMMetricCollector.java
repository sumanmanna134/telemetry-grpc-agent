package com.mjwells.telemetry.telemetry.service.metrics;

import com.mjwells.telemetry.telemetry.builder.SystemMetricsBuilder;
import com.mjwells.telemetry.telemetry.strategy.MetricCollector;
import org.springframework.stereotype.Component;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.ThreadMXBean;

@Component
public class JVMMetricCollector implements MetricCollector {
    @Override
    public void collect(SystemMetricsBuilder builder) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

        long uptime = ManagementFactory.getRuntimeMXBean().getUptime();

        long heapUsed = memoryMXBean.getHeapMemoryUsage().getUsed();
        long heapMax = memoryMXBean.getHeapMemoryUsage().getMax();



        long gcCount = ManagementFactory.getGarbageCollectorMXBeans()
                .stream().mapToLong(GarbageCollectorMXBean::getCollectionCount).sum();

        long gcTime = ManagementFactory.getGarbageCollectorMXBeans()
                .stream().mapToLong(GarbageCollectorMXBean::getCollectionTime).sum();

        builder.getBuilder()
                .setJvmUptime(uptime + " ms")
                .setJvmThreadCount(threadMXBean.getThreadCount())
                .setJvmHeapUsed(builder.formatBytes(heapUsed))
                .setJvmHeapMax(builder.formatBytes(heapMax))
                .setGcCount((int)gcCount)
                .setGcTime(gcTime);

    }
}
