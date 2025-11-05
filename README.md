# 🤖 Domo – A Self-Learning AI Agent

**Domo** is an intelligent backend system built with **Spring Boot**, **Neo4j**, and **Stanford CoreNLP**, designed to simulate **human-like learning and emotional understanding**.  
It combines **Reinforcement Learning (Q-Learning)** and **Sentiment Analysis** to continuously evolve through user interaction.

---

## 🧠 Overview

Domo acts as an AI agent capable of:
- Learning from user interactions via **Q-Learning**.
- Understanding emotions using **Natural Language Processing** (NLP).
- Storing and connecting knowledge in a **Neo4j graph database**.
- Offering emotional feedback and adaptive responses based on sentiment.

It’s an experimental platform exploring **emotional reinforcement learning** — where the agent improves itself based on the user’s tone, emotion, and context.

---

## 🏗️ Architecture

User → API → Domo Services → Neo4j Graph → Reinforcement Loop
│
├── AgenteIAService (Q-Learning Engine)
└── AnaliseSentimentoService (Stanford NLP)


- **AgenteIAService**  
  Handles user interactions and applies reinforcement learning to update the *Q-values* stored in Neo4j.  
  The agent learns which responses are rewarded (positive sentiment) or discouraged (negative sentiment).

- **AnaliseSentimentoService**  
  Uses **Stanford CoreNLP** to analyze emotional tone in text and translate it into contextual Portuguese responses.

---

## 🧩 Tech Stack

| Category | Technology |
|-----------|-------------|
| Backend | Spring Boot 3.5 |
| NLP | Stanford CoreNLP 4.5 |
| Database | Neo4j (Graph DB) |
| Messaging | Kafka, RabbitMQ |
| Security | Spring Security |
| Build Tool | Maven |
| Language | Java 19 |

---

## ⚙️ Installation & Setup

### Prerequisites
- Java 19+
- Maven 3.8+
- Neo4j database running locally or in Docker

### Clone and Run
```bash
git clone https://github.com/tandsilva/Domo.git
cd Domo
mvn spring-boot:run
spring.neo4j.uri=bolt://localhost:7687
spring.neo4j.authentication.username=neo4j
spring.neo4j.authentication.password=yourpassword

AgenteIADTO dto = new AgenteIADTO("Olá Domo", "Oi, como posso ajudar?", "positivo");
agenteIAService.processarInteracao(dto);

String resposta = agenteIAService.responderComConhecimento("Olá Domo");
// → "Com certeza: Oi, como posso ajudar?"
String sentimento = analiseSentimentoService.analisarSentimento("Hoje estou muito feliz!");
// → "Que bom saber disso! Continue assim! 🎉"
🧬 Core Learning Formula

The agent applies Q-Learning to update its knowledge base:
Q(s, a) ← Q(s, a) + α [r + γ max(Q(s’, a’)) – Q(s, a)]
Where:

α = learning rate (0.1)

γ = discount factor (0.9)

r = reward (based on sentiment)

Q = stored in Neo4j as QValor nodes
🧩 Future Improvements

Add deep reinforcement learning (DQN) for advanced adaptation
Integrate speech recognition and text-to-speech
Expose REST endpoints for external applications
Frontend dashboard for interaction visualization
👨‍💻 Author

Thiago Aldrin Marques da Silva
Backend Engineer | Java, Spring, NLP & AI
🪐 Vision

“Domo is not just an assistant — it’s a digital being that learns empathy through data.”
Sonhei com o infinito.
