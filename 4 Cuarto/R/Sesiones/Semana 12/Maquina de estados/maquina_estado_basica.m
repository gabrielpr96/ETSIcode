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

    stop_distance=30; %distancia de para ante obstáculo
    t_marcha_atras=0.2; %tiempo de marcha hacia atrás.
    transicion=1;% inicializa la variable que marca el inicio el mov de la cabeza
    t_giro_cabeza=10; %Girar la cabeza tarda 6 segundos
    t_max_giro_robot=8;
    KP_CABEZA = 0.7;
    KP_GIRO = 1.2;
    giro_robot_margen = 5;


%comienza el bucle
    disp('pulsa el bumper para comenzar')

while(readTouch(Pulsador)==0) 
end

while(readTouch(Pulsador)==1)
end

disp('comienza el bucle')
%try
beep(mi_Robot);
while  readTouch(Pulsador)==0
  
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
                 if (distancia(i)<stop_distance) %si la distancia es menor que stop_distance para
                    estado=2; %transición de estado de paro
                    transicion=i; %indice que marca el inicio del estado 2
                    mi_Robot.beep();
                 elseif readTouch(Detecta_colision)==1
                    estado=5; %transición a marcha atras
                    transicion=i; %indice que marca el inicio del estado 2
                    mi_Robot.beep();
                end
                
            
            case 2 %parando
                if (vel==0)
                    if distancia(i)>stop_distance
                        estado=1; %la transición a estado marcha hacia delante
                    else
                        estado=5; %transición a marcha atras
                    end
                    transicion=i; %indice que marca el inicio del estado 3
                    mi_Robot.beep();
                end
             
            case 3 %girando cabeza
                if (t(i)-t(transicion)>t_giro_cabeza)
                    deg_mejor = calculo_referencia(distancia, giro_cabeza, transicion);
                    giro_robot_target = rad2deg(giro_robot(i)) + deg_mejor;

                    estado=4; %la transición a estado girando robot
                    transicion=i; %indice que marca el inicio del estado 3
                    mi_Robot.beep();
                end
                
            case 4 %girando robot
                if (abs(giro_robot_error) <= giro_robot_margen) || (t(i)-t(transicion)>t_max_giro_robot)
                    estado=2; %la transición a parado
                    transicion=i; %indice que marca el inicio del estado 5
                    mi_Robot.beep();
                end
                
           case 5 %marcha atrás
                if (t(i)-t(transicion)>t_marcha_atras)
                    estado=3; %transición a estado girando cabeza
                    transicion=i; %indice que marca el inicio del estado 2
                    mi_Robot.beep();
                end
            
        end %del siwtch
    
   %-----------------------
          
    
    %-------------------------------------------------
    %SALIDAS ASOCIADAS A LOS ESTADOS
    
        switch estado
        
            case 1 %andando hacia delante
            %establece los valores de control
                vel=30;
                correction_steer = giro_robot_error*KP_GIRO;
                Power1=vel+correction_steer;
                Power2=vel-correction_steer;
                Power_cabeza = 0;

            case 2 %parando
              %establece los valores de control 
                vel=0;
                Power1=vel;
                Power2=vel;
                Power_cabeza = 0;
        
            case 3 %girando cabeza
                giro_cabeza_target = signal_vf(t(i)-t(transicion), 1, t_giro_cabeza-3, 100);
                
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
          
        end %del siwtch

        pinta_robot_v4(x(i), y(i), giro_robot(i), deg2rad(double(giro_cabeza(i))), distancia(i), []);

      %---------------------
        %Manda los comandos de control a los motores
      %-------------        
        Traction_motor_control;   

        %TODO: Quitar esta salida
        [rad2deg(giro_robot(i)) giro_robot_target]
    
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
