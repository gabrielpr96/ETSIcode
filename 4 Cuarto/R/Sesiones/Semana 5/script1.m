clear all;
clc;
robot = legoev3('USB');
sonar = sonicSensor(robot,3);
motor_cabeza = motor(robot,'A');
disp 'Pulsa para comenzar'
while readTouch(touchSensor(robot,2)) == 0  
end
while readTouch(touchSensor(robot,2)) == 1
end
s = 1;
clc;
tstart = tic;
t(s) = toc(tstart);
distancia(s) = readDistance(sonar);
tiempo_final = 10;
disp 'Pulse para salir';
while ((t(s) < tiempo_final) && (readTouch(touchSensor(robot,2)) == 0 ))
    s = s + 1;
    t(s) = toc(tstart);
    distancia(s) = readDistance(sonar);
end
plot(t,distancia);
disp 'Terminado'