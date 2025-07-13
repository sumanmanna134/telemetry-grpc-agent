# Telemetrics

A production-grade, modular telemetry agent for streaming real-time system metrics, built with **Spring Boot 3.2.x**, **gRPC**, and **Java 21**. This service delivers comprehensive system insights, including CPU, memory, disk, JVM, and network metrics, using a robust gRPC `StreamObserver` for real-time data delivery.

## 🚀 Features

- **Real-Time Streaming**: Streams metrics using gRPC for low-latency, bidirectional communication.
- **Modular Architecture**: Leverages **Strategy** and **Builder** design patterns for extensibility and maintainability.
- **Comprehensive Metrics**:
  - **CPU**: Per-core usage, model, frequency, and load averages.
  - **Memory**: System and process-level memory usage, including swap.
  - **Disk**: Usage, read/write rates, and partition details.
  - **JVM**: Heap usage, thread count, garbage collection stats, and runtime details.
  - **Network**: IP addresses, MAC addresses, bandwidth, gateway, DNS, and active TCP/UDP connections (netstat).
  - **System**: Logged-in users, hostname, system uptime, and boot time.
- **Production-Ready**: Built with Java 21 and Spring Boot 3.2.x for performance and scalability.
- **Extensible**: Easy to add new metric collectors via modular design.

## 🧱 Tech Stack

- **Java 21**: Modern Java features for performance and developer productivity.
- **Spring Boot 3.2.x**: Simplified configuration, dependency management, and production-ready features.
- **gRPC (LogNet Spring Boot Starter)**: High-performance, real-time streaming of telemetry data.
- **OSHI**: Cross-platform library for hardware and OS metrics.
- **Design Patterns**:
  - **Strategy**: Flexible metric collection logic.
  - **Builder**: Streamlined construction of telemetry payloads.

