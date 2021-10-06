--EI-1. C�digo, nombre y especialidad de las asignaturas de tercero que tengan m�s de 4,5 cr�ditos
SELECT A.idAsig AS codigo, A.nombre, A.esp AS especialidad
FROM EI.ASIGNATURA A
WHERE A.cr�ditos > 4.5
;

--EI-2. Toda la informaci�n disponible de todos los profesores 
SELECT *
FROM EI.PROFESOR
;

--EI-3. Distintos tipos de ordenadores que existen en la Escuela
SELECT DISTINCT O.tipo
FROM EI.ORDENADOR O
;

--EI-4. Nombre de los alumnos, el n�mero de hermanos y el descuento que le corresponde (el descuento es de 300 � por hermano), ordenados de mayor a menor seg�n el descuento, y en caso de igualdad, ordenados alfab�ticamente por nombre 
SELECT A.nombre, A.nh AS numeroDeHermanos, A.nh*300 AS descuento
FROM EI.ALUMNO A
ORDER BY A.nh DESC, A.nombre ASC
;

--EI-5. Dni y nombre de los alumnos nacidos entre 1970 y 1974, y cuya localidad de nacimiento sea Huelva o C�diz
SELECT A.dni, A.nombre
FROM EI.ALUMNO A
WHERE (EXTRACT(YEAR FROM A.fechaNac) >= 1970 AND EXTRACT(YEAR FROM A.fechaNac) <= 1974) AND (A.lugar = 'Huelva' OR A.lugar = 'C�diz')
;

--EI-6. Nnombre de todos los alumnos cuyo nombre empiece por la letra M y hayan nacido en una ciudad cuyo nombre tenga, al menos, 6 caracteres pero no comience por la letra P
SELECT A.nombre
FROM EI.ALUMNO A
WHERE A.nombre LIKE('M%') AND A.lugar LIKE('______%') AND A.lugar NOT LIKE('P%')
;

--EI-7. A�os de nacimiento de los alumnos, ordenados crecientemente
SELECT DISTINCT EXTRACT(YEAR FROM A.fechaNac) AS anioNacimiento
FROM EI.ALUMNO A
ORDER BY EXTRACT(YEAR FROM A.fechaNac) ASC
;

--EI-8. Nombres de las asignaturas junto con el nombre del profesor responsable
SELECT A.nombre AS nombreAsignatura, P.nombre AS nombreProfesor
FROM EI.ASIGNATURA A INNER JOIN EI.PROFESOR P ON(nPr = prof)
;

--EI-9. N�meros de los alumnos que se han matriculado en Bases de Datos I en el curso 2002-03
SELECT alum
FROM EI.MATRICUlA INNER JOIN EI.ASIGNATURA USING(idAsig)
WHERE a�o = 2002
;

--EI-10. Nombres de los alumnos que han aprobado la asignatura Algoritmos y Estructuras de Datos I en la convocatoria de febrero_junio de 2001
SELECT nombre
FROM EI.MATRICUlA INNER JOIN EI.ALUMNO ON(alum = nAl)
WHERE a�o = 2001 AND feb_jun > 5
;

--EI-11. N�mero de despacho, pero s�lo de aquellos donde hay al menos 2 profesores
SELECT UNIQUE despacho
FROM EI.PROFESOR P1 INNER JOIN EI.PROFESOR P2 USING(despacho)
WHERE P1.nPr <> P2.nPr
;

--EI-12. Obtener una lista con todas las asignaturas de las que es responsable o docente la profesora Dolores Toscano Barriga
SELECT A.nombre
FROM EI.ASIGNATURA A INNER JOIN EI.PROFESOR P ON(prof = nPr)
WHERE P.nombre = 'Dolores Toscano Barriga'
UNION
SELECT A.nombre
FROM EI.ASIGNATURA A INNER JOIN EI.MATRICULA M USING(prof) INNER JOIN EI.PROFESOR P ON(prof = nPr)
WHERE P.nombre = 'Dolores Toscano Barriga'
;

