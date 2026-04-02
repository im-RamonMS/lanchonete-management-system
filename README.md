# Lanchonete Management System

## Rodando com Docker

### Build e run com Docker Compose

```bash
docker compose up --build
```

A API sobe em `http://localhost:8080`.

### Build e run direto com Docker

```bash
docker build -t lanchonete-management-system .
docker run --rm -p 8080:8080 lanchonete-management-system
```

Swagger UI: `http://localhost:8080/swagger-ui.html`
API docs: `http://localhost:8080/api-docs`

