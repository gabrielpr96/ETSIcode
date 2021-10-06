INSERT INTO RALLY VALUES ('R001', 'Rally de Cataluña', 'España', to_date('05/09/2009', 'dd/mm/yyyy'));
INSERT INTO RALLY VALUES ('R002', 'Rally 1000 Lagos', 'Finlandia', to_date('03/08/2009', 'dd/mm/yyyy'));

INSERT INTO TRAMO VALUES ('R001', 1, 50.3, 'A');
INSERT INTO TRAMO VALUES ('R001', 2, 40.2, 'B');
INSERT INTO TRAMO VALUES ('R001', 3, 30.0, 'A');
INSERT INTO TRAMO VALUES ('R002', 1, 50.5, 'A');
INSERT INTO TRAMO VALUES ('R002', 2, 40.2, 'B');

--Comprobar
SELECT * FROM RALLY;
SELECT * FROM TRAMO;


-- ORA-00001 : Ya hay un rally con ese código. (codRally es clave primaria, eso la convierte en NOT NULL y UNIQUE)
INSERT INTO RALLY VALUES ('R001', 'Rally San Remo', 'Italia', to_date('18/05/2009', 'dd/mm/yyyy'));

-- ORA-00001 : Ya hay un rally con ese nombre (Nombre tiene el atributo UNIQUE)
INSERT INTO RALLY VALUES ('R003', 'Rally de Cataluña', 'Italia', to_date('19/09/2009', 'dd/mm/yyyy'));

-- ORA-02290 : La fecha no está comprendida entre el rango válido (Hay un check que comprueba que esté en 2009)
INSERT INTO RALLY VALUES ('R004', 'Rally de Andalucia', 'España', to_date('01/10/1997', 'dd/mm/yyyy'));

-- ORA-02291 : No existe el rally con código R005 (codRally de TRAMO es clave ajena de codRally de RALLY)
INSERT INTO TRAMO VALUES ('R005', 1, 50, 'A');

-- ORA-02290 : La longitud es menor de 20km (Hay un CHECK que comprueba que sea mayor que 20)
INSERT INTO TRAMO VALUES ('R001', 4, 10, 'A');

-- ORA-00001 : Ya hay un tramo del rally R001 que mide 40.2km (La combinación codRally con totalKms es UNIQUE)
INSERT INTO TRAMO VALUES ('R001', 5, 40.2, 'A');


-- Debería realizar un borrado en cascada (Desaparecen las tuplas de TRAMO que tenian codRally R002)
DELETE FROM RALLY WHERE codRally = 'R002';
-- Comprobar con esto
SELECT * FROM TRAMO;