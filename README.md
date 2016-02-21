# realty

Application that provides possibility to search realty in your city

![realty](realty.jpg)

# Getting Started
## Dependencies
Tools needed to run this app: 

* `java 8` 
or 
* `docker` (for Linux), `docker-toolbox` (for Windows and Mac)

## Building the App
### With Java 8
* `./gradlew build`

### With docker
* `docker build -t alexanderpa/realty .`

## Running the App
### With Java 8
* `java -jar build/libs/realty-1.0.0.jar`
* `http://localhost:8080` - to access the running application

### With docker
* `docker run -d --name realty -p 8080:8080 alexanderpa/realty`
#### Linux
* `http://localhost:8080` - to access the running application
#### Windows or Mac
* `docker-machine ip $(docker-machine active)` - to get ip of active VM
* `http://<vm_ip>:8080`