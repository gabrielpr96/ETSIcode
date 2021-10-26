for i = 0 : 0.05 : 20
    M = makehgtform('translate', i, 0, 0);
    cla;
    pinta_bloque(M, 'g');
    drawnow;
end