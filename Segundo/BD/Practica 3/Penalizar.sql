CREATE OR REPLACE
PROCEDURE penalizar (a_piloto PILOTO.nombreP%TYPE, a_rally RALLY.nombre%TYPE, a_cantidad PARTICIPA.penalizacion%TYPE) IS
  e_piloto_no_encontrado EXCEPTION;
  e_rally_no_encontrado EXCEPTION;
  cod_piloto PILOTO.codPiloto%TYPE;
  cod_rally RALLY.codRally%TYPE;
  existe INTEGER;
  CURSOR c_penalizacion IS
    SELECT nombreP, penalizacion
    FROM PARTICIPA INNER JOIN RALLY USING(codRally) INNER JOIN PILOTO USING(codPiloto)
    WHERE codRally = cod_rally
  ;
BEGIN
  SELECT COUNT(*) INTO existe FROM PILOTO WHERE nombreP=a_piloto;
  IF existe = 0 THEN RAISE e_piloto_no_encontrado; END IF;
  SELECT COUNT(*) INTO existe FROM RALLY WHERE nombre=a_rally;
  IF existe = 0 THEN RAISE e_rally_no_encontrado; END IF;
  
  SELECT codPiloto INTO cod_piloto FROM PILOTO WHERE nombreP=a_piloto;
  SELECT codRally INTO cod_rally FROM RALLY WHERE nombre=a_rally;
  
  dbms_output.put_line(a_rally||'     (Antes de la actualización)');
  dbms_output.put_line('==============================================');
  FOR v_penalizacion IN c_penalizacion() LOOP
    dbms_output.put_line(v_penalizacion.nombreP||'  '||v_penalizacion.penalizacion);
  END LOOP;
  
  UPDATE PARTICIPA SET penalizacion=penalizacion+a_cantidad WHERE codRally=cod_rally AND codPiloto=cod_piloto;
  
  dbms_output.put_line('');
  dbms_output.put_line(a_rally||'     (Despues de la actualización)');
  dbms_output.put_line('==============================================');
  FOR v_penalizacion IN c_penalizacion() LOOP
    dbms_output.put_line(v_penalizacion.nombreP||'  '||v_penalizacion.penalizacion);
  END LOOP;
EXCEPTION
  WHEN e_piloto_no_encontrado THEN
    dbms_output.put_line('No existe el piloto con el nombre '||a_piloto);
  WHEN e_rally_no_encontrado THEN
    dbms_output.put_line('No existe el rally con el nombre '||a_rally);
  WHEN OTHERS THEN
    dbms_output.put_line('Se ha producido un error inesperado.'); 
END;