function mapa = pinta_robot_v3(x, y, theta, alpha, distancia, mapa)
    persistent SR_robot SR_rueda_izquierda SR_rueda_derecha  SR_cabeza SR_ojo_izq SR_ojo_der;

    %Esquina inferior izquierda (-1.5, -1.5) y tamaño anchura 3, altura 3 (Centrado)
    robot_size = [-1.5 -1.5 3 3];
    rueda_size = [-0.5 -0.1 1 0.2];
    rueda_izquierda_pos = [0 -1 0];
    rueda_derecha_pos = [0 1 0];
    cabeza_size =[-0.25 -0.5 0.5 1];
    cabeza_pos = [1 0 0];
    ojo_size = [-0.15, -0.15, 0.3, 0.3];
    ojo_izq_pos = [0.15 -0.25 0];
    ojo_der_pos = [0.15 0.25 0];

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

    axis([-10 10 -10 10]);

    T = SR_robot.Matrix * SR_cabeza.Matrix;
    punto = T*[distancia, 0, 0, 1]';
    mapa = [mapa; punto(1), punto(2)];
    animatedline(mapa(:, 1), mapa(:, 2), 'Marker','*', 'LineStyle','none');

    drawnow;
end