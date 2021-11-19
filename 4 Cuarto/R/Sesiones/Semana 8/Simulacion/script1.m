clear all
clc
cla
tstart=tic;
i=1;
t(i)=toc(tstart);
 
%condiciones inciales
encoder1(i)=0;
encoder2(i)=0;
x(i)=0;
y(i)=0;
theta(i)=0;
 
global r
global l
r=3;
l=1;

 
while t(i)<15
    i=i+1;
    t(i)=toc(tstart);

    %velocidades de las ruedas.
    w1=0.2;
    w2=0.5;
    
    %simulamos la evolución de los encoders   
    encoder1(i)=w1*t(i);
    encoder2(i)=w2*t(i);
    
    %simulamos la odometría
    [x(i), y(i), theta(i)] = calculo_odometria(encoder1, encoder2, x, y, theta, i);
    
    
    %Dibujamos la simulación
    pinta_robot_v1(x(i), y(i), theta(i), 0);
end
 
 plot(t,encoder1,t,encoder2)
 
   