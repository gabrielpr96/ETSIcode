-- WRC-10. Nombre de los rallies en los que haya corrido algún vehículo en los que los copilotos no fuesen ni 'Marc Martí' ni 'Timo Rautiainen'
SELECT UNIQUE nombre
FROM WRC.RALLY INNER JOIN WRC.PARTICIPA USING(codRally) INNER JOIN WRC.PILOTO USING(codPiloto) INNER JOIN WRC.COCHE ON(coche = codCoche)
WHERE codCoche IN ( SELECT PI.coche
                    FROM WRC.PILOTO PI
                    WHERE PI.nombreCop <>ALL ('Marc Martí', 'Timo Rautiainen'))
;


-- WRC-11. Nombre de los pilotos que hayan participado en el Rally de Cataluña pilotando un Subaru y hayan acumulado menos de 10 segundos de penalización
SELECT PI.nombreP
FROM WRC.PILOTO PI
WHERE PI.codPiloto IN ( SELECT PA.codPiloto
                        FROM WRC.PARTICIPA PA
                        WHERE PA.penalizacion < 10
                              AND PA.codRally = ( SELECT RA.codRally
                                                  FROM WRC.RALLY RA
                                                  WHERE RA.nombre = 'Rally de Cataluña'))
      AND PI.coche IN ( SELECT CO.codCoche
                        FROM WRC.COCHE CO
                        WHERE CO.marca = 'Subaru')

;

-- WRC-12. Nombre de los rallies en los que haya corrido algún piloto con grupo sanguíneo A y rh +
SELECT RA.nombre
FROM WRC.RALLY RA
WHERE RA.codRally IN (SELECT PA.codRally
                      FROM WRC.PARTICIPA PA
                      WHERE PA.codPiloto IN ( SELECT PI.codPiloto
                                              FROM WRC.PILOTO PI
                                              WHERE PI.rh = '+' AND PI.grupoS = 'A'))
;

-- WRC-13. Nombre de los pilotos que hayan participado en alguno de los rallies que tengan algunos de sus tramos etiquetado como de dificultad C
SELECT PI.nombreP
FROM WRC.PILOTO PI
WHERE PI.codPiloto IN ( SELECT UNIQUE PA.codPiloto
                        FROM WRC.PARTICIPA PA
                        WHERE PA.codRally IN (SELECT UNIQUE TR.codRally
                                              FROM WRC.TRAMO TR
                                              WHERE TR.dificultad = 'C'))
;

-- WRC-14. Nombre de los pilotos que tengan más puntos que todos los pilotos que corren con un Subaru.
SELECT PI.nombreP
FROM WRC.PILOTO PI
WHERE PI.puntos >ALL (SELECT PI.puntos
                      FROM WRC.PILOTO PI
                      WHERE PI.coche IN ( SELECT CO.codCoche
                                          FROM WRC.COCHE CO
                                          WHERE CO.marca = 'Subaru'))
;

-- WRC-15. Modelo de coche, el nombre del rally y el nombre del piloto para aquellos pilotos que hayan participado en un rally que tenga algún tramo de menos de 30 kms
SELECT modelo, nombre, nombreP
FROM WRC.RALLY INNER JOIN WRC.PARTICIPA USING(codRally) INNER JOIN WRC.PILOTO USING(codPiloto) INNER JOIN WRC.COCHE ON(coche = codCoche)
WHERE codRally IN ( SELECT UNIQUE TR.codRally
                    FROM WRC.TRAMO TR
                    WHERE totalKms < 30)
;