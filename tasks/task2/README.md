
# Task 2. Blind SQL injection

Purpose of the task:
1. Learn to find vulnerable code chunks
1. Learn to exploit SQL injection in order to understand the risks
1. Learn to fix SQL injection vulnerabilities

Task sequence:
Given an app with REST services running on app: GET/users, POST/users, POST/login, show the proof of vulnerability by logging in as administrator
App must response "Logged in OK" in API message 
*Consider you do not know salt value.*

1. Exploiting
   1. Identify vulnerable service
   1. Create user
   1. Extract your new user hash value
   1. Replace administrator password hash with yours and try login
1. Try automated tools like sqlmap
1. Fixing
   1. Try find and edit app server code in order to fix SQL injection vulnerability
1. Can you find protection from sqlmap in the app (despite vulnerability still exist)?


1. Install docker-compose
1. clone this task folder from GitHub
1. Launch application using docker composer: 
  ```
  docker-compose up
  ```



You may want to build database separately and run app locally:
* Build image:  
  docker build . -tag db-only -f Dockerfile-postgresql 
* Run image with Postgres SQL port 5432 exposed:
  docker run --name usersdb  -p 5432:5432 --rm db-only

  Container, named usersdb will be available. Since started with --rm, will autocleanup on kill

* To connect to DB in the running container:
  docker exec -it usersdb psql -h localhost -U docker -d myapp




Sample POST request to the application:
curl --header "Content-Type: application/json" --data '{"userName":"admin","password":"guessme"}' http://localhost:8080/login

Sample POST reequest for new user:
curl --header "Content-Type: application/json" --data '{"userName":"alex1","userFName":"Alexander","userLName":"Bob","pas http://localhost:8080/users

Sample GET request for checking if user exists:
curl "http://localhost:8081/users?username=admin"

