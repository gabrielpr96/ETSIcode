CREATE OR REPLACE TRIGGER mantenerTiempoRally AFTER DELETE OR INSERT OR UPDATE OF tiempo ON CORRE FOR EACH ROW
DECLARE
    existe INTEGER;
BEGIN
    SELECT COUNT(*) INTO existe FROM PARTICIPA WHERE codRally=:new.codRally AND codPiloto=:new.codPiloto;
    IF existe = 0 THEN
        RAISE_APPLICATION_ERROR(-20001,'Ese piloto no está corriendo ese Rally');
    END IF;
    
    IF (:old.codRally IS NOT NULL AND :old.codRally <> :new.codRally) OR (:old.codPiloto IS NOT NULL AND :old.codPiloto <> :new.codPiloto) THEN
        --Movido de piloto o rally
        UPDATE PARTICIPA SET tiemporally = tiemporally+:new.tiempo WHERE codRally = :new.codRally AND codPiloto = :new.codPiloto;
        UPDATE PARTICIPA SET tiemporally = tiemporally-:old.tiempo WHERE codRally = :old.codRally AND codPiloto = :old.codPiloto;
    ELSE
        IF :old.tiempo IS NULL THEN --Nuevo insercion
            UPDATE PARTICIPA SET tiemporally = tiemporally+:new.tiempo WHERE codRally = :new.codRally AND codPiloto = :new.codPiloto;
        ELSE
            IF :new.tiempo IS NULL THEN --Eliminacion
                UPDATE PARTICIPA SET tiemporally = tiemporally-:old.tiempo WHERE codRally = :old.codRally AND codPiloto = :old.codPiloto;
            ELSE --Modificacion
                UPDATE PARTICIPA SET tiemporally = tiemporally+:new.tiempo-:old.tiempo WHERE codRally = :new.codRally AND codPiloto = :new.codPiloto;
            END IF;
        END IF;
    END IF;
END;