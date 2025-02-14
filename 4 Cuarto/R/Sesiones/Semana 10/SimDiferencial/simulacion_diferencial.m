
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

    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    %valores de los parámetros de control
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%     velocidad_derecha=2.1;
%     velocidad_izquierda=k*0.05;
    %R=10;
    %rho=1/R;

    punto=[camino(p, 1) camino(p, 2)];

    delta = ((pose(1, k)-punto(1))*sin(pose(3, k))) - ((pose(2, k)-punto(2))*cos(pose(3, k)));
    lh = sqrt(power(pose(1, k)-punto(1), 2) + power(pose(2, k)-punto(2), 2));

    rho = (2*delta)/power(lh, 2);

    V = lh*1.5;
    W = rho*V;

    velocidad_derecha = (1/radio_rueda)*(V+W*l);
    velocidad_izquierda = (1/radio_rueda)*(V-W*l);

    conduccion=[velocidad_derecha velocidad_izquierda];

    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    %para representar el punto onjetivo sobre la trayectoria

    

    %metodo de integración ruge-kuta

    pose(:,k+1)=kuta_diferencial(t(k),pose(:,k),h,conduccion);

    p = p + 3;
    if p > caminoSize
        p = 1;
    end
end




