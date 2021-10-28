function pinta_robot(x, y, theta, alpha)
    persistent SR_robot SR_rueda_izquierda SR_rueda_derecha  SR_cabeza;

    %Esquina inferior izquierda (-1.5, -1.5) y tamaño anchura 3, altura 3 (Centrado)
    robot_size = [-1.5 -1.5 3 3];
    rueda_size = [-0.5 -0.1 1 0.2];
    rueda_izquierda_pos = [0 -1 0];
    rueda_derecha_pos = [0 1 0];
    cabeza_size =[-0.25 -0.5 0.5 1];
    cabeza_pos = [1 0 0];

    if isempty(SR_robot) || ~isvalid(SR_robot)
        SR_robot = hgtransform;
    end
    rectangle('Position', robot_size, 'Parent', SR_robot);
    SR_robot.Matrix = makehgtform('translate', [x y 0], 'zrotate', theta);
    
    if isempty(SR_rueda_derecha) || ~isvalid(SR_rueda_derecha)
        SR_rueda_derecha = hgtransform('Parent', SR_robot);
    end
    rectangle('Position', rueda_size, 'Parent', SR_rueda_derecha);
    SR_rueda_derecha.Matrix = makehgtform('translate', rueda_izquierda_pos);
    
    if isempty(SR_rueda_izquierda) || ~isvalid(SR_rueda_izquierda)
        SR_rueda_izquierda = hgtransform('Parent', SR_robot);
    end
    rectangle('Position', rueda_size, 'Parent', SR_rueda_izquierda);
    SR_rueda_izquierda.Matrix = makehgtform('translate', rueda_derecha_pos);
    
    if isempty(SR_cabeza) || ~isvalid(SR_cabeza)
        SR_cabeza = hgtransform('Parent', SR_robot);
    end
    rectangle('Position', cabeza_size, 'Parent', SR_cabeza);
    SR_cabeza.Matrix = makehgtform('translate', cabeza_pos, 'zrotate', alpha);

    axis([-10 10 -10 10]);
    drawnow;
end

