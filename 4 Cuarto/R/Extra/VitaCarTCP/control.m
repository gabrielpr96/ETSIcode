clear s;
s = tcpserver(40144);

ROT_SPEED = 0.0005;
TRANS_SPEED = 0.0005;

x = 0;
y = 0;
rot = 0;

vez = 0;
while true
    data = read(s, 6, "uint8");
    if length(data) == 6 && data(1) == 240 && mod(data(2)+data(3)+data(4)+data(5), 256) == data(6)
        salida = data;
        pwmL = data(2)/2.55;
        pwmR = data(3)/2.55;
        if data(4) == 0
            pwmL = -pwmL;
        end
        if data(5) == 0
            pwmR = -pwmR;
        end
        vel = sqrt(pwmL^2 + pwmR^2)*TRANS_SPEED;
        if pwmL < 0 && pwmR < 0
            vel = -vel;
        end
        rot = rot + (pwmR-pwmL)*ROT_SPEED; %Invertir si gira dle reves
        x = x + cos(rot)*vel;
        y = y + sin(rot)*vel;
        vez = vez + 1;
        if vez == 5
            pinta_robot(x, y, rot, 0);
            vez = 0;
        end
    else
        error = data
    end
end