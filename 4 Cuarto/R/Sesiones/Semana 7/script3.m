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
KP = 0.6;
start(motor_cabeza);

disp 'Pulse para salir';
while (isempty(t) || (t(s) < tiempo_final) && (readTouch(touchSensor(robot,2)) == 0 ))
    s = s + 1;
    t(s) = toc(tstart);
    alpha(s) = readRotation(motor_cabeza);
    referencia(s) = readRotation(motor_derecha);
    error(s) = referencia(s) - alpha(s);

    % Controlador
    velocidad(s) = error(s) * KP;
    if velocidad(s) > 100
        velocidad(s) = 100;
    elseif velocidad(s) < -100
            velocidad(s) = -100;
    end
    velocidad(s) = int8(velocidad(s));
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