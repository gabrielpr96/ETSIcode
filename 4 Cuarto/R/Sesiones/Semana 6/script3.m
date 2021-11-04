cla;

distancia = 5;

if isfile('mapa.dat')
    mapa = load('mapa.dat', '-ascii');
else
    mapa = [];
end

tStart = tic();
tElapsed = 0;
while tElapsed < 10
    tElapsed = toc(tStart);
    mapa = pinta_robot_v4(tElapsed-5, sin(tElapsed)*2, tElapsed, -tElapsed/5, 2+tElapsed/2, mapa);
    %mapa = pinta_robot_v4(tElapsed-5, sin(tElapsed)*2, -tElapsed, tElapsed/5, 2+tElapsed/2, mapa);
end

save('mapa.dat', 'mapa', '-ascii');

