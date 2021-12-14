function deg = media_ponderada(distancia_raw, degInicio, degFin)
    sumDisPorDeg = 0;
    sumDis = 0;

    for s = 1:length(distancia_raw)
        if distancia_raw(s, 1) <= degInicio && distancia_raw(s, 1) >= degFin
            sumDisPorDeg = sumDisPorDeg + distancia_raw(s, 1) * distancia_raw(s, 2);
            sumDis = sumDis + distancia_raw(s, 2);
        end
    end

    if sumDis > 0
        deg = sumDisPorDeg / double(sumDis);
    else
        deg = sumDisPorDeg;
    end
end