[//]: # (## 📁 Project Structure)

[//]: # ()
[//]: # (```)

[//]: # (telemetry/)

[//]: # (├── src/)

[//]: # (│   ├── main/)

[//]: # (│   │   ├── java/)

[//]: # (│   │   │   ├── config/          # Spring configuration and beans)

[//]: # (│   │   │   ├── grpc/            # gRPC service definitions and implementations)

[//]: # (│   │   │   ├── metrics/         # Metric collectors &#40;CPU, Memory, JVM, etc.&#41;)

[//]: # (│   │   │   ├── model/           # Data models for telemetry payloads)

[//]: # (│   │   │   ├── service/         # Business logic and strategy implementations)

[//]: # (│   │   │   └── util/            # Utility classes and builders)

[//]: # (│   │   └── resources/)

[//]: # (│   │       ├── proto/           # gRPC .proto files)

[//]: # (│   │       └── application.yml  # Spring Boot configuration)

[//]: # (│   └── test/                    # Unit and integration tests)

[//]: # (├── pom.xml                      # Maven build configuration)

[//]: # (└── README.md                    # Project documentation)

[//]: # (```)

## Sample Stream Response
```
{
    "perCoreCpuLoad": [
        "25.96%",
        "23.29%",
        "21.29%",
        "18.64%",
        "22.09%",
        "11.42%",
        "7.00%",
        "4.99%"
    ],
    "networkInterfaces": [],
    "dnsServers": [
        "2409:40e1:11af:2405:9ed4:a6ff:fe5a:9f3d",
        "192.168.31.1"
    ],
    "activeConnections": [
        "tcp6       0      0  2409:40e1:11af:2.62499 2606:4700:7::a29.443   ESTABLISHED",
        "tcp4       0      0  127.0.0.1.6565         127.0.0.1.62498        ESTABLISHED",
        "tcp4       0      0  127.0.0.1.62498        127.0.0.1.6565         ESTABLISHED",
        "tcp4       0      0  127.0.0.1.59361        127.0.0.1.62496        ESTABLISHED",
        "tcp4       0      0  127.0.0.1.62496        127.0.0.1.59361        ESTABLISHED",
        "tcp4       0      0  127.0.0.1.62494        127.0.0.1.62495        ESTABLISHED",
        "tcp4       0      0  127.0.0.1.62495        127.0.0.1.62494        ESTABLISHED",
        "tcp4       0      0  127.0.0.1.62012        127.0.0.1.62490        ESTABLISHED",
        "tcp4       0      0  127.0.0.1.62490        127.0.0.1.62012        ESTABLISHED",
        "tcp4       0      0  127.0.0.1.62012        127.0.0.1.62476        ESTABLISHED",
        "tcp4       0      0  127.0.0.1.62476        127.0.0.1.62012        ESTABLISHED",
        "tcp4       0      0  192.168.31.201.62046   140.82.113.25.443      ESTABLISHED",
        "tcp4       0      0  127.0.0.1.24265        127.0.0.1.62014        ESTABLISHED",
        "tcp4       0      0  127.0.0.1.62014        127.0.0.1.24265        ESTABLISHED",
        "tcp6       0      0  2409:40e1:11af:2.61221 2606:4700:3030::.443   ESTABLISHED",
        "tcp4       0      0  192.168.31.201.61219   35.165.165.7.443       ESTABLISHED",
        "tcp4       0      0  192.168.31.201.61213   13.234.246.132.443     ESTABLISHED",
        "tcp4       0      0  192.168.31.201.61208   35.174.127.31.443      ESTABLISHED",
        "tcp6       0      0  fe80::18ee:4f8:5.61201 fe80::801:4af8:a.58884 ESTABLISHED",
        "tcp6       0      0  2409:40e1:11af:2.61203 2404:6800:4003:c.5228  ESTABLISHED"
    ],
    "cpuUsage": "16.82%",
    "logicalCpuCount": 8,
    "physicalCpuCount": 8,
    "memoryUsed": "6.52 GB",
    "memoryAvailable": "1.48 GB",
    "memoryTotal": "8.00 GB",
    "diskUsed": "219.48 GB",
    "diskFree": "8.79 GB",
    "diskTotal": "228.27 GB",
    "osName": "macOS",
    "osVersion": "15.5",
    "osArch": "aarch64",
    "jvmUptime": "23217 ms",
    "jvmThreadCount": 19,
    "timestamp": "2025-07-13T16:03:43.878319",
    "cpuModel": "Apple M1\n 1 physical CPU package(s)\n 8 physical CPU core(s) (4 performance + 4 efficiency)\n 8 logical CPU(s)\nIdentifier: Apple Inc. Family 0x1b588bb3 Model 0 Stepping 0\nProcessorID: 0100000c1b588bb3\nMicroarchitecture: ARM64 SoC: Firestorm + Icestorm",
    "cpuFreq": "3.20 GHz",
    "motherboard": "MGN63",
    "firmware": "J313",
    "serialNumber": "C02G5D7VQ6L4",
    "ipv4": "",
    "ipv6": "feXX:0:0:0:20XX:Xdff:XeeX:XaXX",
    "macAddress": "XXXXXX",
    "hostname": "MacBookAir.lan",
    "jvmHeapUsed": "0.04 GB",
    "jvmHeapMax": "2.00 GB",
    "gcCount": 6,
    "gcTime": "18",
    "networkBytesSent": "0.00 GB",
    "networkBytesRecv": "0.00 GB",
    "loggedInUser": "sumanmanna",
    "systemBootTime": "2025-07-08T09:02:00",
    "defaultGateway": "192.168.31.1"
}
```
## 🛠️ Getting Started

### Prerequisites
- **Java 21** JDK
- **Maven** 3.8.x or higher
- **gRPC** client (for testing, e.g., BloomRPC or grpcurl)
- Docker (optional, for containerized deployment)

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/your-org/telemetrics.git
   cd telemetrics
   ```
2. Build the project:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

### Configuration
- Configure gRPC server port and other settings in `src/main/resources/application.yml`.
- Example:
  ```yaml
  grpc:
    server:
      port: 9090
  spring:
    application:
      name: telemetrics
  ```

### Usage
- Connect to the gRPC server at `localhost:9090` using a gRPC client.
- Subscribe to the `StreamMetrics` endpoint to receive real-time telemetry data.
- Metrics are streamed as structured payloads, defined in `src/main/resources/proto/telemetry.proto`.

## 📊 Example Metrics Payload
```protobuf
message TelemetryData {
  CpuMetrics cpu = 1;
  MemoryMetrics memory = 2;
  DiskMetrics disk = 3;
  JvmMetrics jvm = 4;
  NetworkMetrics network = 5;
  SystemMetrics system = 6;
}
```

## 🧪 Testing
Run unit and integration tests:
```bash
mvn test
```

## 🚀 Deployment
- **Docker**:
  ```bash
  docker build -t telemetrics:latest .
  docker run -p 9090:9090 telemetrics:latest
  ```
- **Kubernetes**: See `k8s/deployment.yaml` for a sample deployment configuration.

## 📜 License
This project is licensed under the MIT License. See the `LICENSE` file for details.

## 🤝 Contributing
Contributions are welcome! Please submit a pull request or open an issue for bug reports and feature requests.

## 📬 Contact
For support or inquiries, reach out to [your-email@domain.com](mailto:your-email@domain.com).