include .env

# Default target
all: build run

# Build the project with Maven
build:
	mvn clean install

# Run the JAR file
run:
	java -jar target/soap_service-1.0.jar

# Clean up the project
clean:
	mvn clean
	rm -f target/soap_service-1.0.jar

create-db:
	mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS $(DB_NAME)" 
	exit

migrate:
	mvn flyway:migrate -Dflyway.url=jdbc:mysql://localhost:3306/$(DB_NAME) -Dflyway.user=$(DB_USER) -Dflyway.password=$(DB_PASSWORD)

migrate-repair:
	mvn flyway:repair -Dflyway.url=jdbc:mysql://localhost:3306/$(DB_NAME) -Dflyway.user=$(DB_USER) -Dflyway.password=$(DB_PASSWORD)

# Phony targets to prevent conflicts with filenames
.PHONY: all build run clean