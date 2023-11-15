FROM maven:3.6.3-amazoncorretto-8 AS build

COPY . /usr/src/app
WORKDIR /usr/src/app

RUN --mount=type=cache,target=/root/.m2 mvn clean install assembly:assembly

FROM amazoncorretto:8
COPY --from=build /usr/src/app/target/ .
WORKDIR /usr/src/app

EXPOSE 8080
CMD java -cp soap_service-1.0.jar com.soap.Main