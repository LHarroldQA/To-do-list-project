Test Coverage: 60%

# To-Do-List
To-Do-List Web Application capable of creating, reading updating and deleting Users and the tasks assigned to them

Getting Started
Fork the repo and open as a SpringBoot project in your IDE.

Prerequisites
To make changes, run or develope over this project using Java you will need the SpringBoot tool suite.

SpringBoot: https://spring.io/tools

Installing
To get a developement environment running after forking the repo:

Clone onto your PC and open in Spring Tool Suite (as maven project).
Run project as Spring application to use the application.
Open a crome tab and navigate to: https://localhost:8080.
If you want to change the port which you run the web app on you can change the port number in the file: src/main/resources/application.properties.
Running the tests
To run the automated tests:

Open project using Spring Tool Suite
Navigate to src\test\java\com\qa\todo
Open up rest, service or selenium folder.
Rest is where the unit and integration tests for the CRUD functionality can be found/
The service package contains the unit and integration tests for the services of each entity.
Run the test class you'd like to use as a Junit test.
The selenium tests will need the spring boot app to be running in order to fully test the front end functionality. (Some tests need to be uncommented)

Unit Tests
The Controller and Service tests are used to test the crud controller and service layer for the Task/Users Entity and ensure they output as expected for the project.

Integration Tests
Controller and Service - Testing that the Task/User service layer and controllers works in communicating with eachother and the repository by using mocked data.

Selenium Tests
Testing the front end files to ensure CRUD functionality in the front end works as is set out in the specification.

Built With
Maven - Dependency Management

Authors
Liam Harrold - https://github.com/LHarroldQA
License
This project is licensed under the MIT license - see the LICENSE.md file for details
