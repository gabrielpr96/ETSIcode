k = 1;
tstart = tic();
telapse d = 0;

while telapsed < 10
    telapsed = toc(tstart);
    t(k) = telapsed;
    y(k) = signal_v0(t(k));
    k = k + 1;
end

plot(t, y);