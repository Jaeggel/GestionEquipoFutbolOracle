
CREATE TABLE [dbo].[detalle_partido](
	[id_partido] [char](10) NULL,
	[cod_jugador] [char](10) NULL
)


ALTER TABLE [dbo].[detalle_partido]  
ADD  CONSTRAINT [FK_detalle_partido_jugador] FOREIGN KEY([cod_jugador])
REFERENCES [dbo].[jugador] ([cod_jugador])


ALTER TABLE [dbo].[detalle_partido]
ADD  CONSTRAINT [FK_detalle_partido_partido] FOREIGN KEY([id_partido])
REFERENCES [dbo].[partido] ([id_partido])


