# CryptoWeatherAPI
Weather API Validation

This is a reference for setting up a new Crypto Weather API Java project.

This Maven Project contains API validation in 2 ways,
  - Validating with HttpClient & HttpCore.
  - Validating with Karate Framework.

1. HttpClient & HttpCore:
	- Navigate to /com/qa/tests/GetApiTest.java and run with TestNg.
	- Required data are passed from config.property file.

2. With Karate Cucumber:
	- Navigate to /com/qa/KarateTest/KarateRunner.java and run with JUnit 4
	- All the required validation steps are available in GetAPI.feature

Tools and Requirements:
  - Maven
  - Karate
  - Junit 4
  - TestNg
  - Java 1.8
