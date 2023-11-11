# Spring Docker Projects
The projects in this repository are structured to work with Docker and Kubernetes, focusing on microservices built using Spring Boot. They are ideal for understanding how different components interact in a cloud-native environment.

## Instruction:
Before running any project, ensure you create or copy the application.properties file. Use the provided application.properties.dist as a template and place it in the respective resources folder of each project

## Projects

### ms-comp-user
**Description:**
This is a CRUD (Create, Read, Update, Delete) application that demonstrates the use of OpenFeign for communication with the ms-comp-course service. 

**Technologies Used:**
- Spring Boot
- Spring JPA
- Postgres
- OpenFeign
- Lombok

### ms-comp-course
**Description:**
This CRUD application uses a MySQL database and communicates with the ms-comp-user service using OpenFeign. It focuses on the registration process in courses.

**Technologies Used**
- Spring Boot
- Spring JPA
- Mysql
- OpenFeign
- Lombok