cla;

tStart = tic();
tElapsed = 0;
while tElapsed < 10
    tElapsed = toc(tStart);
    pinta_robot_v4(-5, -5, signal_vf(tElapsed, 3, 6, pi/2), 0, 2.55, []);
    %
end