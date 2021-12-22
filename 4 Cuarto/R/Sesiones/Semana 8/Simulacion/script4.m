clear all
clc
cla
 
%Conexion
IPaddress='192.168.0.132';
Robot_ID='00165380547c';
robot  = legoev3('wifi',IPaddress,Robot_ID);
motor_izquierdo = motor(robot,'B');
motor_derecho = motor(robot,'C');
start(motor_izquierdo);
start(motor_derecho);

s = tcpserver(40144);
beep(robot);

step = 0;
while true
    data = read(s, 7, "uint8");
    if length(data) == 7 && data(1) == 240 && mod(data(2)+data(3)+data(4)+data(5)+data(6), 256) == data(7)
        if mod(step, 3) == 0
            pwmL = data(2)/255.0;
            pwmR = data(3)/255.0;
            if data(4) == 0
                pwmL = -pwmL;
            end
            if data(5) == 0
                pwmR = -pwmR;
            end
    
            motor_izquierdo.Speed = pwmL*100;
            motor_derecho.Speed = pwmR*100;
        end
        step = step+1;
    else
        error = data
    end
end