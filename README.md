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