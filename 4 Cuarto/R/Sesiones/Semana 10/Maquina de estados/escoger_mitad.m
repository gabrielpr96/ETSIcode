function [degInicio, degFin] = escoger_mitad(distancia_raw, degInicio, degFin)
    mitad = degInicio + ((degFin - degInicio) / 2);

    mediaIzq = 0;
    mediaDer = 0;
    nIzq = 0;
    nDer = 0;

    for s = 1:length(distancia_raw)
        if distancia_raw(s, 1) >= degInicio && distancia_raw(s, 1) < mitad
            mediaIzq = mediaIzq + distancia_raw(s, 2);
            nIzq = nIzq + 1;
        elseif distancia_raw(s, 1) <= degFin && distancia_raw(s, 1) > mitad
            mediaDer = mediaDer + distancia_raw(s, 2);
            nDer = nDer + 1;
        end
    end

    if nIzq > 0
        mediaIzq = mediaIzq / nIzq;
    end
    if nDer > 0
        mediaDer = mediaDer / nDer;
    end

    if mediaIzq > mediaDer
        degFin = mitad;
    else
        degInicio = mitad;
    end
end

