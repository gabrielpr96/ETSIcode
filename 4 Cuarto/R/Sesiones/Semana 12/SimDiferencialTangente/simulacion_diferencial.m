
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Simulación del movimiento de un robot móvil
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

clear all
clc

j=1;

global l
global radio_rueda
global camino
global pose
global punto
%cargamos el camino

X0 = 15;
Y0 = 15;
theta0 = -pi/4.0;

Xf = 80;
Yf = 80;
thetaF = -pi/4.0;

dd = 5;
da = 5;
Pdx = X0 + dd*cos(theta0);
Pdy = Y0 + dd*sin(theta0);
Pax = Xf - da*cos(thetaF);
Pay = Yf - da*sin(thetaF);

Xi1 = 40;
Yi1 = 50;
Xi2 = 50;
Yi2 = 60;

ds = 3;
xc = [X0 Pdx Xi1 Xi2 Pax Xf];
yc = [Y0 Pdy Yi1 Yi2 Pay Yf];

camino = funcion_spline_cubica_varios_puntos(xc, yc, ds)';

%camino=load('camino.dat');

caminoSize = size(camino, 1);

l=3.5; %distancia entre rudas delanteras y traseras, tambien definido en modelo
radio_rueda=1;

%Condiciones iniciales
pose0=[X0; Y0; theta0];

t0=0;

%final de la simulación
tf=60;

%paso de integracion
h=0.1;
%vector tiempo
t=0:h:tf;
%indice de la matriz
k=0;
p = 1;
V = 10;

%inicialización valores iniciales
pose(:,k+1)=pose0;

t(k+1)=t0;

while (t0+h*k) < tf && abs(V) > 0.05
    %actualización
    k=k+1;

    p = minima_distancia(camino, pose(:, k)');%+16
    while sqrt(power(pose(1, k)-camino(p, 1), 2) + power(pose(2, k)-camino(p, 2), 2)) < 5 && p < caminoSize
        p = p + 1;
        if p > caminoSize
            p = caminoSize;
        end
    end

    punto = camino(p, :);

    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    %valores de los parámetros de control
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


    delta = ((pose(1, k)-punto(1))*sin(pose(3, k))) - ((pose(2, k)-punto(2))*cos(pose(3, k)));
    lh = sqrt(power(pose(1, k)-punto(1), 2) + power(pose(2, k)-punto(2), 2));

    rho = (2*delta)/power(lh, 2);

    KP = 0.5;
    distancia_al_final = sqrt(power(pose(1, k)-camino(caminoSize, 1), 2) + power(pose(2, k)-camino(caminoSize, 2), 2));
    V = min(distancia_al_final*KP, 20);
    W = rho*V;

    velocidad_derecha = (1/radio_rueda)*(V+W*l);
    velocidad_izquierda = (1/radio_rueda)*(V-W*l);

    conduccion=[velocidad_derecha velocidad_izquierda];

    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    %metodo de integración ruge-kuta

    pose(:,k+1)=kuta_diferencial(t(k),pose(:,k),h,conduccion);
end




