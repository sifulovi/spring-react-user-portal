# User Portal
A User Portal app by react and Spring Boot.

## Technology Used:

1. React JS
2. Spring Boot
3. Spring Security
4. Spring Data JPA
5. Auto Configure Oauth.
6. MySQL


#### Service End dependencies :

**user-info-service** are responsible for running back-end.

For Configure DB:
```
spring.datasource.username=root
spring.datasource.password=
spring.datasource.url=jdbc:mysql://localhost:3306/user-portal?serverTimezone=UTC

## Hibernate Properties
# The SQL dialect makes Hibernate generate better SQL for the chosen database
#spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto = update
spring.jpa.show-sql=true
```

Gradle Dependencies For Oauth: 

```
	compile group: 'org.springframework.security.oauth.boot', name: 'spring-security-oauth2-autoconfigure', version: '2.2.1.RELEASE'
```

#### Front end Dependencies: 
**user-info-client** folder is responsible for running front-end.

1. As front-end is run with React.js with Material UI, so just run Following command will resolve all dependencies.

```
npm install
```

### UI OverView: 

<img src="/gallery/1.PNG" alt="login Ui" style="height: 200px; width:400px;"/>
