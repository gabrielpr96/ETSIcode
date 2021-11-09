cla;

distancia = 5;
tStart = tic();
tElapsed = 0;
while tElapsed < 10
    tElapsed = toc(tStart);
    pinta_robot_v2(0, 0, 0, sin(tElapsed)*2*pi, 1+tElapsed/1.5);
end