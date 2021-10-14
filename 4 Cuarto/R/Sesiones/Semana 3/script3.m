clear all;
clc;

delay = 4;
periodo = 6;
amplitud = 1;

k=1;
for s = 0 : 0.01 : 4*pi
    t(k) = s;
    y(k) = signal_vf(t(k), delay, periodo, amplitud);
    k = k+1;
end

k = 1;
tiempo(k) = 0;
tstart = tic();
while tiempo(k) < 4*pi
    cla;
    hold on;

    y2(k) = signal_vf(tiempo(k), delay, periodo, amplitud);

    plot(t, y);
    plot(tiempo(k), y2(k), 'Or');

    axis([0 4*pi -1 1]);
    drawnow;

    k = k+1;
    tiempo(k) = toc(tstart);
end