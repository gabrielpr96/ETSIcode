clear all
clc
cla
 
%condiciones inciales
x = 0;
y = 0;
theta = 0;

global r
global l
r=3;
l=12;

s = tcpserver(40144);

pinta_robot_v4(x, y, theta, 0, 2.55, []);
while true
    data = read(s, 7, "uint8");
    if length(data) == 7 && data(1) == 240 && mod(data(2)+data(3)+data(4)+data(5)+data(6), 256) == data(7)
        pwmL = data(2)/255.0;
        pwmR = data(3)/255.0;
        if data(4) == 0
            pwmL = -pwmL;
        end
        if data(5) == 0
            pwmR = -pwmR;
        end

        [x, y, theta] = calculo_odometria([0 pwmR], [0 pwmL], [x 0], [y, 0], [theta 0], 2);

        pinta_robot_v4(x, y, theta, deg2rad(double(data(6)-90)), 2.55, []);
    else
        error = data
    end
end