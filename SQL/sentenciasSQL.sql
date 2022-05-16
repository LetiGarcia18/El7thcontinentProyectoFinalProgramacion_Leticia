drop database if exists the7thContinent;
create database the7thContinent;
use the7thContinent;

create table cartaTerreno(
	id numeric(10)  primary key,
	ruta varchar(500) not null,
    numeroCarta numeric(5) not null,
    posicionX numeric(3),
    posicionY numeric(3)
);

SELECT * FROM the7thcontinent.cartaterreno;

INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (1, 'cuadrados/blue.jpg', 1, 20, 100);

