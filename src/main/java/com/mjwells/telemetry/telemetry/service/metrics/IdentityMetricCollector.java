package com.mjwells.telemetry.telemetry.service.metrics;

import com.mjwells.telemetry.telemetry.builder.SystemMetricsBuilder;
import com.mjwells.telemetry.telemetry.strategy.MetricCollector;
import org.springframework.stereotype.Component;
import oshi.SystemInfo;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class IdentityMetricCollector implements MetricCollector {
    @Override
    public void collect(SystemMetricsBuilder builder) {
        var os = new SystemInfo().getOperatingSystem();
        var cs = new SystemInfo().getHardware().getComputerSystem();

        String bootTime = LocalDateTime.ofInstant(
                Instant.ofEpochSecond(os.getSystemBootTime()),
                ZoneId.systemDefault()
        ).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        builder.getBuilder()
                .setHostname(os.getNetworkParams().getHostName())
                .setLoggedInUser(System.getProperty("user.name"))
                .setFirmware(cs.getFirmware().getVersion())
                .setMotherboard(cs.getBaseboard().getModel())
                .setSerialNumber(cs.getSerialNumber())
                .setSystemBootTime(bootTime)
                .setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}
