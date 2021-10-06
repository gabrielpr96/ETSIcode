create or replace FUNCTION fSexoExperto (s IN EXPERTO.SEXO%TYPE) RETURN Integer IS
    n Integer;
BEGIN
    IF s != 'F' AND s != 'M' Then 
        RAISE_APPLICATION_ERROR (-20001, 'El sexo introducido (' || s ||  ') no es valido');
    END IF;
    SELECT COUNT(*) INTO n FROM EXPERTO WHERE SEXO = s;
    RETURN n;
END;