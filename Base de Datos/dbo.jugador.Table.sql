CREATE TABLE [dbo].[jugador](
	[cod_jugador] [char](10) NOT NULL,
	[nombres_jugador] [char](20) NOT NULL,
	[apellidos_jugador] [char](20) NOT NULL,
	[nacionalidad] [char](20) NOT NULL,
	[duracion_contrato] [char](20) NOT NULL,
	[fecha_nac] [date] NOT NULL,
	[posicion] [char](20) NOT NULL,
	[num_camiseta] [int] NOT NULL,
CONSTRAINT [PK_JUGADOR] 
PRIMARY KEY (cod_jugador))
