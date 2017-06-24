# Instructions
- fire up the application using `$ mvn spring-boot:run`
- then hit the server using curl `$ curl -s --request POST http://localhost:8080/rest/mars/MMRMMRMM`
- you should see `(2, 0, S)` printed out

# Packaging app for servlet container
- use this command `$ mvn package` (this will also run the tests) 
