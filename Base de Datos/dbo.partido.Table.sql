CREATE TABLE C##JOEL.PARTIDO
(
	id_partido char(10) NOT NULL,
	fecha_hora DATE NULL,
	lugar char(20) NOT NULL,
	estadio char(20) NOT NULL,
	arbitro_designado char(20) NULL,
	resultado char(3) NOT NULL,
	estado_partido NUMBER(6,0) NOT NULL,
	nombre_equipo_rival char(20) NOT NULL,
CONSTRANUMBER(6,0) PK_PARTIDO, 
PRIMARY KEY(id_partido));