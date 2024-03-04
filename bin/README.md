# Student Management RESTful API

This Spring Boot application provides RESTful API endpoints for managing students.

## Building the Application

To build the application, you need to have Maven and Java installed on your system.

1. Clone the repository to your local machine:
git clone https://github.com/your_username/RestfulApiCurd.git

2. Navigate to the project directory:
cd RestfulApiCurd

3. Build the application using Maven:
mvn clean package

## Running the Application

Once the application is built, you can run it using the following command:
java -jar target/RestfulApiCurd-1.0.jar

The application will start, and you should see log messages indicating that the server has started successfully.

## Testing the Application

You can test the application by sending HTTP requests to the provided RESTful API endpoints.

### RESTful API Endpoints
GET /home: Description: Returns a welcome message. Example: [http://localhost:8080/home](http://localhost:8080/swagger-ui.html)
- **GET /students**: Returns a list of all students.
- **GET /students/{studentId}**: Returns the details of a student by ID.
- **POST /students**: Adds a new student.
- **PUT /students/{studentId}**: Updates an existing student by ID.
- **DELETE /students/{studentId}**: Deletes a student by ID.

### Sample Requests

- **GET /students**:
curl -X GET http://localhost:8080/students

- **GET /students/{studentId}**:
curl -X GET http://localhost:8080/students/1

- **POST /students**:
curl -X POST -H "Content-Type: application/json" -d '{"studentName": "Alice", "studentMarks": 95, "email": "alice@example.com", "department": "Computer Science"}' http://localhost:8080/students

- **PUT /students/{studentId}**:

curl -X PUT -H "Content-Type: application/json" -d '{"studentName": "Bob", "studentMarks": 90, "email": "bob@example.com", "department": "Electrical Engineering"}' http://localhost:8080/students/1

- **DELETE /students/{studentId}**:
curl -X DELETE http://localhost:8080/students/1
