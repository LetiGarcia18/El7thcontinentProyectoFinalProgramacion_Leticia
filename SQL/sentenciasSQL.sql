drop database if exists the7thContinent;
create database the7thContinent;
use the7thContinent;

create table cartasTerreno(
	id numeric(10)  primary key,
	rutaImagen varchar(500) not null,
    numeroCarta varchar(5) not null,
    textoCarta varchar(800),
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
     carta_num varchar(5) not null,
     iconoAccion varchar(500)
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
    textoCarta varchar(800),
    posicionX numeric(3),
    posicionY numeric(3),
    id_cartaAsociada numeric(10) not null,
    id_accionDesactivada numeric(10) not null,
    foreign key(id_cartaAsociada) references cartasTerreno(id),
	foreign key(id_accionDesactivada) references accion(id)    
);

create table cartasEstado(
	id numeric(10) primary key,
	numeroCarta varchar(5),
    rutaImagen varchar(500) not null,
    textoCarta varchar(800),
    textoEstado varchar(200)
);

create table cartasInventario(
	id numeric(10) primary key,
    numeroCarta varchar(5),
    rutaImagen varchar(500) not null,
    textoCarta varchar(800)
);

create table personaje(
	id numeric(10) primary key,
    nombre varchar(40),
    habilidad varchar(100),
    rutaCartaHistoria varchar(500),
    rutaIconoPersonaje varchar(500),
    rutaHistoriaPersonajeTxt varchar(900)
);
SELECT * FROM the7thcontinent.cartasterreno;
SELECT * FROM the7thcontinent.accion;
SELECT * FROM the7thcontinent.consecuencia;
SELECT * FROM the7thcontinent.cartasevento;
SELECT * FROM the7thcontinent.cartasestado;
SELECT * FROM the7thcontinent.personaje;

#Insercción cartas terreno
INSERT INTO cartasTerreno (id, rutaImagen, numeroCarta, textoCarta, posicionX, posicionY, incialmenteVisible) VALUES
(1, 'cartasTerreno/015.png', '015', 'A thirty-foot long submarine is hanging two cranes keeping it above the surface of the water. There is not a soul in sight.', 1, 2, 1),
(2, 'cartasTerreno/009.png', '009', 'The only vegetation in your area is clumps of red algae clinging to rocks. Columns of yellowish smoke occasionally rise from the ground, swirling near a dead seagull.', 2, 2, 1),
(3, 'cartasTerreno/007.png', '007', 'Further to the north, the terrain slopes steadily down to the water.', 3, 1, 1),
(4, 'cartasTerreno/004.png', '004', 'Progress in this direction is hampered by multiple jets of boiling steam.', 3, 3, 1),
(5, 'cartasTerreno/010.png', '010', 'Thick columns of yellowish smoke rise from cracks in the volcanic rock.', 3, 2, 1),
(6, 'cartasTerreno/006.png', '006', 'There is no smoke here. Some moss and even a few bamboo-like canes grow in this area.', 2, 3, 1),
(7, 'cartasTerreno/024.png', '024', 'The submarine is in the water. Now set off as quickly as you can, you never know what could happen.', 1, 2, 0),
(8, 'cartasTerreno/010g.png', '010g', 'You see the great abyss before you. It scares you just to think you might fall out there.', 3, 2, 0),
(9, 'cartasTerreno/006.png', '006g', 'There is no smoke here. Some moss and even a few bamboo-like canes grow in this area.', 2, 3, 0)
;

#Inserción cartas estado
INSERT INTO cartasEstado (id, numeroCarta, rutaImagen, textoCarta, textoEstado) VALUES 
(1, '104', 'cartasEstado/104.png', 'Your injury throbs with pain', 'INJURED'),
(2, '102', 'cartasEstado/102.png', 'It is so cold your hands and feet are starting to go numb', 'FREEZING'),
(3, '108', 'cartasEstado/108.png', 'Surprisingly, your body seems to be resisting the effects of the poison', 'POISONED'),
(4, '107', 'cartasEstado/107.png', 'You are sweating profusely and the madness of sheer terror fills your eyes', 'TERRIFIED'),
(5, '103', 'cartasEstado/103.png', 'Your heart jumps at even the faintest suspicious sound', 'FRIGHTENED')
;

#Insercción cartas inventario
INSERT INTO cartasInventario (id, numeroCarta, rutaImagen, textoCarta) VALUES 
(1, '001', 'cartasInventario/001.png', 'A small delicacy to recover energy'),
(2, '016', 'cartasInventario/016.png', 'A small metal gearwheel found in the lair of a crab'),
(3, '032', 'cartasInventario/032.png', 'A small metal gearwheel found on the body of a naval officer')
;


