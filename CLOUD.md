# CLOUD

## Visão geral

Este projeto já está preparado para execução em contêiner com `Dockerfile` e `docker-compose.yml`.
A aplicação é uma API Spring Boot com:

- Java 21
- Spring Boot 3.5.9
- Maven
- Spring Data JPA
- H2 Database
- Swagger / OpenAPI

## Estado atual para uso em cloud

Hoje a aplicação está configurada para subir em um único contêiner na porta `8080`.
O build da imagem é feito por multi-stage no `Dockerfile`:

1. compila o projeto com Maven e Java 21
2. copia o `.jar` final para uma imagem JRE mais enxuta

O `docker-compose.yml` já publica a porta `8080:8080` e injeta variáveis de ambiente básicas.

## Como subir localmente com Docker

### Docker Compose

```bash
docker compose up --build
```

### Docker puro

```bash
docker build -t lanchonete-management-system .
docker run --rm -p 8080:8080 lanchonete-management-system
```

## Endpoints úteis

Com a aplicação rodando:

- API base: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI docs: `http://localhost:8080/api-docs`
- H2 Console: `http://localhost:8080/h2-console`

## Variáveis de ambiente suportadas

A aplicação já lê as seguintes variáveis em `src/main/resources/application.properties`:

| Variável | Padrão | Uso |
|---|---|---|
| `SERVER_PORT` | `8080` | Porta da aplicação |
| `SPRING_DATASOURCE_URL` | `jdbc:h2:mem:lanchonetedb` | URL do banco |
| `SPRING_DATASOURCE_DRIVER_CLASS_NAME` | `org.h2.Driver` | Driver JDBC |
| `SPRING_DATASOURCE_USERNAME` | `sa` | Usuário do banco |
| `SPRING_DATASOURCE_PASSWORD` | vazio | Senha do banco |
| `SPRING_JPA_HIBERNATE_DDL_AUTO` | `update` | Estratégia de schema |
| `SPRING_JPA_SHOW_SQL` | `true` | Exibição de SQL |
| `SPRING_H2_CONSOLE_ENABLED` | `true` | Liga console H2 |
| `SPRING_H2_CONSOLE_PATH` | `/h2-console` | Caminho do console H2 |

## Observações importantes para deploy em nuvem

### 1. Banco atual é em memória

O projeto usa `H2` em memória por padrão (`jdbc:h2:mem:lanchonetedb`).
Isso significa que os dados são perdidos sempre que o contêiner reinicia.

Para ambiente de cloud/produção, o ideal é trocar para um banco persistente, por exemplo:

- PostgreSQL
- MySQL
- SQL Server

### 2. Console H2 não é indicado para produção

O console H2 está habilitado por padrão. Em ambiente público, o recomendado é desabilitar:

```bash
SPRING_H2_CONSOLE_ENABLED=false
```

### 3. Swagger pode ficar restrito por ambiente

O Swagger está habilitado por padrão. Para produção, vale avaliar restrição por perfil, autenticação ou liberação apenas em ambientes internos.

### 4. Porta compatível com plataformas cloud

A aplicação já respeita `SERVER_PORT`, o que ajuda em plataformas de contêiner que definem a porta por variável de ambiente.

## Exemplo de execução em plataforma cloud baseada em contêiner

Para serviços como Azure Web App for Containers, AWS App Runner, ECS, Render, Railway ou Fly.io, a estratégia mais simples é:

1. gerar a imagem com o `Dockerfile`
2. publicar a imagem em um registry
3. configurar variáveis de ambiente
4. expor a porta `8080` (ou a porta informada em `SERVER_PORT`)

### Exemplo de imagem

```bash
docker build -t lanchonete-management-system:latest .
```

Depois disso, a imagem pode ser enviada para um registry como Docker Hub ou GitHub Container Registry.

## Limitações atuais antes de produção

Antes de considerar este projeto pronto para produção em cloud, é recomendável ajustar:

- banco persistente em vez de H2 em memória
- estratégia de perfis (`dev`, `test`, `prod`)
- desabilitar H2 console em produção
- revisar exposição do Swagger
- adicionar health check, se a plataforma exigir
- revisar o `OpenApiConfig`, que hoje aponta o servidor como `http://localhost:8080`

## Resumo

No estado atual, o projeto já está pronto para:

- buildar com Docker
- rodar localmente em contêiner
- ser publicado em uma plataforma cloud baseada em contêiner

Para produção real, o principal ajuste necessário é substituir o banco em memória por um banco persistente e endurecer as configurações de ambiente.

