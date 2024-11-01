# Assignment 2: RESTful E-commerce API

## Steps

1. Use intellij spring initializer with following configuration:
    - Maven Project
    - Java 21
    - Packaging Jar
    - Dependencies:
      - Spring DevTools
      - Spring Web
      - Spring Data JPA
      - MariaDB Driver
2. Create a database and user in MariaDB
3. Add database configuration in `application.properties`
4. Create a model for `Product`
5. Create a repository for `Product`
6. Add flyway for database migrations
7. Add maven plugin for flyway
8. Add flyway mysql dependency
9. Bean that runs on app start to insert some products
10. Product Controller
11. Fix products being created multiple times on app start
12. Support both JSON and XML for 