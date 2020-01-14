CREATE OR REPLACE
PROCEDURE mejor_piloto_tramo (a_piloto PILOTO.nombreP%TYPE) IS
  e_piloto_no_encontrado EXCEPTION;
  existe INTEGER;
  CURSOR c_rally IS
    SELECT codRally, nombre
    FROM RALLY INNER JOIN PARTICIPA USING(codRally) INNER JOIN PILOTO USING(codPiloto)
    WHERE nombreP=a_piloto
  ;
  CURSOR c_mejorTiempo(cRally RALLY.codRALLY%TYPE) IS
    SELECT numeroTramo, MIN(tiempo) AS mejorTiempo
    FROM CORRE
    WHERE codRally = cRally
    GROUP BY numeroTramo
    ORDER BY mejorTiempo
  ;
BEGIN
  SELECT COUNT(*) INTO existe FROM PILOTO WHERE nombreP=a_piloto;
  IF existe = 0 THEN RAISE e_piloto_no_encontrado; END IF;
  
  FOR v_rally IN c_rally() LOOP
    dbms_output.put_line('-----------------------------');
    dbms_output.put_line(v_rally.nombre);
    dbms_output.put_line('-----------------------------');
    FOR v_mejorTiempo IN c_mejorTiempo(v_rally.codRally) LOOP
      dbms_output.put_line(v_mejorTiempo.numeroTramo||'    '||v_mejorTiempo.mejorTiempo);
    END LOOP;
  END LOOP;
EXCEPTION
  WHEN e_piloto_no_encontrado THEN
    dbms_output.put_line('No existe el piloto con el nombre '||a_piloto);
  WHEN OTHERS THEN
    dbms_output.put_line('Se ha producido un error inesperado.'); 
END;