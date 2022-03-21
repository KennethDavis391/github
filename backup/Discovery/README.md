# Discovery
In order for your service to be discoverable, it needs some YAML.

You need to have a name for the discovery service to expose to the gateway.
spring:
  application:
    name: your-service-name-as-you-want-it-to-appear //(should be user-service or post-service for now)

You need to point your service in the direction of the discovery service itself so it can register itself.
Please note that the discovery service will be running on the same machine as yours (for now), so (for now) you 
should not use port 8083 yourself.
eureka:
  client:
    serviceUrl:
      defaultZone: ${EUREKA_URI:http://localhost:8083/eureka} //Environment variable with default

You can, in fact, allow Eureka to determine the port that your service uses by setting the port to 0.
server:
  port: 0
  
# Installation
Clone https://github.com/Revature-Reverse/Discovery.git, open the project in your IDE, and run it.