#Inserción acciones
INSERT INTO accion (id, tipo, descripcion, costeAccion, dificultadAccion, carta_num, iconoAccion) VALUES
(1, 'MOVE', 'Move to another terrain', 2, 0, '015', './iconosAcciones/move.png'),
(2, 'SEARCH', 'Examine', 0, 0, '015', './iconosAcciones/search.png'),
(3, 'MOVE', 'Move to another terrain', 1, 0, '009', './iconosAcciones/move.png'),
(4, 'OBSERVE', 'Observe something', 1, 0, '009', './iconosAcciones/observe.png'),
(5, 'MOVE', 'Move to another terrain', 1, 0, '007', './iconosAcciones/move.png'),
(6, 'OBSERVE', 'Observe something', 0 ,0, '007', './iconosAcciones/observe.png'),
(7, 'INVESTIGATE', 'Explore this area', 1, 0, '007', './iconosAcciones/investigate.png'),
(8, 'MOVE', 'Move to another terrain', 2 ,0, '004', './iconosAcciones/move.png'),
(9, 'INVESTIGATE', 'Explore this area', 1 ,0, '004', './iconosAcciones/investigate.png'),
(10, 'MOVE', 'Move to another terrain', 1 ,0, '010', './iconosAcciones/move.png'),
(11, 'INVESTIGATE', 'Explore this area', 1 ,0, '010', './iconosAcciones/investigate.png'),
(12, 'MOVE', 'Move to another terrain', 2 ,0, '006', './iconosAcciones/move.png'),
(13, 'OBSERVE', 'Observe something', 1 ,0, '006', './iconosAcciones/observe.png'),
(14, 'SEARCH', 'Examine', 0 ,0, '006', './iconosAcciones/search.png'),
(15, 'CLIMB', 'Show off your physical prowess.', 1 ,2, '005', './iconosAcciones/climb.png'),
(16, 'MOVE', 'Move to another terrain', 0, 0, '010g', './iconosAcciones/move.png'),
(17, 'HEAL', 'Heal yourself.', 2 ,3, '104', './iconosAcciones/heal.png'),
(18, 'EAT', 'Restore your energy', 0 ,0, '001', './iconosAcciones/eat.png'),
(19, 'HANDLE', 'Take this', 1 ,0, '037', './iconosAcciones/handle.png'),
(20, 'MOVE', 'Move to another terrain', 2 , 0, '024', './iconosAcciones/move.png'),
(21, 'SEARCH', 'Examine', 0 , 0, '024', './iconosAcciones/search.png'),
(22, 'SWIM', 'Jump into the water', 3 , 2, '018', './iconosAcciones/swim.png'),
(23, 'SEARCH', 'Examine', 1, 0, '011', './iconosAcciones/search.png'),
(24, 'BALANCE', 'Balance yourself', 1, 3, '031', './iconosAcciones/balance.png'),
(25, 'HUNT', 'Wait for your prey', 2, 2, '012', './iconosAcciones/hunt.png'),
(26, 'PULL', 'Use your strength', 1, 1, '022', './iconosAcciones/pull.png'),
(27, 'HEAL', 'Heal yourself', 2, 4, '108', './iconosAcciones/heal.png'),
(29, 'SING', 'Play music', 1, 4, '107', './iconosAcciones/sing.png'),
(30, 'THINK', 'Calmly meditate', 1, 3, '103', './iconosAcciones/think.png'),
(31, 'MOVE', 'Move to another terrain', 2 ,0, '006g', './iconosAcciones/move.png'),
(32, 'OBSERVE', 'Observe something', 1 ,0, '006g', './iconosAcciones/observe.png'),
(33, 'SEARCH', 'Examine', 0 ,0, '006g', './iconosAcciones/search.png')
;


