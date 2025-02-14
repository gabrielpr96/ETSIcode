%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Controla la convertgencia del robot EV3 a un punto mediante una estrategia de control geom�trico.
% utiliza los encoders de los motores para estimar calcular la odometr�a
% utiliz un  switch, para comenzar y terminar  la rutina
%
% Utiliza los script: 
% Traction_motor_control_laboratorio.m; Signal_reading_odo_path_following.m;  Para.m.
%
% Utiliza las funciones: calculo_calculo_odometria.m; 
%
% 
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% 29/11/2020. FGB.
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
clc
clear all

%variables control angulo del robot
    clear yaw
    clear x y theta
    clear giro_derecho giro_izquierdo

    global radio_rueda
    global l %distancia entre ruedas
    
    mi_Robot = legoev3('USB')

%----------------------------------------
% Variables para la representaci�n gr�fica
%------------------------------------------

% %Crea los sistemas de referencia del robot y de la cabeza para la
% %representaci�n utilizando la funci�n pinta_robot_v2
%     SR_robot = hgtransform;
%     SR_cabeza = hgtransform('Parent',SR_robot);
%-----------------------------------------

%------------------------------
%Valores para la odometr�a
      l=5.9;
      radio_rueda=2.7

%----------------------------


% Declaraci�n de sensores
    Detecta_colision = touchSensor(mi_Robot,1); %Switch conectado al puerto 1.
    Pulsador = touchSensor(mi_Robot,2); %Switch conectado al puerto 2.
    Sonar = sonicSensor(mi_Robot); %definici�n del sonar

% Declaraci�n de los motores
    motor_cabeza = motor(mi_Robot,'A') %motor de la cabeza
    motor_izquierdo = motor(mi_Robot,'B') %Motor izquierdo
    motor_derecho = motor(mi_Robot,'C') %Motor_derecho

%Activacion de los motores
    start(motor_cabeza);
    start(motor_izquierdo);
    start(motor_derecho);

%inicializa velocidad de motores
    Power1=0;
    Power2=0;
    Power_cabeza=0;


%reset del encoder de motores
    resetRotation(motor_cabeza);
    resetRotation(motor_izquierdo);
    resetRotation(motor_derecho);

%indice inicial
    i=1;

%valores iniciales de los encoders
    giro_derecho(i)=0;
    giro_izquierdo(i)=0;


%--------------------
% Valores iniciales
%--------------------
%tiempo inicial
    t(i)=0;
    x(i)=0;
    y(i)=0;
    theta(i)=0;
    yaw(i)=0;
    

%comienza el bucle
disp('pulsa el bumper para comenzar')



while(readTouch(Pulsador)==0) 
end

while(readTouch(Pulsador)==1)
end


disp('comienza el bucle')
tf=60;
%referencia tiempo inicial
    tstart = tic;

while  (readTouch(Pulsador)==0) & (t(i)<tf)
  
        i=i+1; %indice global
        t(i)= toc(tstart); %tiempo global del bucle
    %---------------------
    %lectura se�ales y calculo del heading
    %-------------------------------
    
        Signal_reading_odo_path_following;
      
      %-----------------------------
       %Calcula Odometr�a
      %--------------------------  
    %calculo odometria
        [x(i) y(i) theta(i)]=calculo_odometria(giro_derecho,giro_izquierdo,x,y,theta,i);
    %para controlar el giro
        yaw(i)=theta(i)*180/pi;
   
    
%----------------------------
% Control Geom�trico

%para converger a un punto
 
 punto=[0 40];    


 delta= (x(i)-punto(1))*sin(theta(i))-(y(i)-punto(2))*cos(theta(i));
 
 LH=sqrt((x(i)-punto(1))^2+(y(i)-punto(2))^2);
 
 rho=2*delta/LH^2;
 
 

%Control proporcional de la velocidad
 Kp=1.1;
 %final=[camino(end,1) camino(end,2)]; %para converger al final del camino
 final=punto; %para converger a un punto
 Distance_to_end=sqrt((x(i)-final(1))^2+(y(i)-final(2))^2);
 velocidad=Kp*Distance_to_end;
 
 
 if velocidad>15
     velocidad=15;
 end
 
 if Distance_to_end<3
     break
 end

 
 %-------------------------------------
 % modelo Inverso
 %-------------------------------------
 
    velocidad_derecha=velocidad*(1+l*rho)/radio_rueda;
 
    velocidad_izquierda=velocidad*(1-l*rho)/radio_rueda;
    
%---------------------------------------------------
% Cversi�n de velocidad a potencia
    potencia_equivalente=7.0;

    Power1_a(i)=velocidad_derecha*potencia_equivalente;
    Power2_a(i)=velocidad_izquierda*7.0;

    Power1=Power1_a(i);
    Power2=Power2_a(i);
    
    

      %---------------------
        %Manda los comandos de control a los motores
      %-------------        
        Traction_motor_control_laboratorio;
        
  
    
end %del while

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Para motores y cierra sensores
%-------------------------------
 Para;

% plot(t,giro_izquierdo)
% hold on 
% plot(t,giro_derecho)

velocidad_izquierda
giro_izquierdo(end)/t(end)

velocidad_derecha
giro_derecho(end)/t(end)

figure

plot(x,y)

axis equal

%axis([0 90 0 90]);
