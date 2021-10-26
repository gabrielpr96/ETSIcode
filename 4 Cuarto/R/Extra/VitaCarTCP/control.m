clear s;
s = tcpserver(40144);

while true
    data = read(s, 6, "uint8");
    if length(data) == 6 && data(1) == 240 && mod(data(2)+data(3)+data(4)+data(5), 256) == data(6)
        salida = data
    else
        error = data
    end
end