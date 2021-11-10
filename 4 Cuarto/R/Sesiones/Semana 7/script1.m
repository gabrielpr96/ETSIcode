clear all;
clc;
cla;

% Definir motor
robot = legoev3('USB');
motor_cabeza = motor(robot,'A');

% Reset Encoder
resetRotation(motor_cabeza);

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
KP = 0.6;
start(motor_cabeza);

disp 'Pulse para salir';
while (isempty(t) || (t(s) < tiempo_final) && (readTouch(touchSensor(robot,2)) == 0 ))
    s = s + 1;
    t(s) = toc(tstart);
    alpha(s) = readRotation(motor_cabeza);
    referencia(s) = 90;
    error(s) = referencia(s) - alpha(s);

    % Controlador
    velocidad(s) = error(s) * KP;
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