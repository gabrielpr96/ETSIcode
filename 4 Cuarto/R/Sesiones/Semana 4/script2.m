clear all;
cla;

esquinas = [-1.5 -1.5 6 1; -1.5 1.5 6 1; 1.5 -1.5 6 1; 1.5 1.5 6 1];

T = makehgtform('translate', 5, 5, 5)*makehgtform('xrotate', pi/2)*makehgtform('zrotate', pi/4);

pinta_bloque(T, 'g');

for j = 1:4
    esquinas(j,:) = (T*esquinas(j,:)')';
    plot3(esquinas(j, 1), esquinas(j, 2), esquinas(j, 3), '*r')
end