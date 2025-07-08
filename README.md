# Selenium Java Automation Framework

This is a comprehensive test automation framework developed using Selenium WebDriver with Java and TestNG.
The project is structured to ensure high maintainability, reusability, and scalability, following industry-standard practices.

## 📌 Technologies & Tools Used

- Java (JDK 17)
- Selenium WebDriver
- TestNG
- Maven (Project Management)
- Apache POI (Data-driven testing from Excel)
- ExtentReports (Reporting)
- Jenkins (CI/CD Integration)
- Git + GitHub (Version Control)
- Page Object Model (POM) Design Pattern

## 🧱 Framework Architecture

The project follows the Page Object Model (POM) pattern and includes:

- /src/main/java
  - base: BaseTest, DriverFactory
  - pageObjects: All page classes (e.g., LoginPage, CheckoutPage)
  - utilities: ConfigReader, ReusableMethods
- /src/test/java
  - testcases: Test classes grouped by feature
- testng.xml – suite and test configurations
- extent-config.xml – reporting configuration
- data/
  - TestData.xlsx – for data-driven tests
- reports/
  - HTML reports generated after test runs

## ✅ Features Covered

- Cross-browser testing (Chrome, Firefox)
- Data-driven testing using Excel
- Dynamic test reports with ExtentReports
- Page Object Model structure
- Parameterization via testng.xml
- Parallel test execution via TestNG
- Continuous Integration support via Jenkins

## 🚀 How to Run the Project

1. Clone the repository
   ```bash
   git clone https://github.com/yourusername/selenium-java-framework.git

2. Import as Maven Project in Eclipse or IntelliJ

3. Install dependencies
 ```bash
mvn clean install 
 ```


5. Run the test suite
 ```bash
mvn test
```

7. View reports:
Navigate to 
/reports/index.html to view ExtentReports summary


🧪 Sample Test Scenario

Launch the application

Login with valid credentials

Add item to cart

Checkout and verify order confirmation

Validate success message and order ID


