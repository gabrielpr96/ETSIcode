% Ejercicio 6

clear all;
k = 1;
for s = 0 : .05 : 30
    x(k) = s;
    y(k) = N(s);
    k = k + 1;
end
plot(x, y);

symvar x;