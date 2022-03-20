# PREREQUISITES

    -PostgreSql Server version 13

    -Review the README files for the Discovery and Assets repositories after cloning them
        --To run the full application download all repositories in the Revature Reverse organization

# SETTING UP THE ENVIRONMENT
    
    -Applications

        --Begin by cloning the git repositories to the local folder of your choice
            Ex: git clone https://github.com/Revature-Reverse/User-Service.git
        
        --open the project in IntelliJ or your IDE of choice (refer to IDE documentation for assistance importing a Maven project from IntelliJ)

        --use VS Code or another editor if your IDE does not provide support for front-end technologies
            -open a new terminal window and navigate to the root directory of the angular application
            -run 'npm install'
            -once the dependencies have been installed run 'ng serve -o' to start the Angular application. it will automatically open a new browser window

    -Database

        --In order to create the schema needed for the application, you'll first want to log into your PostgreSql instance and run the setup.sql file. This file also contains some dummy data, to not include this data only run up to line 163 in the file.

    -Environment file
        --To keep our credentials secret we paramaterized the application.yaml file to pull the following from environment variables

        --DB_URL
            This should be the jdbc url used to connect to your database

        --DB_USERNAME
            Username needed to connect to your database

        --DB_PASSWORD
            Password needed to connect to your database

        --SECRET
            This should be at least 64 random bytes that are then base 64 encoded. This is used for verifying and creating JWTs
    
        --EUREKA-URI
            The address and port where the Eureka Discovery Service can be reached for registration and heartbeat. 


# RUNNING THE PROJECT

    -Ensure the Discovery Service is running
        --Browse to the Eureka dashboard at http://localhost:8083 
 
    -Run the User Service Application 
        --Browse to the swagger-ui dashboard at http://localhost:8081/swagger-ui.html