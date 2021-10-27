
cla;

tStart = tic();
tElapsed = 0;
while tElapsed < 2*pi
    tElapsed = toc(tStart);
    pinta_robot(sin(tElapsed)*5, -5, tElapsed, 0);
end

