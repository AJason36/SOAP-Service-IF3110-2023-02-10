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

# Phony targets to prevent conflicts with filenames
.PHONY: all build run clean