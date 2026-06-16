# Horse Racing Backend

Spring Boot 3.x + JWT + MySQL REST API for Horse Racing Management System

## Project Setup

### Prerequisites
- Java 17+
- Maven 3.8+
- MySQL 8.0+
- IDE: IntelliJ IDEA or VS Code

### Database Setup

1. Create database:
```sql
CREATE DATABASE horse_racing_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. Update `application.yml` with your MySQL credentials

### Running the Application

```bash
# Build
mvn clean install

# Run
mvn spring-boot:run
```

Server runs on `http://localhost:8080`

### API Documentation
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- API Docs: `http://localhost:8080/api-docs`

## Architecture

- **Controller**: REST endpoints
- **Service**: Business logic
- **Repository**: Data access layer
- **Entity**: JPA entities
- **DTO**: Data transfer objects
- **Storage**: File storage abstraction (local/S3/MinIO)
- **Security**: JWT authentication & authorization
- **WebSocket**: Real-time notifications

## Key Endpoints

### Auth
- `POST /api/v1/auth/login`
- `POST /api/v1/auth/register`
- `POST /api/v1/auth/refresh-token`
- `POST /api/v1/auth/logout`
- `GET /api/v1/auth/me`

### Horses
- `GET /api/v1/horses`
- `POST /api/v1/horses`
- `GET /api/v1/horses/{id}`
- `PUT /api/v1/horses/{id}`
- `PATCH /api/v1/horses/{id}/approve`

### Races
- `GET /api/v1/races`
- `POST /api/v1/races`
- `GET /api/v1/races/{id}`
- `PATCH /api/v1/races/{id}/publish`

## WebSocket

Connect to `ws://localhost:8080/ws` with token in headers:

```javascript
const client = new Client({
  webSocketFactory: () => new SockJS('http://localhost:8080/ws'),
  connectHeaders: { Authorization: `Bearer ${token}` },
});
```

## Testing

```bash
mvn test
```

## Environment Variables

Create `.env` file or update `application.yml`:

```yml
jwt:
  secret: your-256-bit-secret-key
  access-token-expiration: 3600000
  refresh-token-expiration: 604800000

file:
  upload-dir: ./uploads
  storage-provider: local  # or s3, minio
```