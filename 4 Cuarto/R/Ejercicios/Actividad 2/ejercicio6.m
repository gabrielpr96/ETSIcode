% Ejercicio 6

clear all;
k = 1;
for s = 0 : .05 : 30
    x(k) = s;
    y(k) = N(s);
    k = k + 1;
end
plot(x, y);

clear x y;
syms x;

assume(x > 1 & x < 30);
f = -5*exp(-.2*x)*cos(.9*x-deg2rad(30))+.8*exp(-2*x)+5;
fp = diff(f, x);
fpp = diff(fp, x);
mejor = solve(fp==0&fpp<0, x);
mejor = 3.85 % Lo pongo yo porque el solve no lo encuentra
mejorValor = N(mejor)

% Este es otro metodo que he visto por Internet, pero se basa en lo mismo y
% tampoco funciona por culpa del solve o algo que escapa a mis habilidades
% maticaticas.
%f = -5*exp(-.2*x)*cos(.9*x-deg2rad(30))+.8*exp(-2*x)+5;
%extremos = solve(diff(f,x)==0,x);
%valoresExtremos = subs(f, x, extremos);
%[maximo, maximoI] = max(valoresExtremos);
%mejor = extremos(maximoI);
%mejorValor = simplify(maximo, 'steps', 50);

hold on
plot(double(mejor), double(mejorValor), 'Or');
