SET serveroutput ON;

CALL hola('Mundo');

SELECT nombreP, penalizacion
FROM PARTICIPA INNER JOIN RALLY USING(codRally) INNER JOIN PILOTO USING(codPiloto)
WHERE codPiloto = '' AND codRally = ''
;

UPDATE PARTICIPA SET penalizacion=penalizacion+120 WHERE codRally='R001' AND codPiloto='P001' ;

SELECT * FROM PARTICIPA;
SELECT * FROM RALLY;

CALL penalizar('Dani Sordo','Rally de Cataluña',300);

SELECT numeroTramo, MIN(tiempo) AS mejorTiempo
FROM CORRE
WHERE codRally = 'R006'
GROUP BY numeroTramo
ORDER BY mejorTiempo
;

CALL mejor_piloto_tramo('Sebastien Loeb');