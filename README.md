# WebAutomation\_Task

## Overview

WSD_WebAutomation\_Task is a Maven-based automation framework designed for web testing using Selenium. It follows a structured page-object model and supports test execution with TestNG. The project includes reusable components, utility functions, and data-driven testing capabilities.

## Features

- **Maven Integration:** Dependency management and build automation.
- **Page Object Model (POM):** Enhances maintainability and readability of test scripts.
- **TestNG Support:** Test execution and reporting.
- **Explicit Waits:** Improves test reliability.
- **Data-Driven Testing:** Uses JSON configuration files.
- **Reusable Utility Classes:** Includes functions for handling browser interactions, random data generation, and tab switching.
- **Cross-Browser Testing:** Supports multiple browsers.

## Project Structure

```
WebAutomation_Task
├── pom.xml                         # Project Object Model file for dependencies
├── README.md                       # Project documentation
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── driver
│   │   │   │   └── Browser.java       # Manages browser initialization
│   │   │   ├── elements
│   │   │   │   ├── BaseElement.java  # Base abstract class for UI elements
│   │   │   │   ├── Element.java      # Wrapper class for WebElement actions
│   │   │   │   ├── Links.java        # Manages link elements
│   │   │   ├── pages
│   │   │   │   ├── CartPage.java      # Cart page actions
│   │   │   │   ├── HomePage.java  # Home Page Action
│   │   │   │   ├── SearchResultPage.java     # Search Result  Page actions
│   │   │   ├── utility
│   │   │   │   ├── DataReader.java   # Reads test data from JSON
│   │   │   ├── waits
│   │   │   │   └── ExplicitWait.java # Implements explicit waits
│   ├── test
│   │   ├── java
│   │   │   ├── Tests
│   │   │   │   ├── CartFunctionalityTest.java       # Verify Cart functionality
│   │   │   │   ├── SearchFunctionalityTest.java # verify search functionality
│   │   │   │   ├── TestBase.java            # Base abstract test setup class
│   ├── resources
│   │   ├── configdata
│   │   │   └── config.json        # Stores environment-specific configurations
│   │   ├── testdata
│   │   │   └── TestData.Json      # Stores test data for execution
├── testng.xml                      # TestNG suite configuration file
```



## Test Class Summary

| Test Class                    | Test Cases                                      |
|--------------------------------|------------------------------------------------|
| Tests.CartFunctionalityTest               | verifyAddToCartFunctionality              |
| Tests.SearchFunctionalityTest           | verifySearchFunctionality                   |




## Prerequisites

- **Java 8 or later** java version "20.0.2" or Higher
- **Maven** installed
  - If Maven is not installed, follow this guide: [How to resolve 'mvn' is not recognized](https://medium.com/nerd-for-tech/how-to-resolve-the-mvn-is-not-recognized-as-an-internal-or-external-command-operable-program-or-145914fcaaab)
- **Selenium WebDriver**
- **TestNG Plugin** (for running test cases)
  - If TestNG Plugin is not installed in Eclipse, follow this guide: [How To Install TestNG In Eclipse](https://www.lambdatest.com/blog/how-to-install-testng-in-eclipse-step-by-step-guide/)

## Installation & Setup

1. **Clone the repository:**
   ```sh
   git clone https://github.com/Arif-Uz-Zaman/WSD_Automation_Assessment.git 
   ```
2. **Navigate to the project directory:**
   ```sh
   cd WSD_Automation_Assessment
   ```
3. **Install dependencies:**
   ```sh
   mvn clean install
   ```

## Running Tests

### Run all test cases:

```sh
mvn test
```

- This will execute tests randomly from the test file.

### Run specific test file:

```sh
mvn -Dtest=CartFunctionalityTest test
```

### Run tests using TestNG suite:

```sh
mvn test -DsuiteXmlFile=testng.xml
```

- This will execute tests sequentially.

### Run testng.xml in Eclipse:

To run a `testng.xml` file in Eclipse, follow these steps:

1. Open Eclipse and navigate to the TestNG project folder containing `testng.xml` file.
2. Right-click on the `testng.xml` file.
3. Select **Run As > TestNG Suite**.

## Reports & Logs

- **Chaitest Report:** `target/chaintest/index.html`
- **TestNG Reports:** Located in `test-output/index.html`
- **Surefire Reports:** Found in `target/surefire-reports`
- **Emailable Report:** `test-output/emailable-report.html`

## Author

K.M. Arif Uz Zaman - Software Quality Assurance Engineer

## ChainTest Report 
<a href="https://ibb.co.com/FkmfcNN4"><img src="https://i.ibb.co.com/ZRGwvttz/screencapture-file-C-Users-shobo-eclipse-workspace-WSD-Automation-Assessment-target-chaintest-Index.png" alt="screencapture-file-C-Users-shobo-eclipse-workspace-WSD-Automation-Assessment-target-chaintest-Index" border="0"></a>