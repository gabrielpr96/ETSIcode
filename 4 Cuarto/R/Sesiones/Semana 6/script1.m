clear all;
clc;
robot = legoev3('USB');
%sonar = sonicSensor(robot,3);
motor_cabeza = motor(robot,'A');
resetRotation(motor_cabeza);
disp 'Pulsa para comenzar'
while readTouch(touchSensor(robot,2)) == 0  
end
while readTouch(touchSensor(robot,2)) == 1
end
s = 1;
clc;
tstart = tic;
t(s) = toc(tstart);
%distancia(s) = readDistance(sonar);
angulo(s) = readRotation(motor_cabeza);
tiempo_final = 10;
start(motor_cabeza);
%motor_cabeza.Speed = 10;
disp 'Pulse para salir';
x = 5;
y = 5;
theta = 0;
while ((t(s) < tiempo_final) && (readTouch(touchSensor(robot,2)) == 0 ))
    s = s + 1;
    t(s) = toc(tstart);
    %distancia(s) = readDistance(sonar);
    %motor_cabeza.Speed = t(s)*2;
    motor_cabeza.Speed = 20;
    angulo(s) = readRotation(motor_cabeza);
    alpha = deg2rad(double(angulo(s)));
    pinta_robot(x, y, theta, alpha);
end
%plot(t,distancia);
%plot(t,angulo);
motor_cabeza.Speed = 0;
stop(motor_cabeza);
disp 'Terminado'