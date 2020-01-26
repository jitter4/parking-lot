# To run the application, execute following command in terminal -

cd app-parking-lot

mvn clean install

java -jar -Dspring.profiles.active=<dev/prod> -Dspring.datasource.url=jdbc:mysql://<host>:<port>/<db_name>?useSSL=false -Dspring.datasource.username=root -Dspring.datasource.password=root <jar-name>.jar

# After successfully execution, open browser and check following url -

http://<domain>:<port>/api/public/health





# For depolying with an Nginx Rverse proxy by replacing text in between <> :

cd app-parking-lot

mvn clean install

cp parking-lot.service /etc/systemd/system/

sudo vi /etc/system/system/parking-lot.serivce
# Provide correct file path in ExecStart
# ExecStart=/usr/bin/java -jar <DIR_PATH>/parking-lot/app-parking-lot/target/parking-lot.jar

sudo systemctl start parking-lot

cp parking-lot.conf /etc/nginx/conf.d/

sudo systemctl start nginx

# Application shall be accessible at http://localhost:8080/parking-lot/...

