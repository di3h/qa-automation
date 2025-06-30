# Automation Tests with Java + Selenium

This repository contains a collection of demo tests written in Java using Selenium WebDriver.  
It is part of my QA portfolio and showcases my ability to create automated tests for web applications.

## 📌 Technologies

- Java 11
- Selenium WebDriver
- JUnit 5
- Maven
- Allure
- Git

## 📁 Project Structure

```
src
├── main
│   └── java
│       └── #app code
├── test
│   └── java
│       ├── tests         # UI test classes
│       ├── pages         # Page Objects (optional)
│       └── utils         # Driver setup, helpers (optional)
```



## 🧪 Tested Website

[https://the-internet.herokuapp.com/](https://the-internet.herokuapp.com/)

Scenarios:
- Invalid login: incorrect username/password shows error
- Valid login: redirects to secure page
- Logout flow
- (planned) Other forms and alerts

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