#Inserción de consecuencias
INSERT INTO consecuencia (id, tipo, accion_id, esPositiva, cartaObjetivo) VALUES
(1, 'DESPLAZARSE', 1, 1, null),
(2, 'TRAER_CARTA', 2, 1, '022'),
(3, 'DESPLAZARSE', 3, 1, null),
(4, 'TRAER_CARTA', 4, 1, '034'),
(5, 'DESPLAZARSE', 5, 1, null),
(6, 'TRAER_CARTA', 6, 1, '011'),
(7, 'TRAER_CARTA', 7, 1, '018'),
(8, 'DESPLAZARSE', 8, 1, null),
(9, 'TRAER_CARTA', 9, 1, '008'),
(10, 'DESPLAZARSE', 10, 1, null),
(11, 'TRAER_CARTA', 11, 1, '005'),
(12, 'DESPLAZARSE', 12, 1, null),
(13, 'TRAER_CARTA', 13, 1, '012'),
(14, 'TRAER_CARTA', 14, 1, '016'),
(15, 'QUITAR_CARTA', 15, 1, '010'),
(16, 'QUITAR_CARTA', 15, 1, '005'),
(17, 'TRAER_CARTA', 15, 1, '010g'),
(18, 'DESPLAZARSE', 16, 1, null),
(19, 'TRAER_CARTA', 15, 0, '104'),
(20, 'QUITAR_CARTA', 17, 1, '104'),
(21, 'TRAER_CARTA', 17, 0, '108'),
(22, 'RESTAURAR', 18, 1, null),
(23, 'QUITAR_CARTA', 18, 1, '001'),
(25, 'GANAR', 19, 1, null),
(26, 'TRAER_CARTA', 22, 1, '001'),
(27, 'TRAER_CARTA', 22, 0, '102'),
(28, 'TRAER_CARTA', 23, 1, '031'),
(29, 'QUITAR_CARTA', 23, 1, '011'),
(30, 'TRAER_CARTA', 24, 1, '032'),
(31, 'QUITAR_CARTA', 24, 1, '031'),
(32, 'TRAER_CARTA', 24, 1, '139'),
(33, 'TRAER_CARTA', 24, 0, '102'),
(34, 'TRAER_CARTA', 25, 1, '001'),
(35, 'QUITAR_CARTA', 25, 1, '012'),
(36, 'TRAER_CARTA', 25, 1, '012g'),
(37, 'QUITAR_CARTA', 26, 1, '015'),
(38, 'TRAER_CARTA', 26, 1, '024'),
(39, 'QUITAR_CARTA', 26, 1, '022'),
(40, 'TRAER_CARTA', 26, 0, '022g'),
(41, 'QUITAR_CARTA', 27, 1, '108'),
(44, 'QUITAR_CARTA', 29, 1, '107'),
(45, 'QUITAR_CARTA', 30, 1, '103'),
(46, 'TRAER_CARTA', 30, 0, '107'),
(47, 'TRAER_CARTA', 21, 1, '037'),
(48, 'DESPLAZARSE', 31, 1, null),
(49, 'TRAER_CARTA', 32, 1, '012'),
(50, 'TRAER_CARTA', 33, 1, '016g'),
(51, 'QUITAR_CARTA', 14, 1, '006'),
(52, 'TRAER_CARTA', 14, 1, '006g'),
(53, 'DESPLAZARSE', 20, 1, null)
;

#Inserción cartas evento
INSERT INTO cartasEvento (id, numeroCarta, rutaImagen, textoCarta, posicionX, posicionY, id_cartaAsociada, id_accionDesactivada) VALUES 
(1, '005', 'cartasEvento/005.png', 'You stand before a nearly fifty-foot high rocky peak', 4, 2, 5, 11),
(2, '018', 'cartasEvento/018.png', 'The many reefs that surround the island would surely wreck any crack trying to approach or leave.', 3, 0, 3, 7),
(3, '011', 'cartasEvento/011.png', 'A man is lying face down among the rocks. Parts of the body are mutilated and the man´s skin bulges with what lokks like large egss', 4, 1, 3, 6),
(4, '031', 'cartasEvento/031.png', 'The waves violently pound the rocks, splashing your face with the sea spray. You inch towards the body, trying not to fall into the water', 4, 1, 3, 6),
(5, '139', 'cartasEvento/139.png', 'Judging by the insignias on his jacket, this man was once a French naval officer. You can still make out a name: "FT16 La Rochelle". Unfortunately, there is nothing else worthwhile on him.', 4, 1, 3, 6),
(6, '008', 'cartasEvento/008.png', 'The surf is rough and choppy, and the salt spray from the waves is enough to tell you the water is freezing.', 4, 3, 4, 9),
(7, '034', 'cartasEvento/034.png', 'You tear a piece of seaweed and give it a taste', 2, 1, 2, 4),
(8, '012', 'cartasEvento/012.png', 'You hide and wait in silence', 2, 4, 6, 13),
(9, '012g', 'cartasEvento/012g.png', 'There is not much more food in this desolate land than you have already found', 2, 4, 6, 13),
(10, '016g', 'cartasEvento/016g.png', 'You notice that the ground around the tunnel is loose and crumbling.', 1, 3, 9, 33),
(11, '022', 'cartasEvento/022.png', 'Unloading and sinking a submarine, of course, requires tremendous precision and power.', 0, 2, 1, 2),
(12, '022g', 'cartasEvento/022g.png', 'The mechanism hopelessly jammed and has stopped working. There´s no way to repair it and lower the submarine into the water', 0, 2, 1, 2),
(13, '037', 'cartasEvento/037.png', 'Quickly check the remaining fuel upon entering the control room. All you have to do is restart the engine and you will de able to leave this basted place', 0, 2, 7, 21)
;


#Insercción personajes
INSERT INTO personaje (id, nombre, habilidad, rutaCartaHistoria, rutaIconoPersonaje, rutaHistoriaPersonajeTxt) VALUES
(1, 'Ferdinand', 'INVESTIGATE', 'cartasPersonaje/character1Story.png', 'cartasPersonaje/characterToken2.png', './cartasPersonaje/historiaFerdinand.txt'),
(2, 'Mary Kingsley', 'SEARCH', 'cartasPersonaje/characterStory02.png', 'cartasPersonaje/characterToken02.png', './cartasPersonaje/historiaMary.txt');

