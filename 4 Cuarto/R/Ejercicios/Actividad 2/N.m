function salida = N(t)

    if t >= 0 && t <= 30
        salida = -5*exp(-.2*t)*cos(.9*t-deg2rad(30))+.8*exp(-2*t)+5;
    else
        salida = 0;
    end

end

