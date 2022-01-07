 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 % Manda las señales de control a los motores de tracción:
 % motor_derecho y motro izquierdo
 %------------------------------------

 if Power1 > 100
     Power1 = 100;
 else
     if  Power1 <- 100
         Power1 =- 100;
     end
 end

 if Power2 > 100
     Power2 = 100;
 else
     if  Power2 <- 100
         Power2 =- 100;
     end
 end

 motor_derecho.Speed = Power1;
 motor_izquierdo.Speed = Power2;


