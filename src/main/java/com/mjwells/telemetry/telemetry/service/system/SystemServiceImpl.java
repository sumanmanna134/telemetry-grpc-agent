package com.mjwells.telemetry.telemetry.service.system;

import com.example.grpc.system.Empty;
import com.example.grpc.system.SystemMetrics;
import com.example.grpc.system.SystemServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@GRpcService
public class SystemServiceImpl extends SystemServiceGrpc.SystemServiceImplBase {
    private final SystemMetricsCollector collector = new DefaultSystemMetricsCollector();
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    @Override
    public void streamSystemInfo(Empty request, StreamObserver<SystemMetrics> responseObserver) {
        executorService.scheduleAtFixedRate(()->{
            try{
                SystemMetrics systemMetrics = collector.collect();
                responseObserver.onNext(systemMetrics);
            }catch (Exception e){
                responseObserver.onError(e);
            }
        }, 0, 5, TimeUnit.SECONDS);
    }
}
