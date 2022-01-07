%Iniciacion
Iniciar

%Constantes
global r_rueda
global l_semi
global camino
global punto

%Estado inicial
tf=60;

%Objetivo: Ir de (0, 0) pasar por (30, 30) a (60, 60) y aparcar en (100,
%115) con orientacion pi/2
pose0 = [x(i); y(i); theta(i)];
pose1 = [30; 30];
pose2 = [60; 60];
posef = [100, 115, pi/2];

dd = 8;
Pdx = pose0(1) + dd*cos(pose0(3));
Pdy = pose0(2) + dd*sin(pose0(3));
da = 20;
Pax = posef(1) - da*cos(posef(3));
Pay = posef(2) - da*sin(posef(3));

camino = funcion_spline_cubica_varios_puntos([pose0(1) Pdx pose1(1) pose2(1) Pax posef(1)], [pose0(2) Pdy pose1(2) pose2(2) Pay posef(2)], 5)';
p = 1;

%Dibujado
figure
hold on
plot(pose0(1), pose0(2), 'r*')
plot(Pdx, Pdy, 'c*')
plot(pose1(1), pose1(2), 'r*')
plot(pose2(1), pose2(2), 'r*')
plot(Pax, Pay, 'c*')
plot(posef(1), posef(2), 'r*')
plot(camino(:, 1), camino(:, 2), 'g')
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

    %Busqueda del objetivo Seguimiento
    p = minima_distancia(camino, [x(i) y(i)]);
    while sqrt(power(x(i)-camino(p, 1), 2) + power(y(i)-camino(p, 2), 2)) < 5 && p < length(camino)
        p = p + 1;
        %Creo que este if se puede eliminar
        if p > length(camino)
            p = length(camino);
        end
    end
    punto = camino(p, :);
    final = camino(end, :);

    %Control geometrico
    delta = (x(i) - punto(1)) * sin(theta(i)) - (y(i) - punto(2)) * cos(theta(i));
    LH = sqrt((x(i) - punto(1))^2 + (y(i) - punto(2))^2);
    rho = 2 * delta / LH^2;

    %Control proporcional
    distancia_total = sqrt((x(i) - final(1))^2 + (y(i) - final(2))^2);
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
