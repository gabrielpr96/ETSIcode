k = 1;

for s = 0 : 0.01 : 2*pi
    t(k) = s;
    y(k) = signal_v0(t(k));
    k = k + 1;
end

sUltimo = s
tstart = tic();
telapsed = 0;

while telapsed < 10
    telapsed = toc(tstart);
    t(k) = sUltimo+telapsed;
    y(k) = signal_v0(t(k));
    k = k + 1;
end

plot(t, y);