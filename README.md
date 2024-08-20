# Kitchensink

KitchenSink is a Java 21 Spring Boot application that has been migrated from a JBoss-based architecture. This application uses MongoDB as the primary database and serves as an example of a complete, fully-functional backend service with a variety of features and best practices. The migration to Spring Boot provides enhanced flexibility, ease of development, and modernized architecture.

### Features
* **Spring Boot:** Provides the framework for building the application with embedded server capabilities.
* **MongoDB Integration:** Utilizes MongoDB as the primary data store, allowing for NoSQL database operations.
* **RESTful APIs:** Exposes a set of RESTful endpoints for interacting with the application.
* **Java 21:** Leverages the latest Java version for improved performance and new language features.

### Prerequisites
Before building and running the KitchenSink application, ensure you have the following installed:

* **Java 21 JDK:** Required to compile and run the application.
* **Maven:** For building the application (Ensure that Maven is configured to use the correct JDK).
* **MongoDB:** Running instance of MongoDB, either locally or via a service.

### Setup and Installation
* **Clone the Repository**
```
git clone https://github.com/codingwithshubham/kitchensink.git
```
* **Update Configuration**
  
Update the following MongoDB connection details in application.properties located in src/main/resources/.
```
kitchensink.mongodb.hostname
kitchensink.mongodb.port
kitchensink.mongodb.databasename
```
The default configuration is set to connect to a MongoDB instance running on localhost with the hostname localhost, port 27017, databasename kitchensink.

* **Go to project directory**
```
cd kitchensink
```

* **Build the Application**

Use Maven to build the application
```
mvn clean install
```

This will compile the source code, run unit tests, and package the application into a runnable JAR file.

* **Run the Application**

**Option 1:** Run with Maven
You can run the application directly using Maven:
```
mvn spring-boot:run
```

**Option 2:** Run the Packaged JAR

After building the application, you can run the generated JAR file:
```
java -jar target/kitchensink-0.0.1-SNAPSHOT.jar
```

### Access the Application

Once the application is running, you can access the API at http://localhost:8080. Use tools like Postman or curl to interact with the endpoints.

### Running Tests
  
To run unit tests, execute the following Maven command:
```
mvn test
```

### Project Structure
* **Controller:** MemberResource.java Handles incoming API requests and directs them to the appropriate service layer.
* **Service:** MemberService.java Contains business logic.
* **Model:** MemberEntity.java Represents the domain objects.
* **Repository:** MemberRepository.java Interfaces with MongoDB to perform CRUD operations.

### API Endpoints
Below is a list of the main RESTful API endpoints provided by the KitchenSink application. These endpoints allow you to perform various operations on the application data.

* **GET /members:**

  **Description:** Retrieve all members.


* **GET /members/{id}:** 

  **Description:** Retrieve a specific member by ID.


* **POST /members:** 

  **Description:** Create a new member.


* **PUT /members/{id}:**

  **Description:** update a specific member by ID.


* **Delete /members/{id}:**

  **Description:** Delete a specific member by ID.

----

This README outlines the steps to build, run, and develop the KitchenSink Spring Boot application. If you encounter any issues or need further assistance, please open an issue on the project's GitHub page.
