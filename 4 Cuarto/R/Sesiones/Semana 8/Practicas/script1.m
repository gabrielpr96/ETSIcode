clear all;
clc;
cla;

% Definir motor
robot = legoev3('USB');
motor_cabeza = motor(robot,'A');
sonar = sonicSensor(robot,3);

% Reset Encoder
resetRotation(motor_cabeza);

% Pintar robot
x = -5;
y = -5;
theta = 0;
%pinta_robot_v2(x, y, theta, 0, 2.55);
pinta_robot_v4(x, y, theta, 0, 2.55, []);

% Esperar para iniciar el script
disp 'Pulsa para comenzar'
while readTouch(touchSensor(robot,2)) == 0  
    %distancia = readDistance(sonar)
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
tiempo_final = 20;
delay = 3;
periodo = 14;
amplitud = 90;
KP = 1.75;
start(motor_cabeza);

disp 'Pulse para salir';
while (isempty(t) || (t(s) < tiempo_final) && (readTouch(touchSensor(robot,2)) == 0 ))
    s = s + 1;
    t(s) = toc(tstart);
    alpha(s) = readRotation(motor_cabeza);
    referencia(s) = signal_vf(t(s), delay, periodo, amplitud);
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
    
    %Pintar robot y punto
    %pinta_robot_v2(x, y, theta, deg2rad(double(alpha(s))), readDistance(sonar));
    pinta_robot_v4(x, y, theta, deg2rad(double(alpha(s))), readDistance(sonar), []);
end

%Dibujar los resultados
% tiledlayout(2, 1);
% nexttile;
% hold on;
% plot(t, referencia);
% plot(t, alpha);
% title('Referencia + Alpha vs Tiempo');
% hold off;
% nexttile;
% hold on;
% plot(t, error);
% plot(t, velocidad);
% title('Error + Velocidad vs Tiempo');
% hold off;

motor_cabeza.Speed = 0;
motor_cabeza.stop();