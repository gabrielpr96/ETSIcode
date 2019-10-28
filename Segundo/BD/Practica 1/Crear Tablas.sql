CREATE TABLE RALLY (
  codRally CHAR(4),
  Nombre VARCHAR2(50),
  Pis VARCHAR2(20),
  Fecha DATE,
  CONSTRAINT rallyClave PRIMARY KEY (codRally)
);

CREATE TABLE TRAMO (
  codRally CHAR(4),
  numeroTramo NUMBER(38, 0),
  totalKms NUMBER(5, 2),
  Dificultad CHAR(1) DEFAULT 'B' NOT NULL,
  CONSTRAINT tramoClave PRIMARY KEY (codRally, numeroTramo),
  CONSTRAINT tramoAjena FOREIGN KEY (codRally) REFERENCES RALLY (codRally),
  CONSTRAINT tramoDificultadesValidas CHECK(Dificultad IN ('A','B','C'))
);

CREATE TABLE COCHE (
  codCoche CHAR(4),
  marca VARCHAR2(10),
  modelo VARCHAR2(20),
  cilindrada NUMBER(38, 0),
  CONSTRAINT cocheClave PRIMARY KEY (codCoche),
  CONSTRAINT cocheCilindradasValidas CHECK(cilindrada >= 2000 AND cilindrada <= 3000)
);

CREATE TABLE PILOTO (
  codPiloto CHAR(4),
  nombreP VARCHAR2(50) NOT NULL UNIQUE,
  grupoS CHAR(2),
  rh CHAR(1),
  nombreCop VARCHAR(50) NOT NULL UNIQUE,
  coche CHAR(4) NOT NULL UNIQUE,
  puntos NUMBER(38, 0) DEFAULT 0,
  CONSTRAINT pilotoClave PRIMARY KEY (codPiloto),
  CONSTRAINT pilotoClaveAjena FOREIGN KEY (coche) REFERENCES COCHE (codCoche),
  CONSTRAINT pilotoPilotoNoPuedeSerCop CHECK(nombreP != nombreCop),
  CONSTRAINT pilotoPuntosValidos CHECK(puntos >= 0),
  CONSTRAINT pilotoGrupoSanguineoValido CHECK(grupoS IN ('A', 'B', 'AB', '0')),
  CONSTRAINT pilotoRhValido CHECK(rh IN ('+', '-'))
);

CREATE TABLE PARTICIPA (
  codRally CHAR(4),
  codPiloto CHAR(4),
  penalizacion NUMBER(38, 0) DEFAULT 0 NOT NULL,
  tiempoRally NUMBER(38, 0) DEFAULT 0 NOT NULL,
  CONSTRAINT participaClave PRIMARY KEY (codRally, codPiloto),
  CONSTRAINT participaAjenaRally FOREIGN KEY (codRally) REFERENCES RALLY (codRally),
  CONSTRAINT participaAjenaPiloto FOREIGN KEY (codPiloto) REFERENCES PILOTO (codPiloto),
  CONSTRAINT participaPenalizacionValida CHECK(penalizacion >= 0),
  CONSTRAINT participaTiempoRallyValida CHECK(tiempoRally >= 0)
);

CREATE TABLE CORRE (
  codPiloto CHAR(4),
  codRally CHAR(4),
  numeroTramo NUMBER(38, 0),
  tiempo NUMBER(38, 0) NOT NULL,
  CONSTRAINT correClave PRIMARY KEY (codPiloto, codRally, numeroTramo),
  CONSTRAINT correAjenaCodRallyNumeroTramo FOREIGN KEY (codRally, numeroTramo) REFERENCES TRAMO (codRally, numeroTramo),
  CONSTRAINT correAjenaCodPiloto FOREIGN KEY (codPiloto) REFERENCES PILOTO (codPiloto),
  CONSTRAINT correTiempoValido CHECK(tiempo >= 0)
);