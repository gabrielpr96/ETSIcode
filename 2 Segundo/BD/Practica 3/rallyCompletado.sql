create or replace FUNCTION rallyCompletado (a_rally RALLY.nombre%TYPE, a_piloto PILOTO.nombrep%TYPE) RETURN NUMBER IS
  cod_piloto PILOTO.codPiloto%TYPE;
  cod_rally RALLY.codRally%TYPE;
  existe INTEGER;
  nTramos_total INTEGER;
  nTramos_corre INTEGER;
BEGIN
  SELECT COUNT(*) INTO existe FROM PILOTO WHERE nombreP=a_piloto;
  IF existe = 0 THEN RETURN 2; END IF;
  SELECT COUNT(*) INTO existe FROM RALLY WHERE nombre=a_rally;
  IF existe = 0 THEN RETURN 2; END IF;
  
  SELECT codPiloto INTO cod_piloto FROM PILOTO WHERE nombreP=a_piloto;
  SELECT codRally INTO cod_rally FROM RALLY WHERE nombre=a_rally;
  
  SELECT COUNT(*) INTO nTramos_total FROM TRAMO WHERE codRally=cod_rally;
  SELECT COUNT(*) INTO nTramos_corre FROM CORRE WHERE codPiloto=cod_piloto;
  
  IF nTramos_corre < nTramos_total THEN
    RETURN 0;
  ELSE
    RETURN 1;
  END IF;
END;