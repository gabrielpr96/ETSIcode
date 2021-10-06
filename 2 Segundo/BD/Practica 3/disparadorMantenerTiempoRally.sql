CREATE OR REPLACE TRIGGER mantenerTiempoRally BEFORE DELETE OR INSERT OR UPDATE OF tiempo ON CORRE FOR EACH ROW
DECLARE
    existe INTEGER;
BEGIN
   IF INSERTING THEN
      SELECT COUNT(*) INTO existe FROM PARTICIPA WHERE codRally=:new.codRally AND codPiloto=:new.codPiloto;
      IF existe = 0 THEN
          RAISE_APPLICATION_ERROR(-20001,'Ese piloto no está corriendo ese Rally');
      END IF;
      UPDATE PARTICIPA SET tiemporally = tiemporally+:new.tiempo WHERE codRally = :new.codRally AND codPiloto = :new.codPiloto;
    ELSIF UPDATING THEN
      UPDATE PARTICIPA SET tiemporally = tiemporally+:new.tiempo-:old.tiempo WHERE codRally = :new.codRally AND codPiloto = :new.codPiloto;
    ELSE
      UPDATE PARTICIPA SET tiemporally = tiemporally-:old.tiempo WHERE codRally = :old.codRally AND codPiloto = :old.codPiloto;
    END IF;
END;