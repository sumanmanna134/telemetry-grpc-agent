# 🖥️ Telemetrics

A modular, production-grade telemetry agent built using **Spring Boot**, **gRPC**, and **Java 21**. This service streams **real-time system metrics**—like CPU, memory, disk, JVM, and network details—using `StreamObserver`.

---

## 🚀 Features

- ✅ Real-time streaming via gRPC
- ✅ Modular design using Strategy + Builder patterns
- ✅ CPU metrics (per-core, usage, model, frequency)
- ✅ Memory and disk usage
- ✅ JVM stats: heap, threads, GC
- ✅ Network stats: IPs, MAC, bandwidth, gateway, DNS
- ✅ Active TCP/UDP connections (`netstat`)
- ✅ Logged-in user, hostname, uptime, boot time
- ✅ Designed with Java 21 and Spring Boot 3

---

## 🧱 Tech Stack

- ☕ Java 21
- 🌱 Spring Boot 3.2.x
- 📡 gRPC (LogNet Spring Boot Starter)
- 🔍 OSHI (hardware + OS metrics)
- 🧰 Design Patterns: Strategy & Builder

---

## 📁 Project Structure

