function salida = signal_vf(t, delay, periodo, amplitud)

if(t < delay || t > delay+periodo)
    salida = 0;
else
    salida = amplitud*sin(((2*pi)/periodo)*(t-delay));
end

end