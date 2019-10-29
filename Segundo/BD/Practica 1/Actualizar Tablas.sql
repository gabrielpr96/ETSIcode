-- No puede haber dos o más rallies con el mismo nombre.
ALTER TABLE RALLY ADD CONSTRAINT rallyNombreUnico UNIQUE(nombre);

-- El número de kilómetros de los que consta un tramo siempre tiene que ser mayor o igual que 20.
ALTER TABLE TRAMO ADD CONSTRAINT tramoKmsValido CHECK(totalKms > 20);

-- La fecha de celebración de un rallie debe estar comprendida en 2009
ALTER TABLE RALLY ADD CONSTRAINT rallyFechaValida CHECK(Fecha > to_date('01/01/2009', 'dd/mm/yyyy') AND Fecha < to_date('31/12/2009', 'dd/mm/yyyy'));

-- En un rallie no puede haberdos tramos de igual longitud.
ALTER TABLE TRAMO ADD CONSTRAINT tramoLongitudesNoIguales UNIQUE(codRally, totalKms);

-- Al borrar un rally de la tabla RALLY se deben borrar además todos los tramos de los que consta dicho rally.
ALTER TABLE TRAMO DROP CONSTRAINT tramoAjena;
ALTER TABLE TRAMO ADD CONSTRAINT tramoAjena FOREIGN KEY (codRally) REFERENCES RALLY (codRally) ON DELETE CASCADE;