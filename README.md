# Inquiry Management System – Server & Client

## Overview

A **distributed, real‑time inquiry management platform** written in pure Java.  
It consists of two modules that communicate over sockets:

Key design highlights:

* Pure Java 11+
* Multithreading for concurrent queue management
* OOP principles with **Singleton** pattern where appropriate
* Robust edge‑case handling for optimization and maintainability
* Simple **socket** protocol between client and server

---

## Benefits
* Centralised oversight of all customer interactions  
* Live status tracking: **open → handled / canceled → archived**  
* Quick responses by inquiry type (request / question / complaint)  
* Smart filtering & reporting (type, status, date, agent, …)  
* Optional *personal agent* that follows an inquiry end‑to‑end

---

## Requirements
* **Java 11** or higher
* **Maven** (or another build tool of your choice)
* Runtime permissions for reading/writing data files (agent persistence)

---

## Getting Started

```bash
git clone https://github.com/your‑org/inquiry-management-server.git
cd inquiry-management-server
```

### Build & Run the Server
```bash
cd server
mvn clean package
java -jar target/server‑side‑project.jar
```

### Build & Run the Client
Open a new terminal window:

```bash
cd client
mvn clean package
java -jar target/client‑side‑project.jar
```

The client will connect to the server via sockets on the default host/port
(configurable in `config.properties`).

---

## Usage Flow

1. **Open inquiry** – customer opens a Request / Question / Complaint.  
2. **Queue & assignment** – server queues the inquiry and auto‑assigns an available agent (if enabled).  
3. **Handle / cancel** – agent resolves or cancels; server updates status.  
4. **Archive** – resolved inquiries are archived for audit & reporting.

---

## Server Features
* Create, cancel, handle, archive, and list inquiries
* Agent management (add, remove, save/load from file)
* Auto‑assignment & “My inquiries” per agent
* Statistics: inquiry volume per period
* Background simulation of inquiry handling
* Manager view for agent workload transparency

## Client Features
* Open new inquiries from CLI
* List inquiries with live status
* Filter by type or status
* View personal workload (agents)

---

## Sample Commands (Client CLI)
```
1. Open new inquiry
2. View all inquiries
3. View my inquiries      # agent mode
4. Cancel inquiry
5. Exit
```

---
