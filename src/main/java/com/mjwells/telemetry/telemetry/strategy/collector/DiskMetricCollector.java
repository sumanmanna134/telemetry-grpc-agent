package com.mjwells.telemetry.telemetry.strategy.collector;

import com.mjwells.telemetry.telemetry.strategy.MetricCollector;
import com.mjwells.telemetry.telemetry.strategy.SystemMetricsBuilder;
import oshi.SystemInfo;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;

public class DiskMetricCollector implements MetricCollector {
    private final FileSystem fs = new SystemInfo().getOperatingSystem().getFileSystem();

    @Override
    public void collect(SystemMetricsBuilder builder) {
        OSFileStore osFileStore = fs.getFileStores().getFirst();
        long total = osFileStore.getTotalSpace();
        long free = osFileStore.getFreeSpace();
        long used = total - free;
        builder.getBuilder()
                .setDiskFree(builder.formatBytes(free))
                .setDiskTotal(builder.formatBytes(total))
                .setDiskUsed(builder.formatBytes(used));

        // Todo
        /* future enhancement: add volume information */


    }
}
