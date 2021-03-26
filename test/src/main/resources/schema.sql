create memory table if not exists test(ID INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(255));
insert into test(name) values('complexica');
insert into test(name) values('developers');

create memory table if not exists weather(ID INT PRIMARY KEY AUTO_INCREMENT, CITYNAME VARCHAR(50), COUNTRY VARCHAR(50), COUNTRYCODE VARCHAR(10), TEMPERATURE FLOAT(6), WEATHER VARCHAR(50), RECORDTIME TIMESTAMP);