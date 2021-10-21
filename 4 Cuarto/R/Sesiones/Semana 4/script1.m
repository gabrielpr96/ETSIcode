clear all;
cla;

esquinas = [-1.5 -1.5 6 1; -1.5 1.5 6 1; 1.5 -1.5 6 1; 1.5 1.5 6 1];

pinta_bloque(eye(4, 4), 'g');

for j = 1:4
    plot3(esquinas(j, 1), esquinas(j, 2), esquinas(j, 3), '*r')
end