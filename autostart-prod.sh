#!/bin/sh
cd /transport-service

java -jar -Dspring.profiles.active=prod $JAR

