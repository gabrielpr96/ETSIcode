clear all;
cla;
clc;


%robot = legoev3('bluetooth', 'COM5')
robot = legoev3('USB');
motor_cabeza = motor(robot,'A');
motor_izq = motor(robot,'B');
motor_der = motor(robot,'C');
sonar = sonicSensor(robot, 3);
gyro = gyroSensor(robot, 4);

motor_cabeza.start();
motor_cabeza.Speed = 0;
motor_cabeza.resetRotation();
motor_izq.start();
motor_izq.Speed = 0;
motor_izq.resetRotation();
motor_der.start();
motor_der.Speed = 0;
motor_der.resetRotation();
gyro.resetRotationAngle();

s = tcpserver(40144);

KP = 1.2;

x = 0;
y = 0;
pinta_robot_v3(0, 0, 0, 0, 2.55);
mapa = [];
vez = 0;
while true
    data = read(s, 7, "uint8");
    if length(data) == 7 && data(1) == 240 && mod(data(2)+data(3)+data(4)+data(5)+data(6), 256) == data(7)
        salida = data;
        pwmL = data(2)/2.55;
        pwmR = data(3)/2.55;
        if data(4) == 0
            pwmL = -pwmL;
        end
        if data(5) == 0
            pwmR = -pwmR;
        end
        motor_izq.Speed = pwmL;
        motor_der.Speed = pwmR;
        targetAlpha = data(6)-90;

        alpha = double(motor_cabeza.readRotation());
        error = targetAlpha - alpha;
        if abs(error ) > 1
            vel = error*KP;
            if vel > 10
                vel = 10;
            elseif vel < -10
                vel = -10;
            end
            motor_cabeza.Speed = vel;
        else
            motor_cabeza.Speed = 0;
        end
        vez = vez + 1;
        if vez == 5
            distancia = double(sonar.readDistance());
            pinta_robot_v3(x, y, deg2rad(double(gyro.readRotationAngle())), deg2rad(alpha), distancia, mapa);
            vez = 0;
        end
    else
        error = data
    end
end