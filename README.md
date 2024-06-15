# Employee Bonus Manager

## Introduction

This project implements a small Spring Boot application with two APIs: a POST API to store employee data and a GET API to retrieve employees eligible to receive a bonus on a given date. The application utilizes a MySQL database to persist employee information.

## APIs

### POST API Signature and Payload

#### POST /tci/employee-bonus

**Request Payload sample:**

```json
{
	"employees": [
		{
			"empName": "raj singh",
			"department": "accounts",
			"amount": 5000,
			"currency": "INR",
			"joiningDate": "may-20-2022",
			"exitDate": "may-20-2023"
		},
		{
			"empName": "pratap m",
			"department": "accounts",
			"amount": 3000,
			"currency": "INR",
			"joiningDate": "jan-01-2021",
			"exitDate": "may-20-2023"
		},
		...
	]
}
```

### GET API Signature and Payload

#### GET /tci/employee-bonus?date=”may-27-2022”

**Request Payload sample:**

```json
{
	"errorMessage": "",
	"data": [
		{
			"currency": "INR",
			"employees": [
				{
					"empName": "pratap m",
					"amount": 3000
				},
				{
					"name": "raj singh",
					"amount": 5000
				}
			]
		},
		{
			"currency": "USD",
			"employees": [
				{
					"empName": "sam",
					"amount": 2500
				},
				{
					"empName": "susan",
					"amount": 700
				}
			]
		}
	]
}
```

## Implementation Details

The project consists of the following components:

- **EmployeeService:** Implements business logic for saving employees and retrieving employees eligible for a bonus.
- **EmployeeRepository:** Manages database interactions for employee entities.
- **EmployeeController:** Defines endpoints for the POST and GET APIs.
- **GlobalExceptionHandler:** Handles exceptions thrown during API execution.

## Testing

The project includes comprehensive unit tests to ensure correctness and robustness. The tests cover various scenarios, including valid and invalid data for both APIs. Mockito is used for mocking dependencies to isolate the tests from external dependencies.

## Database

The application uses a MySQL database to persist employee data. The database schema includes tables for employees and departments.

## Build and Run

To build and run the application, follow these steps:

1. Clone the repository.
2. Configure the MySQL database connection in the `application.properties` file.
3. Build the project using Gradle.
4. Run the application using the generated JAR file or Gradle's `bootRun` task.

## Project Structure

The project follows a standard Spring Boot structure, with separate packages for controllers, services, repositories, and models. This structure ensures modularity and maintainability of the codebase.

## Conclusion

The Employee Bonus Manager project demonstrates the implementation of RESTful APIs using Spring Boot, along with best practices for testing, exception handling, and database management. It provides a robust foundation for managing employee data and calculating bonuses effectively.
