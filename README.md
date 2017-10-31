Requirements

1. jre 1.8.0 and above.

2. maven 3.3

Steps to deploy, After checkout ..

Run command 'mvn clean package'

1.1 This will download all the dependent packages defined as dependency of this service.

1.2 Create a jar file 'org.springapp-0.0.1-SNAPSHOT.jar'

To deploy this service use following command :

	(Windows)

	'java -jar target\org.springapp-0.0.1-SNAPSHOT.jar  --spring.config.location=c:\config\application.properties'

	(Unix)

	'java -jar target\org.springapp-0.0.1-SNAPSHOT.jar  --spring.config.location=./application.properties'

Copy 'application.properties' from /src/main/resources/application.properties and modify it to point database.

Database configuration

Please refer https://github.com/mthakare/vertx/blob/master/README.md


APIs

GET http://localhost:8080/v1/springapp/records

GET http://localhost:8080/v1/springapp/version

Note no post API is defined.
Please use API defined at  https://github.com/mthakare/vertx/blob/master/README.md to populate database.


