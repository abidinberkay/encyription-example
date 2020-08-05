Task Description:
    
    The assignment is to build UI + API applications (2 separate ones) with the following
    functionalities:
    -UI application displays a form and table of data polled from the API (list of encrypted
    values).
    -The form must include a text input and “Encrypt” & “Decrypt” buttons:
    -Pressing “Encrypt” in the form, the provided text value is sent to API, encrypted
    and saved into the database and a new record appears in the UI table.
    -You can select a row from the table, the encrypted value is placed into the form’s
    text input, where you can press "Decrypt" to display the original value.
    /Or press “Encrypt” where the encrypted value is simply encrypted and saved
    again.
    
    ● The key pair for encryption and decryption must be stored in the API application (you
    decide on the best solution).
    ● API functionality must be tested with Unit tests.
    ● Additional points will be given if authentication is added and the key pair is connected
    to the user (not using one single key pair for everyone).
    
    Tech limits:
    ● The API application should use the Spring (Boot) Framework.
    ● Java SE 8+.
    ● In-memory database for storing encrypted values.
    ● Use Fetch API instead of jQuery on the UI application.

Running the project on docker:
    
    -Go to the root folder of the project where the docker-compose.yaml file is located with bash or other command prompts 
    (which is also same directory with this README file)
    
    -run the command "docker-compose up -d"
    
    And the application will start to work. Frontend will work on http://localhost:4200 and for backend http://localhost:8080. 
    So be sure that these ports are available to use or not already used.
    
Running the project without docker:

    Requirements:
        -java 8
        -maven
        -node (Project is done on version 12.18.1, you can use it or any compatible node version)
        -angular (Project is done on version 10, you can use it or any compatible node version)
        -typescript
        
    Backend:
        Go to root directory of the project (where this README file is located) with bash or any command prompt
        Run following commands:
            -cd icefirebackend
            -mvn clean install
            -cd target
            -java -jar icefire-assignment.jar
        
        The application will start to work on browser link http://localhost:8080
        
        After running backend application, you can see H2 in memory database schema on link http://localhost:8080/h2-console
        Then, be sure that JDBC URL is "jdbc:h2:mem:Entrydb" and click "Connect"
        
    Frontend:
        Go to root directory of the project (where this README file is located) with bash or any command prompt
        Run following commands:
            -cd icefirefrontend
            -cd icefire
            -npm install
            -ng serve
            
        The application will start to work on browser link http://localhost:4200
        
    
TODO (To improve)

    -For authentication, session based authorization and authentication will be implemented. JWT(Json web token) is implemented
    with spring security on back-end and will be added when I solve fetch api request structure.
    I have used spring security before but cannot apply right now because I have used fetch api first time and 
    need to understand the structure of requests better.
