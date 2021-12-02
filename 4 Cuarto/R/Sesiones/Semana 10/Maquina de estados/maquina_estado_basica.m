%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% controla el movimiento del robot mediante una máqunia de estado.
% Utiliza los script: 
% Traction_motro_control.m; Signal_reading_odo.m;
% Para_Cierra.m.
% 28/11/2020. FGB.
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    clc
    cla
    clear t
%variables control movimiento cabeza
    clear giro_cabeza
    clear referencia_cabeza
    clear distancia;
    clear error_sonar;

%variables control angulo del robot
    clear ang_vel;
    clear yaw
    clear error_yaw
    clear referencia_yaw
    clear x y theta
    clear giro_derecho giro_izquierdo giro_robot

    global radio_rueda
    global l_semi %semi distancia entre ruedas

    l_semi=6;
    radio_rueda=3;

    N_SUBDIVISIONES = 1;

% Declaración de sensores
    Detecta_colision = touchSensor(mi_Robot,1); %Switch conectado al puerto 1.
    Pulsador = touchSensor(mi_Robot,2); %Switch conectado al puerto 2.
    Sonar = sonicSensor(mi_Robot); %definición del sonar

% Declaración de los motores
    motor_cabeza = motor(mi_Robot,'A'); %motor de la cabeza
    motor_izquierdo = motor(mi_Robot,'B'); %Motor izquierdo
    motor_derecho = motor(mi_Robot,'C'); %Motor_derecho

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

%referencia tiempo inicial
    tstart = tic;

%posición inicial cabeza
    giro_cabeza(i)=0;
    giro_cabeza_target = 0;

%valores iniciales de los encoders
    giro_derecho(i)=0;
    giro_izquierdo(i)=0;
    giro_robot(i) = 0;
    giro_robot_target = 0;
    x(i) = 0;
    y(i) = 0;

%medida incial
    distancia(i) = readDistance(Sonar)*100;

%--------------------
% Valores iniciales
%--------------------
%tiempo inicial
    t(i)=0;

    estado=1; %estado inicial

    stop_distance=15; %distancia de para ante obstáculo
    t_marcha_atras=0.5; %tiempo de marcha hacia atrás.
    transicion=1;% inicializa la variable que marca el inicio el mov de la cabeza
    t_giro_cabeza=6; %Girar la cabeza tarda 6 segundos
    KP_CABEZA = 0.6;
    KP_GIRO = 2.5;
    giro_robot_margen = 3;


%comienza el bucle
    disp('pulsa el bumper para comenzar')

while(readTouch(Pulsador)==0) 
end

while(readTouch(Pulsador)==1)
end

