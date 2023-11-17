FROM amazoncorretto:8u392

COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean package assembly:assembly

EXPOSE 8080
ENTRYPOINT [ "java", "-cp", "target/soap_service-1.0-jar-with-dependencies.jar", "com.soap.Main" ]
# CMD java -cp target/soap_service-1.0-jar-with-dependencies.jar com.soap.Main

