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
VALUES (1, 'cuadrados/blue.jpg', 1, 0, 0);

INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (2, 'cuadrados/green.jpg', 2, 0, 1);

INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (3, 'cuadrados/pink.jpg', 3, 1, 1);

INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (4, 'cuadrados/red.jpg', 4, 1, 0);

INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (5, 'cuadrados/yellow.jpg', 5, 2, 1);

