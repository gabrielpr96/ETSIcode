cla;

distancia = 5;
tStart = tic();
tElapsed = 0;
while tElapsed < 10
    tElapsed = toc(tStart);
    %pinta_robot_v3(0, 0, 0, sin(tElapsed)*2*pi, 1+tElapsed/1.5);
    pinta_robot_v3(tElapsed-5, sin(tElapsed)*2, tElapsed, tElapsed/5, 2);
end