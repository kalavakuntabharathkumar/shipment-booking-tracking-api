# Shipment Booking and Tracking REST API
Java 17 · Spring Boot · Spring Data JPA · PostgreSQL · Flyway · Maven

## Run
Prerequisites: JDK 17+, Maven 3.9+, Docker.
1. Set the local database password in your shell (do not commit it):
   ```bash
   export POSTGRES_PASSWORD='choose-a-local-password'
   export DB_PASSWORD="$POSTGRES_PASSWORD"
   ```
2. `docker compose up -d`
3. `mvn clean test`
4. `mvn spring-boot:run`
5. Base URL: `http://localhost:8080/api/shipments`

## Endpoints
- `POST /api/shipments` — book a shipment.
- `GET /api/shipments?page=0&size=20` — paginated list.
- `GET /api/shipments/{id}` — retrieve shipment.
- `GET /api/shipments/track/{trackingNumber}` — track shipment.
- `PATCH /api/shipments/{id}/status` — update lifecycle state.
- `DELETE /api/shipments/{id}` — cancel shipment.

Example POST body:
```json
{"senderName":"Asha Rao","recipientName":"Ravi Kumar","origin":"Chennai","destination":"Hyderabad","weightKg":2.5}
```
Status body example: `{"status":"PICKED_UP"}`. Invalid transitions return 409, missing records 404, invalid inputs 400.

## Design
Controller handles HTTP; service coordinates booking, pricing and state transitions; repository handles persistence; DTO validation protects the API boundary; centralized exception handling formats errors. Flyway creates the schema and Hibernate validates it.

This is a portfolio starter, not a production deployment. Add authentication, audit history, idempotency, integration tests, observability and secret management before real use. Resume metrics should be claimed only after measuring them in your own environment.
