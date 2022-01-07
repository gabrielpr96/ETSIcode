%Esperar a que se pulse el boton
disp('pulsa el bumper para comenzar')
while(readTouch(Pulsador)==0) 
end
while(readTouch(Pulsador)==1)
end

%Mas iniciaciones
tstart = tic;