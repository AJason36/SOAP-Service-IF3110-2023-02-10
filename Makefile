include .env
export

# Default target
all: build run

# Build the project with Maven
build:
	mvn clean install assembly:assembly

# Run the JAR file
run:
	java -cp target/soap_service-1.0-jar-with-dependencies.jar com.soap.Main

# Clean up the project
clean:
	mvn clean
	rm -f target/soap_service-1.0.jar

create-db:
	mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS $(MYSQL_DATABASE)" 
	exit

# Phony targets to prevent conflicts with filenames
.PHONY: all build run clean