clc;
cla;
clear t;
clear x y theta;
clear giro_derecho giro_izquierdo giro_robot;

N_SUBDIVISIONES = 1;
STOP_DISTANCE = 15;
T_MARCHA_ATRAS = 1;
MARGIN_GIRO = 3;
KP_GIRO = 5.0;
CAR_IP = "192.168.0.116";

pwmL=0;
pwmR=0;

i=1;
tstart = tic;
t(i)=0;
estado=1;
transicion=1;
x = 0;
y = 0;
theta = 0;

new_cloud = [];
last_map = [];
new_map = [];

server = udpport("datagram","IPV4","LocalPort", 41144)
client = udpport("datagram")

Ping;

while  true
  
    i=i+1;
    t(i)= toc(tstart);
    
    map_updated = false;
    Signal_reading_odo;

    if map_updated
        last_map = new_map;
        new_map = calculo_mapa(new_cloud, x, y, theta);
        Calcular_lidar_odometria;
    end

    estado;

    %--------------------------------------------------------
    % TRANSICIONES DE ESTADO
    %1-> marchando para adelante
    %2-> parando
    %3-> girando cabeza con sonar
    %4-> girando sobre si mismo
    %5-> Marcha atrás

    switch estado
        
        case 1 %andando hacia delante

             if calculo_distancia_delantera < STOP_DISTANCE
                estado=2;
                transicion=i;
             end

        case 2 %parando

            if pwmL == 0 && pwmR == 0
                if calculo_distancia_delantera > STOP_DISTANCE
                    estado=1;
                else
                    estado=5;
                end
                transicion=i;
            end
         
        case 3 %girando cabeza

            degInicio = 90;
            degFin = -90;
            for s=1:N_SUBDIVISIONES
                [degInicio, degFin] = escoger_mitad(new_map, degInicio, degFin);
            end
            [degInicio, degFin];

            deg_mejor = media_ponderada(new_map, degInicio, degFin);
            theta_target = theta + deg_mejor;

            estado=4;
            transicion=i;
            
        case 4 %girando robot

            if  abs(theta_target-theta) <= MARGIN_GIRO                
                estado=2;
                transicion=i;
            end
            
       case 5 %marcha atrás

            if  t(i)-t(transicion) > T_MARCHA_ATRAS
                estado=3;
                transicion=i;
            end
        
    end
    
    %-------------------------------------------------
    %SALIDAS ASOCIADAS A LOS ESTADOS
    
    switch estado
    
        case 1 %andando hacia delante

            pwmL = 50;
            pwmR = 50;

        case 2 %parando

            pwmL = 0;
            pwmR = 0;
    
        case 3 %girando cabeza
            
            pwmL = 0;
            pwmR = 0;

        case 4 %girando sobre si mismo
                
           vel = (theta_target-theta) * KP_GIRO;
           pwmL = vel;
           pwmR = -vel;
           
        case 5 %andando hacia atrás

           pwmL = -50;
           pwmR = -50;    
      
    end

    if map_updated
        pinta_lidar_robot(x, y, theta, new_map);
    end

    Traction_motor_control;

end
    
Para;
