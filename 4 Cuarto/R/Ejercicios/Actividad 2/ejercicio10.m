% Ejercicio 6



elapsed = 0;
tStart = tic();
while elapsed < 4*pi
    l = sin(elapsed);
    elapsed = toc(tStart);

    cla;
    plot3([-l -l l l -l -l -l l l -l -l -l l l l l], [-l l l -l -l -l l l -l -l l l l l -l -l], [-l -l -l -l -l l l l l l l -l -l l l -l]);
    axis([-1 1 -1 1 -1 1]);
    drawnow;
end