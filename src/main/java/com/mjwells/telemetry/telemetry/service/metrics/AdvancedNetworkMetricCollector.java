package com.mjwells.telemetry.telemetry.service.metrics;

import com.mjwells.telemetry.telemetry.builder.SystemMetricsBuilder;
import com.mjwells.telemetry.telemetry.strategy.MetricCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import oshi.SystemInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class AdvancedNetworkMetricCollector implements MetricCollector {
    private static final Logger log = LoggerFactory.getLogger(AdvancedNetworkMetricCollector.class);

    @Override
    public void collect(SystemMetricsBuilder builder) {
        var params = new SystemInfo().getOperatingSystem().getNetworkParams();
        List<String> activeConnections = new ArrayList<>();
        try{
            Process process = new ProcessBuilder("netstat","-an").start();
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))){
                activeConnections = reader.lines()
                        .filter(line-> line.contains("ESTABLISHED") || line.contains("UDP"))
                        .limit(20)
                        .toList();
            }


        }catch (Exception e){
            activeConnections.add("Error: "+e.getMessage());
        }

        builder.getBuilder()
                .setDefaultGateway(params.getIpv4DefaultGateway())
                .addAllDnsServers(Arrays.stream(params.getDnsServers()).toList())
                .addAllActiveConnections(activeConnections);
    }
}
