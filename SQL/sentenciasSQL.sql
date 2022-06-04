drop database if exists the7thContinent;
create database the7thContinent;
use the7thContinent;

create table cartasTerreno(
	id numeric(10)  primary key,
	rutaImagen varchar(500) not null,
    numeroCarta varchar(5) not null,
    posicionX numeric(3),
    posicionY numeric(3),
    incialmenteVisible bit
);

create table accion(
	id numeric(10) primary key,
	 tipo varchar(100) not null,
	 descripcion varchar(250) not null,
     costeAccion numeric(4),
     dificultadAccion numeric(5),
     carta_num varchar(5) not null
);

create table consecuencia(
	id numeric(10) primary key,
	tipo varchar(100) not null,
	accion_id numeric(10) not null,
	esPositiva numeric(1),
	cartaObjetivo varchar(5),
	foreign key(accion_id) references accion(id)
);

create table cartasEvento(
	id numeric(10) primary key,
	numeroCarta varchar(5),
    rutaImagen varchar(500) not null,
    posicionX numeric(3),
    posicionY numeric(3),
    id_cartaAsociada numeric(10) not null,
    id_accionDesactivada numeric(10) not null,
    foreign key(id_cartaAsociada) references cartasTerreno(id),
	foreign key(id_accionDesactivada) references accion(id)    
);

SELECT * FROM the7thcontinent.cartasterreno;
SELECT * FROM the7thcontinent.accion;
SELECT * FROM the7thcontinent.consecuencia;
SELECT * FROM the7thcontinent.cartasevento;

#Insercción cartas terreno*
INSERT INTO cartasTerreno (id, rutaImagen, numeroCarta, posicionX, posicionY, incialmenteVisible)
VALUES (1, 'cartasTerreno/015.png', '015', 1, 2, 1);

INSERT INTO cartasTerreno (id, rutaImagen, numeroCarta, posicionX, posicionY, incialmenteVisible)
VALUES (2, 'cartasTerreno/009.png', '009', 2, 2, 1);

INSERT INTO cartasTerreno (id, rutaImagen, numeroCarta, posicionX, posicionY, incialmenteVisible)
VALUES (3, 'cartasTerreno/007.png', '007', 3, 1, 1);

INSERT INTO cartasTerreno (id, rutaImagen, numeroCarta, posicionX, posicionY, incialmenteVisible)
VALUES (4, 'cartasTerreno/004.png', '004', 3, 3, 1);

INSERT INTO cartasTerreno (id, rutaImagen, numeroCarta, posicionX, posicionY, incialmenteVisible)
VALUES (5, 'cartasTerreno/010.png', '010', 3, 2, 1);

INSERT INTO cartasTerreno (id, rutaImagen, numeroCarta, posicionX, posicionY, incialmenteVisible)
VALUES (6, 'cartasTerreno/006.png', '006', 2, 3, 1);

INSERT INTO cartasTerreno (id, rutaImagen, numeroCarta, posicionX, posicionY, incialmenteVisible)
VALUES (7, 'cartasTerreno/024.png', '024', 1, 1, 0);

INSERT INTO cartasTerreno (id, rutaImagen, numeroCarta, posicionX, posicionY, incialmenteVisible)
VALUES (8, 'cartasTerreno/010g.png', '010g', 3, 2, 0);

#Inserción acciones
INSERT INTO accion (id, tipo, descripcion, costeAccion, dificultadAccion, carta_num) VALUES
(1, 'MOVE', 'Move to another terrain', 2, 0, '015'),
(2, 'SEARCH', 'Examine', 0, 0, '015'),
(3, 'MOVE', 'Move to another terrain', 1, 0, '009'),
(4, 'OBSERVE', 'Observe something', 1, 0, '009'),
(5, 'MOVE', 'Move to another terrain', 1, 0, '007'),
(6, 'OBSERVE', 'Observe something', 0 ,0, '007'),
(7, 'INVESTIGATE', 'Explore this area', 1, 0, '007'),
(8, 'MOVE', 'Move to another terrain', 2 ,0, '004'),
(9, 'INVESTIGATE', 'Explore this area', 1 ,0, '004'),
(10, 'MOVE', 'Move to another terrain', 1 ,0, '010'),
(11, 'INVESTIGATE', 'Explore this area', 1 ,0, '010'),
(12, 'MOVE', 'Move to another terrain', 2 ,0, '006'),
(13, 'OBSERVE', 'Observe something', 1 ,0, '006'),
(14, 'SEARCH', 'Examine', 0 ,0, '006'),
(15, 'CLIMB', 'Show off your physical prowess.', 1 ,2, '005');

#Inserción de consecuencias
INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo)
VALUES (1, 'DESPLAZARSE', 1, 1, null);

INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo)
VALUES (2, 'TRAER_CARTA', 2, 1, '022');

INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo)
VALUES (3, 'DESPLAZARSE', 3, 1, null);

INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo)
VALUES (4, 'TRAER_CARTA', 4, 1, '034');

INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo)
VALUES (5, 'DESPLAZARSE', 5, 1, null);

INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo)
VALUES (6, 'TRAER_CARTA', 6, 1, '011');

INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo)
VALUES (7, 'TRAER_CARTA', 7, 1, '018');

INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo)
VALUES (8, 'DESPLAZARSE', 8, 1, null);

INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo)
VALUES (9, 'TRAER_CARTA', 9, 1, '008');

INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo)
VALUES (10, 'DESPLAZARSE', 10, 1, null);

INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo)
VALUES (11, 'TRAER_CARTA', 11, 1, '005');

INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo)
VALUES (12, 'DESPLAZARSE', 12, 1, null);

INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo)
VALUES (13, 'TRAER_CARTA', 13, 1, '012');

INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo) VALUES
(14, 'TRAER_CARTA', 14, 1, '016'),
(15, 'RESTAURAR', 15, 1, null);

#Inserción cartas evento
INSERT INTO cartasEvento (id, numeroCarta, rutaImagen, posicionX, posicionY, id_cartaAsociada, id_accionDesactivada)
VALUES (1, '005', 'cartasEvento/005.png', 4, 2, 5, 11);










