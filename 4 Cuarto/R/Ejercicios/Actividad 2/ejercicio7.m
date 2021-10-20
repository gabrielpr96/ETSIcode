% Ejercicio 6

clear all;
k = 1;
for s = 0 : 0.01 : 4*pi
    x(k) = s;
    y(k) = sin(s);
    k = k + 1;
end

plot(x, y);

elapsed = 0;
tStart = tic();
while elapsed < 4*pi
    posX = elapsed;
    posY = sin(elapsed);
    elapsed = toc(tStart);

    cla;
    hold on;
    plot(x, y);
    plot(posX, posY, "Or");
    drawnow;
end