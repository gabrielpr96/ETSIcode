--WRC-16. Nombre de los pilotos que no han corrido el rally de Catalu�a
SELECT PI.nombreP
FROM WRC.PILOTO PI
WHERE NOT EXISTS (SELECT *
                  FROM WRC.PARTICIPA INNER JOIN WRC.RALLY USING(codRally)
                  WHERE PI.codPiloto =  codPiloto AND nombre = 'Rally de Catalu�a')
;
                  

--WRC-17. Nombre de los pilotos que tengan m�s puntos que alg�n otro piloto que conduzca un coche con igual o menor cilindrada
SELECT P1.nombreP
FROM WRC.PILOTO P1 INNER JOIN WRC.COCHE C1 ON(coche = codCoche)
WHERE EXISTS( SELECT *
              FROM WRC.PILOTO P2 INNER JOIN WRC.COCHE C2 ON(coche = codCoche)
              WHERE P1.puntos > P2.puntos AND C1.cilindrada >= C2.cilindrada)
;

--WRC-18. Nombre de los pilotos que hayan corrido el Rally de Catalu�a pero no el de Gran Breta�a
SELECT PI.nombreP
FROM WRC.PILOTO PI
WHERE EXISTS( SELECT *
              FROM WRC.PARTICIPA INNER JOIN WRC.RALLY USING(codRally)
              WHERE PI.codPiloto = codPiloto AND nombre = 'Rally de Catalu�a')
      AND NOT EXISTS( SELECT *
                      FROM WRC.PARTICIPA INNER JOIN WRC.RALLY USING(codRally)
                      WHERE PI.codPiloto = codPiloto AND nombre = 'Rally RAC de Gran Breta�a')
;

--WRC-19. Nombre de los pilotos que hayan corrido alg�n tramo de dificultad A, pero no hayan conseguido finalizar alg�n tramo de menos de 30 kms
SELECT PI.nombreP
FROM WRC.PILOTO PI
WHERE EXISTS( SELECT *
              FROM WRC.CORRE INNER JOIN WRC.TRAMO USING(codRally, numeroTramo)
              WHERE PI.codPiloto = codPiloto AND dificultad = 'A')
      AND NOT EXISTS( SELECT *
                      FROM WRC.CORRE INNER JOIN WRC.TRAMO USING(codRally, numeroTramo)
                      WHERE PI.codPiloto = codPiloto AND totalKms < 30)
;

--WRC-20. Marca y modelo de los coches que tienen una cilindrada mayor que, al menos, otros dos coches
SELECT C1.marca, C1.modelo
FROM WRC.COCHE C1
WHERE ( SELECT COUNT(*)
        FROM WRC.COCHE C2
        WHERE C1.cilindrada > C2.cilindrada AND C1.codCoche <> C2.codCoche) >= 2
;

SELECT C1.marca, C1.modelo
FROM WRC.COCHE C1
WHERE EXISTS(SELECT *
             FROM WRC.COCHE C2
             WHERE C1.codCoche <> C2.codCoche AND C1.cilindrada > C2.cilindrada AND EXISTS( SELECT *
                                                                                            FROM WRC.COCHE C3
                                                                                            WHERE C3.codCoche <> C2.codCoche AND C3.codCoche <> C1.codCoche AND C1.cilindrada > C3.cilindrada))
;

