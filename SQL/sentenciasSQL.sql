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

create table accion(
	id numeric(10) primary key,
	 tipo varchar(100) not null,
	 descripcion varchar(250) not null,
     costeAccion numeric(4),
     dificultadAccion numeric(5),
     carta_id numeric(10) not null,
     foreign key(carta_id) references cartaTerreno(id)
);

SELECT * FROM the7thcontinent.cartaterreno;
SELECT * FROM the7thcontinent.accion;

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

INSERT INTO accion (id, tipo, descripcion, costeAccion, dificultadAccion, carta_id)
VALUES (1, 'MOVE', 'Move to another terrain', 1, 2, 1);

INSERT INTO accion (id, tipo, descripcion, costeAccion, dificultadAccion, carta_id)
VALUES (2, 'INVESTIGATE', 'Investigate a terrain', 4, 2, 1);

INSERT INTO accion (id, tipo, descripcion, costeAccion, dificultadAccion, carta_id)
VALUES (3, 'MOVE', 'Move two terrains away', 2, 3, 2);

INSERT INTO accion (id, tipo, descripcion, costeAccion, dificultadAccion, carta_id)
VALUES (4, 'INVESTIGATE', 'Investigate a terrain', 8, 2, 2);

INSERT INTO accion (id, tipo, descripcion, costeAccion, dificultadAccion, carta_id)
VALUES (5, 'MOVE', 'Move two terrains away', 1, 3, 3);

INSERT INTO accion (id, tipo, descripcion, costeAccion, dificultadAccion, carta_id)
VALUES (6, 'MOVE', 'Investigate a terrain', 3 ,2, 3);

INSERT INTO accion (id, tipo, descripcion, costeAccion, dificultadAccion, carta_id)
VALUES (7, 'MOVE', 'Move two terrains away', 1, 3, 4);

INSERT INTO accion (id, tipo, descripcion, costeAccion, dificultadAccion, carta_id)
VALUES (8, 'MOVE', 'Investigate a terrain', 6 ,2, 4);




