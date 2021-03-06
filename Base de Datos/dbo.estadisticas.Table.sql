CREATE TABLE [dbo].[estadisticas](
	[id_partido] [char](10) NULL,
	[cod_jugador] [char](10) NULL,
	[num_pases] [int] NOT NULL,
	[num_goles] [int] NOT NULL,
	[num_asistencias] [int] NOT NULL,
	[dist_recorrida] [real] NOT NULL,
	[num_tarjeta_amar] [int] NOT NULL,
	[num_tarjeta_roja] [int] NOT NULL
) ON [PRIMARY]


ALTER TABLE [dbo].[estadisticas]
ADD  CONSTRAINT [FK_estadisticas_jugador] FOREIGN KEY([cod_jugador])
REFERENCES [dbo].[jugador] ([cod_jugador])


ALTER TABLE [dbo].[estadisticas]
CONSTRAINT [FK_estadisticas_partido] FOREIGN KEY([id_partido])
REFERENCES [dbo].[partido] ([id_partido])
