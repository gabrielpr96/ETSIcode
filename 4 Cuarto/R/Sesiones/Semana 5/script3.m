cla;

tStart = tic();
tElapsed = 0;
while tElapsed < 10
    tElapsed = toc(tStart);
    pinta_robot_v1(-5, -5, 0, signal_vf(tElapsed, 3, 6, pi/2));
end