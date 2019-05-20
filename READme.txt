# To run the application, execute following command in terminal -

cd app-parking-lot

mvn clean install

java -jar -Dspring.profiles.active=<dev/prod> -Dspring.datasource.url=jdbc:mysql://<host>:<port>/<db_name>?useSSL=false -Dspring.datasource.username=root -Dspring.datasource.password=root <jar-name>.jar

# After successfully execution, open browser and check following url -

http://<domain>:<port>/api/public/health
