# Projeto Tintas

## Stack

- **Java 21**
- **Spring Boot**
- **Spring Security + JWT**
- **Spring JMS**
- **ActiveMQ**
- **MySQL 8**
- **Docker + Docker Compose**

---

## Como subir o projeto

### 1. Gerar os arquivos `.jar`

Execute o comando abaixo em cada API:

#### Auth API

```bash
cd tinta-auth/auth-api
mvn clean package -DskipTests
```

### Histórico API

```bash
cd tinta-core/historico-api
mvn clean package -DskipTests
```

### 2. Voltar para a raiz do projeto

```bash
cd ../..
```

Ou volte até a pasta onde está o arquivo docker-compose.yml

### 3. Subir os containers

```bash
docker compose up --build
```

## Serviços

```
| Serviço              | Porta  |
|----------------------|--------|
| Auth API             | 8080   |
| Histórico API        | 8081   |
| MySQL                | 3306   |
| ActiveMQ             | 61616  |
| Painel ActiveMQ      | 8161   |
```

## Acessar ActiveMQ

- **Usuário:** `admin`
- **Senha:** `admin`
- **URL:** [http://localhost:8161](http://localhost:8161)



