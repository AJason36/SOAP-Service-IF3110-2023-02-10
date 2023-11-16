FROM maven:3.6.3-amazoncorretto-8 AS build

COPY . /usr/src/app
WORKDIR /usr/src/app

RUN mvn clean install assembly:assembly

FROM amazoncorretto:8
COPY --from=build /usr/src/app/target/ .
WORKDIR /usr/src/app

CMD java -cp target/soap_service-1.0-jar-with-dependencies.jar com.soap.Main