--EI-13. Obtener los nombres de los alumnos que no se han presentado a �Bases de Datos I� en diciembre de 2002 por haberla aprobado en una convocatoria anterior del mismo a�o
SELECT AL.nombre
FROM EI.MATRICULA M INNER JOIN EI.ALUMNO AL ON(alum = nAl) INNER JOIN EI.ASIGNATURA A USING(idAsig)
WHERE A.nombre = 'Bases de Datos I' AND a�o = 2002 AND sep >= 5
;

--EI-14. Alumnos que hayan nacido en la misma ciudad que Samuel Toscano Villegas y tengan el mismo n�mero de hermanos que Beatriz Rico V�zquez
SELECT nombre
FROM EI.ALUMNO
WHERE lugar = ( SELECT lugar
                FROM EI.ALUMNO
                WHERE nombre = 'Samuel Toscano Villegas')
    AND nH = ( SELECT nh
                FROM EI.ALUMNO
                WHERE nombre = 'Beatriz Rico V�zquez')
;

--EI-15. Alumnos matriculados en BDII y no en BDI
SELECT nombre
FROM EI.ALUMNO
WHERE nAl IN(   SElECT alum
                FROM EI.MATRICULA INNER JOIN EI.ASIGNATURA USING(idAsig)
                WHERE nombre = 'Bases de Datos II')
    AND nAl NOT IN( SELECT alum
                    FROM EI.MATRICULA INNER JOIN EI.ASIGNATURA USING(idAsig)
                    WHERE nombre = 'Bases de Datos I')
;

--EI-16.Profesores que tienen m�s antig�edad que alguno de los profesores del despacho FC-7366 (sin contar los del propio despacho FC-7366)
SELECT nombre
FROM EI.PROFESOR
WHERE ant <ANY (SELECT ant
                FROM EI.PROFESOR
                WHERE despacho = 'FC-7366')
;

--EI-17. Nombres de los alumnos que hayan sacado una nota m�s alta en septiembre que la m�s alta de las notas de febrero/junio, en el a�o 2002 y en la asignatura Bases de Datos I
SELECT AL.nombre
FROM EI.ALUMNO AL INNER JOIN EI.MATRICULA ON(nAl = alum) INNER JOIN EI.ASIGNATURA A USING(idAsig)
WHERE sep >ALL (SELECT feb_jun
                FROM EI.MATRICULA INNER JOIN EI.ASIGNATURA USING(idAsig)
                WHERE a�o = 2002 AND nombre = 'Bases de Datos I' AND feb_jun IS NOT NULL)
    AND A.nombre = 'Bases de Datos I' and a�o = 2002
;

--EI-18. Despachos donde hay profesores que no son responsables de ninguna asignatura
SELECT P.despacho
FROM EI.PROFESOR P
WHERE NOT EXISTS(   SELECT *
                    FROM EI.ASIGNATURA
                    WHERE P.nPr = prof)
;

--EI-19. Alumnos que se han matriculado de alguna asignatura en el a�o 2000 o 2002, y de ninguna asignatura en el a�o 2001
SELECT A.nombre
FROM EI.ALUMNO A
WHERE EXISTS(   SELECT *
                FROM EI.MATRICULA
                WHERE A.nAl = alum AND (a�o = 2000 OR a�o = 2002))
    AND NOT EXISTS( SELECT *
                    FROM EI.MATRICULA
                    WHERE A.nAl = alum AND a�o = 2001)
;

--EI-20. Nombre de los profesores y los n�meros de sus despachos de aquellos profesores que no comparten despacho
SELECT P1.nombre, P1.despacho
FROM EI.PROFESOR P1
WHERE NOT EXISTS(   SELECT *
                    FROM EI.PROFESOR P2
                    WHERE P1.nPr <> P2.nPr AND P1.despacho = P2.despacho)
;

