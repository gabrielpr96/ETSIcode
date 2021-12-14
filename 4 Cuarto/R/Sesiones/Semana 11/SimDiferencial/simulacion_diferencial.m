
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

camino=load('camino.dat');

caminoSize = size(camino, 1);

l=3.5; %distancia entre rudas delanteras y traseras, tambien definido en modelo
radio_rueda=1;

%Condiciones iniciales
pose0=[0; 0; 0];

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

%inicialización valores iniciales
pose(:,k+1)=pose0;

t(k+1)=t0;

while (t0+h*k) < tf,
    %actualización
    k=k+1;

    p = minima_distancia(camino, pose(:, k)');%+16
    while sqrt(power(pose(1, k)-camino(p, 1), 2) + power(pose(2, k)-camino(p, 2), 2)) < 2
        p = p + 1;
        if p > caminoSize
            p = p - caminoSize;
        end
    end

    punto = camino(p, :);

    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    %valores de los parámetros de control
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


    delta = ((pose(1, k)-punto(1))*sin(pose(3, k))) - ((pose(2, k)-punto(2))*cos(pose(3, k)));
    lh = sqrt(power(pose(1, k)-punto(1), 2) + power(pose(2, k)-punto(2), 2));

    rho = (2*delta)/power(lh, 2);

    V = 5;
    W = rho*V;

    velocidad_derecha = (1/radio_rueda)*(V+W*l);
    velocidad_izquierda = (1/radio_rueda)*(V-W*l);

    conduccion=[velocidad_derecha velocidad_izquierda];

    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    %metodo de integración ruge-kuta

    pose(:,k+1)=kuta_diferencial(t(k),pose(:,k),h,conduccion);
end




