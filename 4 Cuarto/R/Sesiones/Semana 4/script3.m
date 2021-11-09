cla;

tStart = tic();
tElapsed = 0;
while tElapsed < 2*pi
    tElapsed = toc(tStart);
    pinta_robot_v1(sin(tElapsed)*5, -5, 0, tElapsed);
end

