#!/bin/bash
mvn clean install  -DskipTests
cd target
java -jar -Dserver.port=9092 Consumption_Calculator-0.0.1-SNAPSHOT.jar