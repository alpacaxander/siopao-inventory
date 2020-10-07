FROM maven:3-openjdk-8

WORKDIR /inventory

COPY target/inventory-1.0-SNAPSHOT.jar ./

CMD ["java", "-jar", "./inventory-1.0-SNAPSHOT.jar"]