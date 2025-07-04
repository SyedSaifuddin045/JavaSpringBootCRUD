# Task List CRUD Application

A comprehensive CRUD application built with Java Spring Boot and PostgreSQL, featuring RESTful APIs for managing task lists and tasks. The application includes Swagger documentation for easy API exploration and testing.

## 🚀 Features

- **Task Lists Management**: Create, read, update, and delete task lists
- **Tasks Management**: Full CRUD operations for tasks within task lists
- **RESTful API**: Clean REST endpoints following best practices
- **Swagger Documentation**: Interactive API documentation and testing interface
- **Docker Support**: Containerized application with Docker Compose
- **PostgreSQL Database**: Robust relational database for data persistence
- **Environment Configuration**: Configurable via environment variables

## 📋 Table of Contents

- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Environment Variables](#environment-variables)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [API Endpoints](#api-endpoints)
- [Database Schema](#database-schema)
- [Development](#development)
- [Testing](#testing)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)

## 🛠️ Prerequisites

Ensure you have the following installed on your system:

- **Java 17** or higher
- **Maven 3.6+**
- **Docker** and **Docker Compose**
- **Git** (for cloning the repository)

## 📦 Installation

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd <repository-directory>
   ```

2. **Build the project:**
   ```bash
   mvn clean install
   ```

3. **Run tests (optional):**
   ```bash
   mvn test
   ```

## 🔧 Environment Variables

Create a `.env` file in the root directory with the following variables:

```env
# Database Configuration
POSTGRES_USER=task_user
POSTGRES_PASSWORD=task_password
POSTGRES_DB=task_database
POSTGRES_URL=jdbc:postgresql://db:5432/task_database

# Application Configuration
SPRING_PROFILES_ACTIVE=dev
SERVER_PORT=8080

# Swagger Configuration
SPRINGDOC_API_DOCS_ENABLED=true
SPRINGDOC_SWAGGER_UI_ENABLED=true
```

## 🏃 Running the Application

### Using Docker Compose (Recommended)

1. **Start all services:**
   ```bash
   docker-compose up --build
   ```

2. **Run in detached mode:**
   ```bash
   docker-compose up -d --build
   ```

3. **Stop the application:**
   ```bash
   docker-compose down
   ```

### Local Development

1. **Start PostgreSQL locally** (ensure it's running on port 5432)

2. **Update environment variables** to point to your local database

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

## 📖 API Documentation

The application includes comprehensive API documentation using Swagger/OpenAPI 3.

### Accessing Swagger UI

- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs
- **OpenAPI YAML**: http://localhost:8080/v3/api-docs.yaml

### Features of Swagger Integration

- Interactive API testing interface
- Detailed request/response schemas
- Authentication testing capabilities
- Export functionality for API specifications

## 🔗 API Endpoints

### Task Lists Controller

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| `GET` | `/task-lists` | Retrieve all task lists | None |
| `GET` | `/task-lists/{id}` | Get specific task list by ID | None |
| `POST` | `/task-lists/new` | Create a new task list | TaskListDTO |
| `PUT` | `/task-lists/{id}` | Update existing task list | TaskListDTO |
| `DELETE` | `/task-lists/{id}` | Delete task list by ID | None |

### Tasks Controller

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| `GET` | `/task-lists/{taskListId}/tasks` | Get all tasks in a task list | None |
| `GET` | `/task-lists/{taskListId}/tasks/{taskId}` | Get specific task by ID | None |
| `POST` | `/task-lists/{taskListId}/tasks` | Create new task in task list | TaskDTO |
| `PUT` | `/task-lists/{taskListId}/tasks/{taskId}` | Update existing task | TaskDTO |
| `DELETE` | `/task-lists/{taskListId}/tasks/{taskId}` | Delete task by ID | None |

### Response Codes

- `200 OK`: Successful GET, PUT operations
- `201 Created`: Successful POST operations
- `204 No Content`: Successful DELETE operations
- `400 Bad Request`: Invalid request data
- `404 Not Found`: Resource not found
- `500 Internal Server Error`: Server error

## 🗄️ Database Schema

### TaskListDTO
```json
{
  "id": "Long",
  "name": "String",
  "description": "String",
  "createdAt": "LocalDateTime",
  "updatedAt": "LocalDateTime"
}
```

### TaskDTO
```json
{
  "id": "Long",
  "title": "String",
  "description": "String",
  "completed": "Boolean",
  "priority": "String",
  "dueDate": "LocalDate",
  "createdAt": "LocalDateTime",
  "updatedAt": "LocalDateTime",
  "taskListId": "Long"
}
```

## 💻 Development

### Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── com/yourpackage/
│   │       ├── controller/
│   │       ├── dto/
│   │       ├── entity/
│   │       ├── repository/
│   │       └── service/
│   └── resources/
│       ├── application.properties
│       └── application-dev.properties
└── test/
    └── java/
```

### Adding New Features

1. Create entity classes in the `entity` package
2. Create DTOs in the `dto` package
3. Create repositories extending `JpaRepository`
4. Implement service layer with business logic
5. Create REST controllers with appropriate annotations
6. Add Swagger documentation annotations

### Swagger Annotations

Use these annotations to enhance API documentation:

```java
@RestController
@RequestMapping("/api/v1/task-lists")
@Tag(name = "Task Lists", description = "Task List management operations")
public class TaskListController {

    @Operation(summary = "Get all task lists", description = "Retrieve all task lists")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved task lists"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<TaskListDTO>> getAllTaskLists() {
        // Implementation
    }
}
```

## 🧪 Testing

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=TaskListControllerTest

# Run tests with coverage
mvn test jacoco:report
```

### Test Categories

- **Unit Tests**: Test individual components in isolation
- **Integration Tests**: Test component interactions
- **API Tests**: Test REST endpoints

## 🔍 Troubleshooting

### Common Issues

1. **Database Connection Issues**
   - Ensure PostgreSQL is running
   - Check environment variables
   - Verify network connectivity

2. **Port Already in Use**
   ```bash
   # Find process using port 8080
   lsof -i :8080
   # Kill the process
   kill -9 <PID>
   ```

3. **Docker Build Issues**
   ```bash
   # Clean Docker cache
   docker system prune -f
   # Rebuild without cache
   docker-compose build --no-cache
   ```

### Logs

```bash
# View application logs
docker-compose logs app

# Follow logs in real-time
docker-compose logs -f app

# View database logs
docker-compose logs db
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Code Style

- Follow Java naming conventions
- Use meaningful variable and method names
- Add appropriate comments and documentation
- Include unit tests for new features

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙋 Support

For support and questions:

- Create an issue in the GitHub repository
- Check the [Troubleshooting](#troubleshooting) section
- Review the Swagger documentation at http://localhost:8080/swagger-ui/index.html

---

**Built with ❤️ using Java Spring Boot**