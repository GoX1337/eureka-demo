# eureka-demo

### Build
- mvn clean install
  
### Start Eureka server
- java -jar eureka-server-demo/target/eureka-server-demo-0.0.1-SNAPSHOT.jar

### Start multiples API Eureka client instances
- java -jar eureka-demo-api/target/eureka-demo-api-0.0.1-SNAPSHOT.jar

### Start multiples service Eureka client instances 
- java -jar eureka-client-demo/target/eureka-client-demo-0.0.1-SNAPSHOT.jar

### Show Eureka server dashboard in browser
- GET http://localhost:8761

### Description
Applications eureka-client-demo and eureka-demo-api register to Eureka server (eureka-server-demo)
Then eureka-client-demo get from Eureka server the eureka-demo-api's hostname/port to feed it's /payloads REST endpoint 