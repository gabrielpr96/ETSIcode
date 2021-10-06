--WRC_1 Todos los datos de los rallies que se celebren antes del 1 de octubre de 2009, en un pa�s que tenga m�s de 6 caracteres de longitud
SELECT *
FROM WRC.RALLY R
WHERE fecha < '01/10/09' AND pais LIKE '_______%';

--WRC_2 El nombre de todos los pilotos y sus copilotos, ordenados alfab�ticamente por el nombre de piloto, pero s�lo de aquellos pilotos cuyo nombre no comience por M
SELECT nombreP AS Piloto, nombreCop AS Copiloto 
FROM WRC.PILOTO P
WHERE nombreP NOT LIKE 'M%'
ORDER BY nombreP;

--WRC_3 El nombre de los pilotos y su grupo sangu�neo, pero s�lo de aquellos cuyo nombre empiece por M y su grupo sangu�neo tenga 2 caracteres
SELECT nombreP AS Nombre, grupoS AS GrupoSanguineo
FROM WRC.PILOTO P
WHERE nombreP LIKE 'M%' AND grupoS LIKE '__';

--WRC_4 Los nombres y fechas de celebraci�n de los rallies que se corran en la segunda mitad  del mes de Agosto
SELECT nombre, TO_CHAR(fecha, 'dd/mm/yy') AS Fecha
FROM WRC.RALLY R
WHERE EXTRACT(MONTH FROM fecha) = 8 AND EXTRACT(DAY FROM fecha) >= 15 AND EXTRACT(DAY FROM fecha) <= 31;