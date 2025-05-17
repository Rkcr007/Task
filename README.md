# RestAssured BDD API Testing Project

This project demonstrates API testing using RestAssured and Cucumber (BDD) for the Inventory API.

## Prerequisites
- Java 8 or higher
- Maven 3.x
- The Inventory API running at `http://localhost:3100/api`

## How to Build

```
mvn clean compile
```

## How to Run Tests

```
mvn test
```

## Project Structure

- `src/test/resources/features/` - Gherkin feature files
- `src/test/java/` - Step definitions and test runners
- `pom.xml` - Maven dependencies and plugins

## Adding/Modifying Tests
- Add new scenarios to the `.feature` files in `src/test/resources/features/`.
- Implement step definitions in Java under `src/test/java/com/example/api/`.

## Example API Endpoints
- `GET /api/inventory` - Get all menu items
- `GET /api/inventory/filter?id=3` - Filter menu item by id
- `POST /api/inventory/add` - Add a new menu item

## Notes
- Ensure the API server is running and accessible at `http://localhost:3100/api` before running the tests. 
