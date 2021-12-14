function new_map = calculo_mapa(new_cloud, x, y, theta)
    new_map = zeros(length(new_cloud), 2);
    for s=1:length(new_cloud)
        T = makehgtform('translate', [0 0 0], 'zrotate', new_map(s, 1));
        D = T*[new_map(s, 2), 0, 0, 1];
        new_map(s) = D(1:2);
    end
    new_map = pointCloud([new_map(:, 1)' new_map(:, 2)' zeros(length(new_map))'])
end