disp('comienza el bucle')
%try
beep(mi_Robot);
while  (readTouch(Pulsador)==0)
  
    i=i+1; %indice global
    t(i)= toc(tstart); %tiempo global del bucle
    
    
    %---------------------
    %lectura señales y calculo del heading
    %-------------------------------
    
    Signal_reading_odo;

    [x(i), y(i), giro_robot(i)] = calculo_odometria(giro_derecho, giro_izquierdo, x, y, giro_robot, i);

    estado; %muestra el estado del sistema
    
    giro_robot_error = giro_robot_target-rad2deg(giro_robot(i));

    %--------------------------------------------------------
    % TRANSICIONES DE ESTADO
    %1-> marchando para adelante
    %2-> parando
    %3-> girando cabeza con sonar
    %4-> girando sobre si mismo
    %5-> Marcha atrás

        switch estado
            
            case 1 %andando hacia delante
                %if (readDistance(Sonar)<stop_distance) %si la distancia es menor que 35 para
                 if (distancia(i)<stop_distance) %si la distancia es menor que 35 para
                    estado=2; %transición de estado de paro
                    transicion=i; %indice que marca el inicio del estado 2
                end
                
            
            case 2 %parando
                if (vel==0)
                    if distancia(i)>stop_distance
                        estado=1; %la transición a estado marcha hacia delante
                        transicion=i; %indice que marca el inicio del estado 1
                    else
                        estado=5; %transición a marcha atras
                        transicion=i; %indice que marca el inicio del estado 3
                    end
                end
             
            case 3 %girando cabeza
                if (t(i)-t(transicion)>t_giro_cabeza)

                    %TODO: Subdividir en un for y luego hacer media
                    %ponderada para calcular el giro_robot_target

                    %Subdividir para que la media salga mejor
                    degInicio = 90;
                    degFin = -90;
                    for s=1:N_SUBDIVISIONES
                        [degInicio, degFin] = escoger_mitad(distancia_raw, degInicio, degFin);
                    end
                    [degInicio, degFin];
                    %Obtener el mejor angulo con la media
                    deg_mejor = media_ponderada(distancia_raw, degInicio, degFin)
                    giro_robot_target = giro_robot(i) + deg_mejor;

                    estado=4; %la transición a estado girando robot
                    transicion=i; %indice que marca el inicio del estado 3              
                end
                
            case 4 %girando robot
                if (abs(giro_robot_error) <= giro_robot_margen)                  
                    estado=2; %la transición a parado
                    transicion=i; %indice que marca el inicio del estado 5           
                end
                
           case 5 %marcha atrás
                if (t(i)-t(transicion)>t_marcha_atras)

                    distancia_raw = [];
                    distancia_i = 1;

                    estado=3; %transición a estado girando cabeza
                    transicion=i; %indice que marca el inicio del estado 2                 
                end
            
        end %del siwtch
    
   %-----------------------
          
    
    %-------------------------------------------------
    %SALIDAS ASOCIADAS A LOS ESTADOS
    
        switch estado
        
            case 1 %andando hacia delante
            %establece los valores de control 
                vel=20;
                Power1=vel;
                Power2=vel;
                Power_cabeza = 0;
                
              %---------------------
              %Manda los comandos de control a los motores
              %-------------
                %Traction_motor_control;

            case 2 %parando
              %establece los valores de control 
                vel=0;
                Power1=vel;
                Power2=vel;
                Power_cabeza = 0;
                
              %---------------------
              %Manda los comandos de control a los motores
              %-------------
               %Traction_motor_control;
        
            case 3 %girando cabeza
                distancia_raw(distancia_i, 1) = giro_cabeza(i);
                distancia_raw(distancia_i, 2) = distancia(i);
                distancia_i = distancia_i + 1;
                
                giro_cabeza_target = signal_vf(t(i)-t(transicion), 0, t_giro_cabeza, 90);
                
                vel=0;
                Power1=vel;
                Power2=vel;
                Power_cabeza = (giro_cabeza_target - giro_cabeza(i)) * KP_CABEZA;

            case 4 %girando sobre si mismo
                    
               vel = giro_robot_error * KP_GIRO;
               Power1=vel;
               Power2=-vel;
               Power_cabeza = 0;
               
            case 5 %andando hacia atrás
                %establece los valores de control 
               vel=-20;
               Power1=vel;
               Power2=vel;
               Power_cabeza = 0;
                
              %---------------------
              %Manda los comandos de control a los motores
              %-------------
               %Traction_motor_control;      
          
        end %del siwtch

        pinta_robot_v4(x(i), y(i), giro_robot(i), deg2rad(double(giro_cabeza(i))), distancia(i), []);

      %---------------------
        %Manda los comandos de control a los motores
      %-------------        
        Traction_motor_control;   

        %TODO: Quitar esta salida
        %[rad2deg(giro_robot(i)) giro_robot_target];
    
end %del while
%catch ME
%    errorMessage = sprintf('ERROR:\n\n%s', ME.message);
%    uiwait(warndlg(errorMessage));
%    send_email(errorMessage);
%end
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Para motores y cierra sensores
%-------------------------------
    
 Para;
