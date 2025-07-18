# 📘 Student Dashboard Application

A Spring Boot RESTful web service to manage university or school courses. This controller handles course creation, retrieval, update, deletion, and pagination-based listing.

---

## Core Features

- Manage students and courses
- RESTful endpoints with full CRUD support
- Pagination with `Pageable` support
- Global exception handling for consistent error responses
- In-memory H2 database for easy development and testing
- Swagger UI for interactive API documentation

---

## 📦 Technologies Used

- Java 21
- Spring Boot 3.5.2
- Spring Web
- Spring Data (Pagination)
- Jakarta Validation
- Lombok

---

## 📂 Project Structure

## Course Service:

### Features

- Create a new course
- Retrieve course details by ID
- Update course information
- Delete a course
- List all courses with pagination

#### Sample request for listing all courses using pagination:

Query request:
```
GET /courses?page=0&size=10
```
Sample response:
```
{
    "content": [
        {
            "id": 1,
            "title": "Digital Logic Design",
            "capacity": 70,
            "prerequisiteIds": []
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 1,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "unpaged": false,
        "paged": true
    },
    "last": false,
    "totalElements": 5,
    "totalPages": 5,
    "size": 1,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "numberOfElements": 1,
    "first": true,
    "empty": false
}
```
All other end points of course service can be accessed in swagger API.

---

## Student Service:

### Features

- Create a new student
- Retrieve student details by ID
- Update student information
- Delete a student
- List all students with pagination

#### Sample request for listing all students using pagination:

Query request:
```
GET /students?page=0&size=10
```
Sample response:
```
{
    "content": [
        {
            "id": 1,
            "name": "Coma",
            "email": "coma@gmail.com"
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 1,
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": false,
    "totalPages": 4,
    "totalElements": 4,
    "numberOfElements": 1,
    "first": true,
    "size": 1,
    "number": 0,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "empty": false
}
```
All other end points of student service can be accessed in swagger API.

---

## File Upload Service:

### Features
- Enabled Auto sync service with large file upload
- Get status of file processing

---

## Grade and Enrollment Service:

### Features
- Add grade of course after completion
- Enroll the student ranging with course capacity

---

## Data Base
This project uses the H2 in-memory database for simplicity and ease of testing.
To persist data between application restarts, an H2 database file is included in the root directory.
This ensures that sample or test data is not lost when the application is restarted.
Keeping the H2 file allows you to retain test data without requiring a full database setup.

H2 Console can be accessed at:
```
http://localhost:8090/h2-console
```
## Swagger access
To access swagger use the following url to list and test all end points:

```
http://localhost:8090/swagger-ui/index.html
```

## How to Run
1. Clone the repository:
   ```bash
   git clone <https://github.com/rahim7566/Student-Dashboard>
2. Build the project:
    ```bash
   mvn clean install
3. Run the application:
    ```bash
   mvn spring-boot:run
4. Access the application at:
   ```bash
   http://localhost:8090/swagger-ui/index.html
This file is structured, detailed, and ready to be used in your GitHub repository.




