CREATE OR REPLACE
PROCEDURE hola (nombre varchar) IS
BEGIN
  dbms_output.put_line('Hola '||nombre);
END;