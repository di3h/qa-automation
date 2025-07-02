# Automation Tests with Java: UI & API

This repository contains a collection of demo tests written in Java.
It is part of my QA portfolio and showcases my ability to create automated UI and API tests.

## Test Areas

- UI tests for web applications using Selenium WebDriver
- API tests using RestAssured (example: testing status codes and response times)

## 📌 Technologies

- Java 11
- Selenium WebDriver
- JUnit 5
- Maven
- Allure
- Git
- RestAssured

## 📁 Project Structure

```
src
├── main
│   └── java
│       └── (empty, reserved for future use)
├── test
│   └── java
│       └── api
            ├── clients       # API client classes
│           ├── tests         # API test classes
│       └── ui
            ├── pages         # Page Objects
│           ├── tests         # UI test classes
│       └── utils         # Driver setup, helpers
```

## 🧪 Tested Website

[https://the-internet.herokuapp.com/](https://the-internet.herokuapp.com/)

### Scenarios:
#### UI Tests (Selenium)
- Invalid login: incorrect username/password shows error
- Valid login: redirects to secure page
- (planned) Logout flow. Other forms and alerts

#### API Tests (RestAssured)
- Status codes endpoint /status_codes/{code}:
- - Checks returned HTTP status codes (e.g., 200, 404, 500)
- - Verifies that the response body contains expected status text 
- - Ensures response time is under 1 second

## ▶️ How to Run

1. Clone the project:
```bash
git clone https://github.com/di3h/qa-automation.git
```
2. Open in IntelliJ IDEA or run from terminal:
```bash
mvn clean test
```
3. (Optional) View test report in Allure:
```bash
allure serve target/allure-results
```
