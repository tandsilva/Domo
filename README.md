# 🧠 Projeto Domo

Backend desenvolvido em **Java + Spring Boot**, com persistência orientada a grafos usando **Neo4j**, seguindo arquitetura em camadas e boas práticas de desenvolvimento. Ideal para aplicações que envolvem relacionamentos complexos como avatares, skins, conquistas e status.

---

## 💠 Tecnologias

* Java 21
* Spring Boot
* Spring Data Neo4j
* Docker
* Maven
* Lombok
* Swagger/OpenAPI
* JUnit / Mockito
* Kafka / RabbitMQ (opcional)

---

## 🗂️ Estrutura de Pacotes

```
src/main/java/com/seuusuario/nomedoprojeto/
├── config          # Configurações (Kafka, Neo4j, Swagger, etc.)
├── controller      # Camada REST (APIs)
├── dto             # DTOs de entrada/saída
├── exception       # Tratamento global de erros
├── model           # Entidades (nós/relacionamentos do Neo4j)
├── repository      # Interfaces Spring Data Neo4j
├── service         # Regras de negócio
├── event           # Eventos Kafka/RabbitMQ (se usado)
├── util            # Helpers e validadores
└── NomedoprojetoApplication.java
```

---

## ✨ Como rodar localmente

### Pré-requisitos

* Java 17+
* Maven 3.6+
* Docker (para Neo4j local)

### 1. Clonar o projeto

```bash
git clone https://github.com/seuusuario/domo.git
cd domo
```

### 2. Rodar o Neo4j com Docker

```bash
docker run -d \
  --name neo4j-domo \
  -p7474:7474 -p7687:7687 \
  -e NEO4J_AUTH=neo4j/Educacional1983 \
  neo4j:5.19
```

> Acesse o console em: [http://localhost:7474](http://localhost:7474)

---

### 3. Configurar o `application.properties`

```properties
spring.application.name=Domo
spring.neo4j.uri=bolt://localhost:7687
spring.neo4j.authentication.username=neo4j
spring.neo4j.authentication.password=Educacional1983
```

---

### 4. Rodar a aplicação

```bash
./mvnw spring-boot:run
```

Ou:

```bash
mvn clean package
java -jar target/domo-0.0.1-SNAPSHOT.jar
```

---

### 5. Acessar a API

* API Base: `http://localhost:8080`
* Swagger: `http://localhost:8080/swagger-ui.html`

---

## 🧪 Testes

### Executar todos os testes unitários e de integração:

```bash
./mvnw test
```

> Inclui testes com `MockMvc`, `Mockito` e suporte a `Neo4j TestContainers` (se configurado).

---

## 📚 Endpoints principais (exemplo)

| Método | Rota                | Descrição                  |
| ------ | ------------------- | -------------------------- |
| GET    | `/avatar`           | Lista todos os avatares    |
| POST   | `/avatar`           | Cria um novo avatar        |
| POST   | `/avatar/{id}/skin` | Associa uma skin ao avatar |
| GET    | `/skin`             | Lista skins disponíveis    |

> Mais detalhes no Swagger.

---

## 🔮 Funcionalidades Futuras

* IA de comportamento para avatares
* Sistema de conquistas e missões
* Integração com frontend 3D (Unreal Engine?)
* Gráficos de relacionamento via GraphQL

---

## 👨‍💼 Autor

Desenvolvido por [Thiago Aldrin Marques da Silva](https://github.com/tandsilva)
📧 [tamdsilva@logatti.edu.br](mailto:tamdsilva@logatti.edu.br)
💼 Logatti / Uniamérica

---

## 📋 Licença

Este projeto é licenciado sob a [MIT License](LICENSE).
