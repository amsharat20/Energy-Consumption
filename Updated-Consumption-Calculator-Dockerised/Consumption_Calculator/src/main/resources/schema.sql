CREATE TABLE placeinfo ( 
id int NOT NULL,
name varchar(255),
PRIMARY KEY (id)
);

CREATE TABLE consumptioninfo (
    keyrand int NOT NULL AUTO_INCREMENT,
    id int, 	
    consumption double,
    tmsmtp datetime,
	PRIMARY KEY (keyrand),
    FOREIGN KEY (id) REFERENCES placeinfo(id)
);