#   Application Security POC Setup Instructions- 
	Prerequisites –
	
	- Java 17
	- Spring Boot
	- Spring tool Suite (IDE) / IntelliJ
	- MYSQL Database
	- Postman (for API testing)
	
	Clone the Repository Provided below – 
	
	Repo URL - https://github.com/aks7hat/ApplicationSecurityPOC.git
	Command – 
	git clone https://github.com/aks7hat/ApplicationSecurityPOC.git
	Or unzip the code and Import the Code in the IDE you selected.
	Setup MYSQL Database in your machine.
	Create a Schema with name “app_security” in the database.
	Update properties in application.properties file according to your database URL. E.g. - jdbc:mysql://localhost:3308/app_security.
	Update the username and password of your database in the same file.
	
	Running the Application –
	
	Once the properties are set, you can run the application as spring boot project.
	The application will run on port 8080.
	Postman to run API’s – 
	Register a user - 
	url - http://localhost:8080/auth/register
	body - {
	    "email": "user@example.com",
	    "password": "SecurePass123"
	}
		Login a user – 
	url - http://localhost:8080/auth/login
	body - {
    "email": "user@example.com",
    "password": "SecurePass123"
	}
