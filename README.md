# Sistema Tinta

## Stack Tecnológica

- **Linguagem:** Java 21
- **Framework:** Spring Boot
- **Segurança:** Spring Security + JWT
- **Banco de Dados:** MySQL 8
- **Mensageria:** ActiveMQ
- **Containerização:** Docker & Docker Compose

---

## Subindo o Projeto

### Pré-requisitos

Antes de iniciar, certifique-se de ter instalado em sua máquina:
- Docker
- Docker Compose

### Executar os Containers

Na raiz do projeto (onde está o arquivo `docker-compose.yml`), execute o comando abaixo para buildar e iniciar os serviços:

```bash
docker compose up --build
