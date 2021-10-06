--WRC-5. Números de tramo (codigo) con dificultad B del Rally de Montecarlo
SELECT TR.numeroTramo AS NumeroTramo 
FROM WRC.TRAMO TR INNER JOIN WRC.RALLY RA USING(codRally)
WHERE TR.dificultad = 'B' AND RA.nombre = 'Rally de Montecarlo';

--WRC-6. Muestra el nombre de los pilotos con RH negativo junto con la marca y modelo de su coche, siempre que la cilindrada de éste supere los 2500cc
SELECT PI.nombreP AS Nombre, CO.marca AS Marca, CO.modelo AS Modelo
FROM WRC.PILOTO PI INNER JOIN WRC.COCHE CO ON PI.coche = CO.codCoche 
WHERE PI.rh = '-' AND CO.cilindrada > 2500;

--WRC-7. Muestra el nombre de todos los copilotos que participan en el Rally de Cataluña y que sufran una penalización de más de 10 segundos
SELECT PI.nombreCop AS NombreCopiloto
FROM (WRC.PILOTO PI INNER JOIN WRC.PARTICIPA PA USING(codPiloto)) INNER JOIN WRC.RALLY RA USING(codRally)
WHERE RA.nombre = 'Rally de Cataluña' AND PA.penalizacion > 10;

--WRC-8. Muestra  un listado con el nombre de los pilotos, el nombre de los rallies en los que ha participado y la fecha de celebración de dicho rally, pero sólo de aquellos rallies en los que el piloto haya tenido menos de 20 segundos de penalización y se celebrasen en los meses de Agosto o Noviembre
SELECT PI.nombreP As NombrePiloto, RA.nombre AS NombreRally, RA.fecha AS Fecha
FROM (WRC.PILOTO PI INNER JOIN WRC.PARTICIPA PA USING(codPiloto)) INNER JOIN WRC.RALLY RA USING(codRally) 
WHERE PA.penalizacion < 20 AND (EXTRACT(MONTH FROM RA.fecha) = 8 OR EXTRACT(MONTH FROM RA.fecha) = 11);

--WRC-9. Muestra un listado con el nombre y fecha de celebración de aquellos rallies en los que haya participado algún piloto con un coche de la marca Citroen
SELECT UNIQUE RA.nombre AS Nombre, RA.fecha AS Fecha
FROM ((WRC.RALLY RA INNER JOIN WRC.PARTICIPA PA USING(codRally)) INNER JOIN WRC.PILOTO PI USING(codPiloto)) INNER JOIN WRC.COCHE CO ON PI.coche = CO.codCoche
WHERE CO.marca = 'Citroen';