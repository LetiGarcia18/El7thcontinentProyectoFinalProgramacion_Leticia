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

create table consecuencia(
	id numeric(10) primary key,
	 tipo varchar(100) not null,
	 accion_id numeric(10) not null,
     esPositiva numeric(1),
     cartaObjetivo numeric(5),
     foreign key(accion_id) references accion(id)
);

SELECT * FROM the7thcontinent.cartaterreno;
SELECT * FROM the7thcontinent.accion;
SELECT * FROM the7thcontinent.consecuencia;

INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo)
VALUES (1, 'DESPLAZARSE', 1, 1, null);

INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo)
VALUES (2, 'TRAER_CARTA', 2, 1, 22);

INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo)
VALUES (3, 'DESPLAZARSE', 3, 1, null);

INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo)
VALUES (4, 'TRAER_CARTA', 4, 1, 34);

INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo)
VALUES (5, 'DESPLAZARSE', 5, 1, null);

INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo)
VALUES (6, 'TRAER_CARTA', 6, 1, 11);

INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo)
VALUES (7, 'TRAER_CARTA', 7, 1, 18);

INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo)
VALUES (8, 'DESPLAZARSE', 8, 1, null);

INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo)
VALUES (9, 'TRAER_CARTA', 9, 1, 8);

INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo)
VALUES (10, 'DESPLAZARSE', 10, 1, null);

INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo)
VALUES (11, 'TRAER_CARTA', 11, 1, 5);

INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo)
VALUES (12, 'DESPLAZARSE', 12, 1, null);

INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo)
VALUES (13, 'TRAER_CARTA', 13, 1, 12);

INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo)
VALUES (14, 'TRAER_CARTA', 14, 1, 16);


/*INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (2, 'cuadrados/green.jpg', 2, 0, 1);

INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (3, 'cuadrados/pink.jpg', 3, 1, 1);

INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (4, 'cuadrados/red.jpg', 4, 1, 0);

INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (5, 'cuadrados/yellow.jpg', 5, 2, 1);*/

INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (1, 'cartasTerreno/015.png', 15, 1, 2);

INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (2, 'cartasTerreno/009.png', 9, 2, 2);

INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (3, 'cartasTerreno/007.png', 7, 3, 1);

/*INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (9, 'cuadrados/burdeos.jpg', 9, 1, 3);*/

INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (4, 'cartasTerreno/004.png', 4, 3, 3);

/*INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (11, 'cuadrados/babyblue.jpg', 11, 4, 1);

INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (12, 'cuadrados/hardPink.jpg', 12, 1, 4);

INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (13, 'cuadrados/darkBlue.jpg', 13, 0, 2);

INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (14, 'cuadrados/black.jpg', 14, 0, 3);

INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (15, 'cuadrados/turquesa.jpg', 15, 0, 4);

INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (16, 'cuadrados/gold.jpg', 16, 2, 0);

INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (17, 'cuadrados/babyRed.jpg', 17, 3, 0);

INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (18, 'cuadrados/darkGreen.jpg', 18, 4, 0);

INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (19, 'cuadrados/darkGrey.jpg', 19, 4, 2);*/

INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (5, 'cartasTerreno/010.png', 10, 3, 2);

INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (6, 'cartasTerreno/006.png', 6, 2, 3);

/*INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (22, 'cuadrados/darkPurple.jpg', 22, 2, 4);

INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (23, 'cuadrados/red.jpg', 23, 3, 4);

INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (24, 'cuadrados/green.jpg', 24, 4, 3);

INSERT INTO cartaTerreno (id, ruta, numeroCarta, posicionX, posicionY)
VALUES (25, 'cuadrados/blue.jpg', 25, 4, 4);*/

INSERT INTO accion (id, tipo, descripcion, costeAccion, dificultadAccion, carta_id)
VALUES (1, 'MOVE', 'Move to another terrain', 2, 0, 1);

INSERT INTO accion (id, tipo, descripcion, costeAccion, dificultadAccion, carta_id)
VALUES (2, 'SEARCH', 'Examine', 0, 0, 1);

INSERT INTO accion (id, tipo, descripcion, costeAccion, dificultadAccion, carta_id)
VALUES (3, 'MOVE', 'Move to another terrain', 1, 0, 2);

INSERT INTO accion (id, tipo, descripcion, costeAccion, dificultadAccion, carta_id)
VALUES (4, 'OBSERVE', 'Observe something', 1, 0, 2);

INSERT INTO accion (id, tipo, descripcion, costeAccion, dificultadAccion, carta_id)
VALUES (5, 'MOVE', 'Move to another terrain', 1, 0, 3);

INSERT INTO accion (id, tipo, descripcion, costeAccion, dificultadAccion, carta_id)
VALUES (6, 'OBSERVE', 'Observe something', 0 ,0, 3);

INSERT INTO accion (id, tipo, descripcion, costeAccion, dificultadAccion, carta_id)
VALUES (7, 'INVESTIGATE', 'Explore this area', 1, 0, 3);

INSERT INTO accion (id, tipo, descripcion, costeAccion, dificultadAccion, carta_id)
VALUES (8, 'MOVE', 'Move to another terrain', 2 ,0, 4);

INSERT INTO accion (id, tipo, descripcion, costeAccion, dificultadAccion, carta_id)
VALUES (9, 'INVESTIGATE', 'Explore this area', 1 ,0, 4);

INSERT INTO accion (id, tipo, descripcion, costeAccion, dificultadAccion, carta_id)
VALUES (10, 'MOVE', 'Move to another terrain', 1 ,0, 5);

INSERT INTO accion (id, tipo, descripcion, costeAccion, dificultadAccion, carta_id)
VALUES (11, 'INVESTIGATE', 'Explore this area', 1 ,0, 5);

INSERT INTO accion (id, tipo, descripcion, costeAccion, dificultadAccion, carta_id)
VALUES (12, 'MOVE', 'Move to another terrain', 2 ,0, 6);

INSERT INTO accion (id, tipo, descripcion, costeAccion, dificultadAccion, carta_id)
VALUES (13, 'OBSERVE', 'Observe something', 1 ,0, 6);

INSERT INTO accion (id, tipo, descripcion, costeAccion, dificultadAccion, carta_id)
VALUES (14, 'SEARCH', 'Examine', 0 ,0, 6);




