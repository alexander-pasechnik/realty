FROM java:8
MAINTAINER Alexander Pasechnik

RUN mkdir /app
COPY . /app
WORKDIR /app
RUN ./gradlew build

WORKDIR /app/build/libs

EXPOSE 8080
ENTRYPOINT ["java","-jar","realty-1.0.0.jar"]