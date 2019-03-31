
Running as Docker Containers:

Pull docker image of mysql.

docker pull mysql

Run for SQl container to get started:

docker run --name mysqlcontainer -e MYSQL_ROOT_PASSWORD=PASSWORD -e MYSQL_DATABASE=energy_consumption -p 3307:3307 -d mysql:latest

To give permissions run in command-line and enter docker mysql bash:

docker exec -it mysql bash

mysql -u root -pPASSWORD --protocol=tcp

ALTER USER root IDENTIFIED WITH mysql_native_password BY 'PASSWORD';

exit

exit


For GUI Interface of Mysql to check values : Use PHP.

Pull docker image of php.

docker pull php

docker run --name phpmyadmin -d --link mysql:db -p 8080:80 phpmyadmin/phpmyadmin:latest

URL : http://localhost:8080/index.php   <<username: root , password: PASSWORD>>


To run Spring boot as container and link it to mySQL:

mvn clean install

docker build -f Dockerfile -t consumptioncalculator .

docker run -t --name consumptioncalculator --link mysqlcontainer:mysql  -p 8091:9099 consumptioncalculator


SWAGGER API to check the conumption calculator APIs:


http://localhost:8091/swagger-ui.html
