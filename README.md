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