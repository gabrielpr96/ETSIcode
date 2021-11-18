clear all;
clc;
cla;

% Definir motor
robot = legoev3('USB');
motor_cabeza = motor(robot,'A');
motor_derecha = motor(robot,'C');

% Reset Encoder
resetRotation(motor_cabeza);
resetRotation(motor_derecha);

% Esperar para iniciar el script
disp 'Pulsa para comenzar'
while readTouch(touchSensor(robot,2)) == 0  
end
while readTouch(touchSensor(robot,2)) == 1
end

% Iniciar variables
s = 0;
t = [];
alpha = [];
referencia = [];
error = [];
velocidad = [];
tstart = tic;
tiempo_final = 10;
delay = 2;
periodo = 6;
amplitud = 90;
start(motor_cabeza);

c = PIDcontroller(0.6, 0.2, 0.5, 0.2, -100, 100, -50, 50);

oldTime = toc(tstart);
disp 'Pulse para salir';
while (isempty(t) || (t(s) < tiempo_final) && (readTouch(touchSensor(robot,2)) == 0 ))
    s = s + 1;
    newTime = toc(tstart);
    delta = newTime-oldTime;
    oldTime = newTime;

    alpha(s) = readRotation(motor_cabeza);
    referencia(s) = readRotation(motor_derecha);

    c = c.update(referencia(s), alpha(s), delta);

    error(s) = c.prevError;
    t(s) = newTime;
    

    % Controlador
    velocidad(s) = int8(c.out);
    motor_cabeza.Speed = velocidad(s);
end

%Dibujar los resultados
tiledlayout(2, 1);
nexttile;
hold on;
plot(t, referencia);
plot(t, alpha);
title('Referencia + Alpha vs Tiempo');
hold off;
nexttile;
hold on;
plot(t, error);
plot(t, velocidad);
title('Error + Velocidad vs Tiempo');
hold off;

motor_cabeza.Speed = 0;
motor_cabeza.stop();