# Ships REST API (Spring Boot + H2)

Simple CRUD API for managing ships, with validation and Basic Auth on write operations. Uses H2 in-memory DB and Spring Data JPA.

## Requirements
- Java 17+
- Maven 3.8+

## Run
```bash
mvn spring-boot:run
```
Server: http://localhost:8080

- H2 console: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:shipsdb`)
- Basic Auth (for POST/PUT/DELETE): username `admin`, password `secret123`

## Endpoints
- GET `/ships` — list ships
- POST `/ships` — create ship (auth required)
- GET `/ships/{id}` — get ship by id
- PUT `/ships/{id}` — update ship (auth required)
- DELETE `/ships/{id}` — delete ship (auth required)

### Examples
Create ship
```http
POST /ships
Authorization: Basic admin:secret123
Content-Type: application/json

{
  "name": "Vikram",
  "email": "vikram@gmail.com"
}
```
201 Created with `Location: /ships/{id}`

Validation error
```json
{
  "status": 400,
  "errors": { "name": "name is required" }
}
```

Not found
```json
{
  "status": 404,
  "message": "Ship not found"
}
```

## Postman
Collection at `postman/ShipsAPI.postman_collection.json` with variables:
- `baseUrl`: `http://localhost:8080`
- `username`: `admin`
- `password`: `secret123`

Includes tests for 200/201/404 and update/delete flows.

## Build
```bash
mvn -DskipTests package
```

## Notes
- DB is in-memory; data resets on restart.
- GET endpoints are public; write endpoints require Basic Auth.
