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

## 📋 **Prerequisites**

Make sure the following are installed before running the framework:

- ☕ **Java JDK 17+**
- 🧩 **Maven** (`mvn -v` to verify)
- 🌐 **Browser Driver** (e.g., ChromeDriver) added to PATH
- 🧠 **TestNG Plugin** (if using an IDE)
- 🗄️ **MySQL Database** instance accessible and configured

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

## 🧩 **Framework Architecture**

```pgsql
OpenCart_project/
├── src/test/java/
│   ├── base/           # Driver factory, Base Test setup
│   ├── pages/          # Page Object Model (Registration, Login, Home, Product, Cart, Checkout)
│   ├── UI_tests/       # TestNG test classes (User Management, Product, Cart, Checkout, DB connection)
│   ├── utils/          # Browser setup, Config Reader, DB Utils, DB Validation, Waits, Screenshots, Element actions, Extent Reports
│   └── listeners/      # TestNG Listeners (logging & reporting)
├── testng.xml          # Test Suite runner configuration
└── pom.xml             # Maven project dependencies

```

---

## 🚀 Key Features

✨ What makes this framework powerful and production-ready:

✅ Hybrid Model (POM + Utilities + Data-Driven)

🔄 Seamless UI + DB Validation Integration

⚙️ Reusable Utilities for browser, config, waits, elements, screenshots, DB, and reporting

💾 MySQL Validation through JDBC queries

🧠 Modular, Maintainable, & Scalable Architecture

📊 Dynamic HTML Reports with Extent Reports

☁️ Jenkins Support for Continuous Integration

---

## 🚀 Key Features

✨ What makes this framework powerful and production-ready:

✅ Hybrid Model (POM + Utilities + Data-Driven)

🔄 Seamless UI + DB Validation Integration

⚙️ Reusable Utilities for browser, config, waits, elements, screenshots, DB, and reporting

💾 MySQL Validation through JDBC queries

🧠 Modular, Maintainable, & Scalable Architecture

📊 Dynamic HTML Reports with Extent Reports

☁️ Jenkins Support for Continuous Integration


---





## 👨‍💻 Author

**Sunil Kumar**
**Automation Test Engineer | Aspiring SDET**
<p align="left"> <a href="https://github.com/Sunil-kumar-mohanavel"><img src="https://img.shields.io/badge/GitHub-Profile-black?style=flat-square&logo=github"/></a> </p>




