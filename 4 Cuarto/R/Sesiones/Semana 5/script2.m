clear all;
clc;
robot = legoev3('USB');
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
angulo(s) = readRotation(motor_cabeza);
tiempo_final = 10;
start(motor_cabeza);
motor_cabeza.Speed = 20;
disp 'Pulse para salir';
while ((t(s) < tiempo_final) && (readTouch(touchSensor(robot,2)) == 0 ))
    s = s + 1;
    t(s) = toc(tstart);
    %motor_cabeza.Speed = t(s)*2;
    angulo(s) = readRotation(motor_cabeza);
end
plot(t,angulo);
motor_cabeza.Speed = 0;
stop(motor_cabeza);
disp 'Terminado'