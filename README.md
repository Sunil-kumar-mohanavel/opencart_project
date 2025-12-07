<!-- Banner -->
<h1 align="center">🛒 OpenCart Automation Framework (UI + DB Testing)</h1>

<p align="center">
  <b>A comprehensive automation framework for the OpenCart e-commerce platform, combining UI Automation & Database Validation (MySQL) with Jenkins-based CI/CD integration.</b>
</p>

---

<p align="center">
  <img src="https://img.shields.io/badge/Language-Java-orange?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Framework-TestNG-blue?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Selenium-WebDriver-brightgreen?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/DB-MySQL-lightgrey?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Reports-Extent%20Reports-yellow?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/CI/CD-Jenkins-red?style=for-the-badge"/>
</p>

---

## 💡 Why This Project Matters

Manual testing of OpenCart workflows can be repetitive and time-consuming.  
This automation framework provides a scalable and maintainable solution to validate both UI and backend workflows seamlessly.

- 🧪 Automates end-to-end testing of core e-commerce flows (UI + DB)
- 🔄 Ensures **UI actions** reflect accurately in the **database**
- ⚙️ Improves test **reliability, reusability, and maintainability**
- ☁️ Supports **CI/CD integration with Jenkins** for continuous validation
- 📉 Reduces manual regression time and increases test coverage across modules

> 💬 **In short:** It bridges the gap between front-end behavior and back-end data integrity, ensuring OpenCart’s key features work flawlessly across releases.




---

## ⚙️ **Tech Stack Overview**

| Category | Technology |
|-----------|-------------|
| **Language** | Java |
| **Framework** | TestNG |
| **Automation Tool** | Selenium WebDriver |
| **Database** | MySQL (via JDBC) |
| **Build Tool** | Maven |
| **Reporting** | Extent Reports |
| **CI/CD** | Jenkins |
| **Version Control** | Git & GitHub |


---

## 📋 **Prerequisites**

Make sure the following are installed before running the framework:

- ☕ **Java JDK 17+**
- 🧩 **Maven** (`mvn -v` to verify)
- 🌐 **Browser Driver** (e.g., ChromeDriver) added to PATH
- 🧠 **TestNG Plugin** (if using an IDE)
- 🗄️ **MySQL Database** instance accessible and configured



---

## 🧩 **Framework Architecture**

```pgsql
OpenCart_project/
├── src/test/java/
│   ├── base/           # Driver factory, Base Test setup
│   ├── pages/          # Page Object Model (Registration, Login, Home, Product, Cart, Checkout)
│   ├── UI_tests/       # TestNG test classes (User Management, Product, Cart, Checkout, DB connection)
│   ├── utils/          # Browser setup, Config Reader, DB Utils, DB Validation, Waits, Screenshots, Element actions, Extent Reports
│   └── listeners/      # TestNG Listeners (Test Listeners)
├── testng.xml          # Test Suite runner configuration
└── pom.xml             # Maven project dependencies

```


## 🧠 Test Coverage (UI Modules)

This framework automates key end-to-end workflows across the OpenCart e-commerce platform:

| Module | Description |
|---------|-------------|
| **User Registration & Login** | Validates new user registration, login/logout flows, and negative scenarios |
| **Product Browsing & Filtering** | Automates product listing, category navigation, and search filters |
| **Add to Cart & Remove Items** | Ensures cart accuracy, item management, and total price verification |
| **Checkout Process** | Verifies address entry, payment flow, and order confirmation screens |
| **Database Validation** | Cross-checks UI actions (e.g., user data, order details) with backend MySQL records via JDBC |


---

## 🚀 Key Features

✨ What makes this framework powerful and production-ready:

✅ Hybrid Model (POM + Utilities)

🔄 Seamless UI + DB Validation Integration

⚙️ Reusable Utilities for browser, config, waits, elements, screenshots, DB, and reporting

💾 MySQL Validation through JDBC queries

🧠 Modular, Maintainable, & Scalable Architecture

📊 Dynamic HTML Reports with Extent Reports

☁️ Jenkins Support for Continuous Integration


---


## ▶️ **How to Run the Tests**

Before running the automation framework, ensure the following prerequisites are met:

### ⚙️ **Mandatory Setup**

- 🛒 **OpenCart Application (Local Instance)** must be installed and running.  
  > The framework interacts directly with your local OpenCart site for UI and DB validations.

- 🗄️ **XAMPP (Apache + MySQL)** must be running.  
  > Used to host the local OpenCart application and provide the MySQL database connection.

---

### ☁️ **Run via Jenkins**

Follow these steps to execute the automated test suite through Jenkins CI/CD:

1. **🧱 Create a New Maven Project**  
   - Open Jenkins → *New Item* → *Maven Project*  
   - Name your job appropriately (e.g., `OpenCart_Automation`)  

2. **🔗 Configure Source Code Management**  
   - Select **Git** as the SCM  
   - Provide your **GitHub repository URL**  

3. **⚙️ Set Up Build Configuration**  
   - **Root POM:** `pom.xml`  
   - **Goals and Options:** `clean test`  

4. **🚀 Execute the Build**  
   - Click **Build Now** to trigger execution  
   - Monitor progress in the **Jenkins Console Output** for real-time logs  

5. **📈 Configure Post-Build Actions**  
   - Add **Publish HTML Reports** under *Post-Build Actions*  
   - **Report Directory:** `test-output/`  
   - **Report File:** `ExtentReports.html`  

---

### 💻 **Run Locally via IDE**

You can also run the test suite directly from your preferred IDE (e.g., IntelliJ IDEA, Eclipse, or VS Code).

1. **🧩 Import the Project**  
   - Open your IDE → *Import as Maven Project*  
   - Wait for dependencies to download (`pom.xml` will auto-resolve)  

2. **⚙️ Configure TestNG**  
   - Ensure the **TestNG plugin** is installed  
   - Open the `testng.xml` file from the project root  

3. **▶️ Execute Tests**  
   - Right-click on `testng.xml` → *Run as* → **TestNG Suite**  
   - Tests will start running in the console output  

4. **📊 View Reports**  
   - After execution, navigate to:  
     `test-output/ExtentReports.html`  
   - Open the report in any browser to view detailed test results  

---

✅ **Tip:**  

Use the Jenkins approach for continuous integration, and IDE execution for quick local debugging or individual test runs.  
Both require your **OpenCart instance** and **MySQL server (via XAMPP)** to be up and running before execution.


---




## 👨‍💻 Author

### **Sunil Kumar**

**Automation Test Engineer | Aspiring SDET**
<p align="left"> <a href="https://github.com/Sunil-kumar-mohanavel"><img src="https://img.shields.io/badge/GitHub-Profile-black?style=flat-square&logo=github"/></a> </p>







