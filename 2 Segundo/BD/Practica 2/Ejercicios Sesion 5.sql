--WRC-21. Nombre de los pilotos que hayan corrido todos los tramos que ha finalizado Marcus Gronholm en el Rally de Montecarlo (excluyendo, claro, al propio Marcus Gronholm)
SELECT nombreP
FROM WRC.PILOTO PI
WHERE NOT EXISTS( SELECT *
                  FROM WRC.RALLY INNER JOIN WRC.CORRE USING(codRally) INNER JOIN WRC.PILOTO USING(codPiloto)
                  WHERE nombreP = 'Marcus Gronholm' AND NOT EXISTS( SELECT *
                                                                    FROM WRC.RALLY INNER JOIN WRC.CORRE USING(codRally) 
                                                                    WHERE PI.codPiloto = codPiloto
                                                                        AND nombre = 'Rally de Montecarlo'
                                                                  )
                        AND nombre = 'Rally de Montecarlo'
                  )
      AND nombreP <> 'Marcus Gronholm'
;

--WRC-22. Nombre de los rallies junto con el n�mero de tramos de cada uno de ellos
SELECT nombre, COUNT(numeroTramo)
FROM WRC.RALLY INNER JOIN WRC.TRAMO USING(codRally)
GROUP BY nombre;

--WRC-23. Para cada rally, nombre del rally, n�mero del tramo y los tiempos m�ximo y m�nimo invertidos en dicho tramo, ordenados, dentro de cada rally, desde el menor al mayor tiempo m�nimo invertido
SELECT nombre, numeroTramo, MAX(tiempo) AS t_max, MIN(tiempo) AS t_min
FROM WRC.RALLY INNER JOIN WRC.CORRE USING(codRally)
GROUP BY nombre, numeroTramo
ORDER BY nombre, t_min ASC
;

--WRC-24. Para cada rally, Nombre del rally, nombre de los pilotos que hayan corrido alg�n tramo de dicho rally y el tiempo total invertido por cada piloto en cada rally
SELECT nombre, nombreP, SUM(tiempo) AS tiempoTotal
FROM WRC.RALLY INNER JOIN WRC.CORRE USING(codRally) INNER JOIN WRC.PILOTO USING(codPiloto)
GROUP BY nombre, nombreP
ORDER BY nombre
;

--WRC-25. Nombre del piloto, n�mero de tramos completados y el total de kil�metros de cada piloto que ha corrido en el Rally de Montecarlo ordenados de mayor a menor kilometraje recorrido
SELECT nombreP, COUNT(numeroTramo), SUM(totalKms) AS totalKilometros
FROM WRC.RALLY INNER JOIN WRC.TRAMO USING(codRally) INNER JOIN WRC.CORRE USING(codRally, numeroTramo) INNER JOIN WRC.PILOTO USING(codPiloto)
WHERE nombre = 'Rally de Montecarlo'
GROUP BY nombreP
ORDER BY totalKilometros DESC
;

--WRC-26. Para cada rally cuyo total de kil�metros de recorrido sea mayor que 100, mostrar su c�digo y su media de kil�metros
SELECT codRally, AVG(totalKms)
FROM WRC.RALLY INNER JOIN WRC.TRAMO USING(codRally)
GROUP BY codRally
HAVING SUM(totalKms) > 100
;

--WRC-27. Nombre de los rallies junto con el n�mero de tramos de cada uno de ellos, pero s�lo aquellos rallies que tengan m�s de 2 tramos
SELECT nombre, COUNT(numeroTramo) AS numeroDeTramos
FROM WRC.RALLY INNER JOIN WRC.TRAMO USING(codRally)
GROUP BY nombre
HAVING numeroDeTramos > 2
;

--WRC-28. Nombre, grupo sangu�neo, rh y n�mero de puntos del piloto que ha realizado m�s tramos de dificultad A
SELECT nombreP, grupos, rh, puntos
FROM WRC.TRAMO INNER JOIN WRC.CORRE USING(codRally, numeroTramo) INNER JOIN  WRC.PILOTO P1 USING(codPiloto)
WHERE dificultad = 'A'
GROUP BY nombreP, grupos, rh, puntos
HAVING COUNT(numeroTramo) >=ALL(SELECT COUNT(numeroTramo)
                                FROM WRC.TRAMO INNER JOIN WRC.CORRE USING(codRally, numeroTramo) INNER JOIN  WRC.PILOTO P1 USING(codPiloto)
                                WHERE dificultad = 'A'
                                GROUP BY nombreP
                                )
;



