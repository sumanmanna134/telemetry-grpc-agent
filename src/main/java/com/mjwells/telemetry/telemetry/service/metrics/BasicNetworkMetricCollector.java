package com.mjwells.telemetry.telemetry.service.metrics;

import com.mjwells.telemetry.telemetry.builder.SystemMetricsBuilder;
import com.mjwells.telemetry.telemetry.strategy.MetricCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import oshi.SystemInfo;
import oshi.hardware.NetworkIF;

@Component
public class BasicNetworkMetricCollector implements MetricCollector {
    private static final Logger log = LoggerFactory.getLogger(BasicNetworkMetricCollector.class);

    @Override
    public void collect(SystemMetricsBuilder builder) {
        NetworkIF nic = new SystemInfo().getHardware().getNetworkIFs().getFirst();

        nic.updateAttributes();

        builder.getBuilder()
                .setIpv4(nic.getIPv4addr().length>0? nic.getIPv4addr()[0]:"")
                .setIpv6(nic.getIPv6addr().length>0 ? nic.getIPv6addr()[0]:"")
                .setMacAddress(nic.getMacaddr())
                .setNetworkBytesSent(builder.formatBytes(nic.getBytesSent()))
                .setNetworkBytesRecv(builder.formatBytes(nic.getBytesRecv()));
    }
}
