# Sistema Tinta

## Stack

- Java 21
- Spring Boot
- Spring Security + JWT
- MySQL 8
- ActiveMQ
- Docker
- Docker Compose

---

# Subindo o Projeto

## Pré-requisitos

- Docker
- Docker Compose

---

## Executar os containers

Na raiz do projeto execute:

```bash
docker compose up --build

Para subir em background:

docker compose up -d --build
Serviços
Serviço	Porta
auth-api	8080
historico-api	8081
MySQL	3306
ActiveMQ	61616
ActiveMQ Web	8161
ActiveMQ Web
http://localhost:8161

Usuário:

admin

Senha:

admin
Parar os containers
docker compose down