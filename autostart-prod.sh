#!/bin/sh
cd /parking-lot

java -jar -Dspring.profiles.active=prod $JAR

