create or replace FUNCTION ganador (a_rally RALLY.nombre%TYPE) RETURN PILOTO.nombrep%TYPE IS
  no_existe EXCEPTION;
  cod_piloto PILOTO.codPiloto%TYPE;
  nom_piloto PILOTO.nombrep%TYPE;
  cod_rally RALLY.codRally%TYPE;
  existe INTEGER;
  nTramos INTEGER;
BEGIN
  SELECT COUNT(*) INTO existe FROM RALLY WHERE nombre=a_rally;
  IF existe = 0 THEN RAISE no_existe; END IF;
  
  SELECT codRally INTO cod_rally FROM RALLY WHERE nombre=a_rally;
  
  SELECT COUNT(*) INTO nTramos FROM TRAMO WHERE codRally=cod_rally;
  
  SELECT codPiloto INTO cod_piloto FROM CORRE C1 WHERE codRally = cod_rally GROUP BY codPiloto HAVING COUNT(*) = nTramos
  AND SUM(tiempo) < ALL (SELECT SUM(tiempo) FROM CORRE C2 WHERE codRally = cod_Rally AND C1.codPiloto <> C2.codPiloto GROUP BY codPiloto HAVING COUNT(*) = nTramos);
  
  SELECT nombrep INTO nom_piloto FROM PILOTO WHERE codPiloto = cod_piloto;
  
  return nom_piloto;
EXCEPTION
  WHEN no_existe THEN
    RETURN 'NO EXISTEN DATOS';
  WHEN OTHERS THEN
    RETURN 'ERROR INESPERADO';
END;