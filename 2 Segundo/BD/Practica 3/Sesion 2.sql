SET serveroutput ON;


Execute dbms_output.put_line(ganador('Rally de Catalu�a'));

SELECT codRally FROM RALLY WHERE nombre='Rally de Catalu�a';
SELECT COUNT(*) FROM TRAMO WHERE codRally='R001';

SELECT codPiloto, SUM(tiempo) AS tTotal, COUNT(numeroTramo)  FROM CORRE C1 WHERE codRally='R001' GROUP BY codPiloto HAVING COUNT(*)=3
AND SUM(tiempo) < ALL (SELECT SUM(tiempo) FROM CORRE C2 WHERE codRally='R001' AND C1.codPiloto <> C2.codPiloto GROUP BY codPiloto HAVING COUNT(*)=3);


Execute dbms_output.put_line(rallyCompletado('Rally de Catalu�a', 'Dani Sordo'));
Execute dbms_output.put_line(rallyCompletado('Rally de Catalu�a','Juan Perez'));

INSERT INTO TRAMO VALUES ('R001', 4, 50, 'A');
INSERT INTO CORRE VALUES ('P001','R001',4,10);
UPDATE CORRE SET tiempo=20 WHERE codRally='R001' AND codPiloto='P001' AND numerotramo=4 AND tiempo=10;
DELETE FROM CORRE WHERE codRally='R001' AND codPiloto='P001' AND numerotramo=4;

SELECT * FROM PARTICIPA WHERE codRally='R001' AND codPiloto='P001';
SELECT codRally, codPiloto, SUM(tiempo) FROM CORRE WHERE codRally='R001' AND codPiloto='P001' GROUP BY codRally, codPiloto;

INSERT INTO CORRE VALUES ('P002','R002',4,10);