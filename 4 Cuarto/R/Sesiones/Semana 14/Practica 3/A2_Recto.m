%Iniciacion
Iniciar

%Constantes
global r_rueda
global l_semi

%Estado inicial
tf=60;

%Objetivo: Ir de (0, 0) a (40, 0)
pose0 = [x(i); y(i); theta(i)];
posef = [60; 0; theta(i)];

%Dibujado
figure
hold on
plot(pose0(1), pose0(2), 'r*')
plot(posef(1), posef(2), 'r*')
axis equal

%Esperar pulsador
EsperarPulsador

while readTouch(Pulsador) == 0 && t(i) < tf
    i = i + 1;
    t(i) = toc(tstart);

    %Lectura y calculo odometria
    Signal_reading_odo_path_following;
    [x(i), y(i), theta(i)] = calculo_odometria(giro_derecho, giro_izquierdo, x, y, theta, i);
    theta_deg(i) = rad2deg(double(theta(i)));

    %Busqueda del objetivo
    punto = posef(1:2);

    %Control geometrico
    delta = (x(i) - punto(1)) * sin(theta(i)) - (y(i) - punto(2)) * cos(theta(i));
    LH = sqrt((x(i) - punto(1))^2 + (y(i) - punto(2))^2);
    rho = 2 * delta / LH^2;

    %Control proporcional
    distancia_total = sqrt((x(i) - posef(1))^2 + (y(i) - posef(2))^2);
    velocidad = KP * distancia_total;
    if velocidad > 15
        velocidad = 15;
    end
    if distancia_total < 3
        break
    end

    %Modelo inverso
    velocidad_derecha = velocidad * (1 + l_semi * rho) / r_rueda;
    velocidad_izquierda = velocidad * (1 - l_semi * rho) / r_rueda;

    Power1 = velocidad_derecha * conversion_potencia;
    Power2 = velocidad_izquierda * conversion_potencia;
    Power1_a(i) = Power1;
    Power2_a(i) = Power2;

    %Ejecucion
    Traction_motor_control_laboratorio;
    
    %Dibujado
    plot(x(i), y(i), 'bo')
end

%Parar
Para;
