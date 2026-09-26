# API_AUTO_TEST_petstore

API automation framework for testing the **Swagger Petstore REST API** using **Java, Rest Assured, Cucumber BDD, JUnit 4, and Maven**.

## Tech Stack

* **Java**
* **Maven**
* **Rest Assured 5.5.0**
* **Cucumber 7.18.1**
* **JUnit 4**
* **Git / GitHub**
* **Swagger Petstore API**

## Automated Test Scenarios

The current feature file covers the following API operations.

### Pet APIs

1. Add a new pet successfully
2. Retrieve a pet by ID
3. Update an existing pet
4. Delete a pet
5. Retrieve a pet using an invalid ID and validate `404`

### Store / Order APIs

6. Place a new order
7. Retrieve an order by ID
8. Delete an order
9. Retrieve an order using an invalid ID and validate `404`


## Running the Tests

### Prerequisites

Install:

* Java 17 or later
* Maven
* Git
* IntelliJ IDEA or another Java IDE

Verify Java:

```bash
java -version
```

Verify Maven:

```bash
mvn -version
```

### Run All Tests

From the project root:

```bash
mvn test
```

### Run the Cucumber Test Runner

The test runner is located at:

```text
src/test/java/com/petstore/runner/TestRunner.java
```

It uses:

```java
@RunWith(Cucumber.class)
```

and scans:

```text
classpath:features
```

with step definitions under:

```text
com.petstore.steps
```

## Test Report

After execution, Cucumber generates an HTML report:

```text
target/cucumber-report.html
```

Open the report in a browser to review:

* Executed scenarios
* Passed/failed scenarios
* Step execution results
* Execution details

## HTTP Methods Covered

| Method | Purpose                  |
| ------ | ------------------------ |
| GET    | Retrieve pets and orders |
| POST   | Create pets and orders   |
| PUT    | Update an existing pet   |
| DELETE | Delete pets and orders   |

## Error Handling

The framework also includes negative API scenarios.

## Design Principles

The framework follows a simple separation of responsibilities:

### Feature Files

Responsible for:

* Business-readable scenarios
* API behavior
* Expected results

### Step Definitions

Responsible for:

* Mapping Gherkin steps to Java
* Preparing test data
* Calling the API client
* Validating responses

### API Client

Responsible for:

* HTTP requests
* Base URL
* Headers
* Request payloads
* Rest Assured implementation

### ConfigReader

Responsible for:

* Loading external configuration
* Providing configuration values to the framework

This separation makes the framework easier to maintain and extend.

## Future Enhancements

The framework can be extended with:

* Environment-specific configuration
* Request/response logging
* POJO-based request and response models
* JSON Schema validation
* Cucumber tags
* Parallel execution
* Allure reporting
* CI/CD integration
* GitHub Actions
* Test data management
* Dynamic test data generation
* Retry handling for transient API failures
* Authentication/token management
* Contract testing
* Database validation
* Docker-based execution

## Git Workflow

Typical workflow:

```bash
git status

git add .

git commit -m "Update API automation tests"

git push origin main
```

## Important Note

The Swagger Petstore is a shared public API. Fixed test IDs such as `10001` and `20001` may potentially conflict with data created by other users or previous test executions.

For production-grade automation, test data should be generated dynamically and cleaned up after execution.

## Author

**Prabu Chinnavar**

Senior / Automation QA Lead

Focus areas:

* API Automation
* Rest Assured
* Java
* Cucumber BDD
* Selenium
* Appium
* Test Automation Framework Design
* QA Strategy
* CI/CD
