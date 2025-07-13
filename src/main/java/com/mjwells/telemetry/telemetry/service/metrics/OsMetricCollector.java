package com.mjwells.telemetry.telemetry.service.metrics;

import com.mjwells.telemetry.telemetry.builder.SystemMetricsBuilder;
import com.mjwells.telemetry.telemetry.strategy.MetricCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import oshi.SystemInfo;
import oshi.software.os.ApplicationInfo;
import oshi.software.os.OperatingSystem;

@Component
public class OsMetricCollector implements MetricCollector{
    private static final Logger log = LoggerFactory.getLogger(OsMetricCollector.class);
    private final OperatingSystem os = new SystemInfo().getOperatingSystem();


    @Override
    public void collect(SystemMetricsBuilder builder) {
        builder.getBuilder()
                .setOsName(os.getFamily())
                .setOsVersion(os.getVersionInfo().getVersion())
                .setOsArch(System.getProperty("os.arch"));

    }
}
