function mapa = pinta_robot_v4(x, y, theta, alpha, distancia, mapa)
    persistent SR_robot SR_rueda_izquierda SR_rueda_derecha  SR_cabeza SR_ojo_izq SR_ojo_der SR_bumper;

    %Esquina inferior izquierda (-1.5, -1.5) y tamaño anchura 3, altura 3 (Centrado)
    robot_size = [-12 -8.5 21 17];
    rueda_size = [-3 -1.5 6 3];
    rueda_izquierda_pos = [0 -6 0];
    rueda_derecha_pos = [0 6 0];
    cabeza_size =[-1 -2.75 2 5.5];
    cabeza_pos = [5 -5.5 0];
    ojo_size = [-.5, -1, 1, 2];
    ojo_izq_pos = [0.75 -1.25 0];
    ojo_der_pos = [0.75 1.25 0];
    bumper_size = [-4, -8.5, 8, 17];
    bumper_pos = [13 0 0];

    if isempty(SR_robot) || ~isvalid(SR_robot)
        SR_robot = hgtransform;
        rectangle('Position', robot_size, 'Parent', SR_robot);
    end
    SR_robot.Matrix = makehgtform('translate', [x y 0], 'zrotate', theta);
    
    if isempty(SR_rueda_derecha) || ~isvalid(SR_rueda_derecha)
        SR_rueda_derecha = hgtransform('Parent', SR_robot);
        rectangle('Position', rueda_size, 'Parent', SR_rueda_derecha);
    end
    SR_rueda_derecha.Matrix = makehgtform('translate', rueda_izquierda_pos);
    
    if isempty(SR_rueda_izquierda) || ~isvalid(SR_rueda_izquierda)
        SR_rueda_izquierda = hgtransform('Parent', SR_robot);
        rectangle('Position', rueda_size, 'Parent', SR_rueda_izquierda);
    end
    SR_rueda_izquierda.Matrix = makehgtform('translate', rueda_derecha_pos);
    
    if isempty(SR_cabeza) || ~isvalid(SR_cabeza)
        SR_cabeza = hgtransform('Parent', SR_robot);
        rectangle('Position', cabeza_size, 'Parent', SR_cabeza);
    end
    SR_cabeza.Matrix = makehgtform('translate', cabeza_pos, 'zrotate', alpha);

    if isempty(SR_ojo_izq) || ~isvalid(SR_ojo_izq)
        SR_ojo_izq = hgtransform('Parent', SR_cabeza);
        rectangle('Position', ojo_size, 'Parent', SR_ojo_izq, 'FaceColor', 'r');
    end
    SR_ojo_izq.Matrix = makehgtform('translate', ojo_izq_pos);

    if isempty(SR_ojo_der) || ~isvalid(SR_ojo_der)
        SR_ojo_der = hgtransform('Parent', SR_cabeza);
        rectangle('Position', ojo_size, 'Parent', SR_ojo_der, 'FaceColor', 'r');
    end
    SR_ojo_der.Matrix = makehgtform('translate', ojo_der_pos);

    if isempty(SR_bumper) || ~isvalid(SR_bumper)
        SR_bumper = hgtransform('Parent', SR_robot);
        rectangle('Position', bumper_size, 'Parent', SR_bumper);
    end
    SR_bumper.Matrix = makehgtform('translate', bumper_pos);

    axis([-100 100 -100 100]);

    if(distancia < 250)
        T = SR_robot.Matrix * SR_cabeza.Matrix;
        punto = T*[double(distancia), 0, 0, 1]';
        mapa = [mapa; punto(1), punto(2)];
        animatedline(mapa(:, 1), mapa(:, 2), 'Marker','*', 'LineStyle','none');
    end

    drawnow;
end