--EI-21. Nombres de TODOS los profesores junto con las asignaturas de las que son responsables
SELECT P.nombre, A.nombre
FROM EI.PROFESOR P LEFT OUTER JOIN EI.ASIGNATURA A ON(nPr = prof)
;

--EI-22. Cu�ntos despachos ocupan los profesores de la escuela 
SELECT COUNT(DISTINCT despacho)
FROM EI.PROFESOR
;

--EI-23. N�mero de matriculados, la nota m�xima, la m�nima y la nota media de la asignatura �Bases de Datos I� en la convocatoria de septiembre de 2002
SELECT COUNT(alum) AS numeroDeMatriculados, MAX(sep) AS Maxima, MIN(sep) AS Minima, AVG(sep) AS Media
FROM EI.MATRICULA INNER JOIN EI.ASIGNATURA USING(idAsig)
WHERE a�o = 2002 AND nombre = 'Bases de Datos I'
;

--EI-24. Nombres de las asignaturas que tienen recomendadas 2 o m�s asignaturas. 
SELECT A.nombre
FROM EI.ASIGNATURA A
WHERE ( SELECT COUNT(*)
        FROM EI.RECOMENDACIONES
        WHERE idAsig1 = A.idAsig) >=2
;

--EI-25. Nombres de los profesores que tienen m�s antig�edad que, al menos, otros cinco profesores.
SELECT P1.nombre
FROM EI.PROFESOR P1
WHERE ( SELECT COUNT(*)
        FROM EI.PROFESOR P2
        WHERE P1.ant < P2.ant) >= 5
;

--EI-26. Para cada asignatura y a�o acad�mico, mostrar el nombre de la asignatura, el a�o, el n�mero de alumnos que se han presentado y la nota media obtenida en la convocatoria de febrero_junio
SELECT nombre, a�o, COUNT(*), AVG(feb_jun)
FROM EI.MATRICULA INNER JOIN EI.ASIGNATURA USING(idAsig)
GROUP BY nombre, a�o
;

--EI-27. Obtener un listado con el nombre del alumno, el nombre de la asignatura y el n�mero de veces que se ha matriculado en esa asignatura, pero s�lo cuando se haya matriculado 3 o m�s a�os
SELECT AL.nombre AS nombreAlmuno, ASIG.nombre AS nombreAsignatura, count(*) AS nMatriculas
FROM EI.MATRICULA INNER JOIN EI.ASIGNATURA ASIG USING(idAsig) INNER JOIN EI.ALUMNO AL ON(nAl = alum)
GROUP BY AL.nombre, ASIG.nombre
HAVING count(*) >= 3
;

--EI-28. Nombre de los alumnos que hayan sacado m�s de un 5 de nota media en junio del 2002
SELECT nombre
FROM EI.MATRICULA INNER JOIN EI.ALUMNO ON(nAl = alum)
WHERE a�o=2002
GROUP BY nombre
HAVING AVG(feb_jun) > 5
;

--EI-29. Nombre de las asignaturas y n�mero de alumnos matriculados de las asignaturas donde se hayan matriculado m�s alumnos en el a�o 2002
SELECT nombre
FROM EI.MATRICULA INNER JOIN EI.ASIGNATURA USING(idAsig)
WHERE a�o=2002
GROUP BY nombre
HAVING count(*)>=ALL(SELECT count(*)
                    FROM EI.MATRICULA
                    WHERE a�o=2002
                    GROUP BY idAsig)
;

--EI- 30. Obtener el n�mero total de alumnos que han suspendido en cada asignatura en junio de 2002, pero s�lo de aquellas asignaturas en las que se hayan matriculado m�s de 50 alumnos
SELECT count(*)
FROM EI.MATRICULA
WHERE feb_jun < 5 AND a�o=2002 AND idAsig IN(SELECT idAsig
                                                FROM EI.MATRICULA
                                                GROUP BY idAsig
                                                HAVING count(*) > 50)
GROUP BY idAsig

