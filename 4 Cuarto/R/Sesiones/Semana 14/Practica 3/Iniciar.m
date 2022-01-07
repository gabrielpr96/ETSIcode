%Limpieza
clear t x y giro_derecho giro_izquierdo theta theta_deg Power1_a Power2_a
clc

%Conectar con el robot, si no lo esta ya
if ~exist('robot','var')
    WiFi_connection
end

%Reiniciar el encoder de los motores y encenderlos
start(motor_izquierdo);
start(motor_derecho);
motor_izquierdo.Speed = 0;
motor_derecho.Speed = 0;
resetRotation(motor_izquierdo);
resetRotation(motor_derecho);

%Definir constantes
global r_rueda
global l_semi
l_semi = 5.0;
r_rueda = 2.6;
conversion_potencia = 7.0;
KP = 1.1;

%Estado inicial
i = 1;
t(i)=0;
x(i) = 0;
y(i) = 0;
giro_derecho(i) = 0;
giro_izquierdo(i) = 0;
theta(i) = 0;
theta_deg(i) = rad2deg(double(theta(i)));
Power1_a(i) = 0;
Power2_a(i) = 0;
Power1=0;
Power2=0;

