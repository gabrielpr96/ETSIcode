classdef PIDcontroller
    properties
        kp
        ki
        kd
        tau
        limMin
        limMax
        limMinInt
        limMaxInt
        integrator
        prevError
        differentiator
        prevMeasurement
        out
    end
    
    methods
        function obj = PIDcontroller(kp, ki, kd, tau, limMin, limMax, limMinInt, limMaxInt)
           obj.kp = kp;
           obj.ki = ki;
           obj.kd = kd;
           obj.tau = tau;
           obj.limMin = limMin;
           obj.limMax = limMax;
           obj.limMinInt = limMinInt;
           obj.limMaxInt = limMaxInt;
           obj.integrator = 0.0;
           obj.prevError = 0.0;
           obj.differentiator = 0.0;
           obj.prevMeasurement = 0.0;
           obj.out = 0.0;
        end
        
        function obj = update(obj, setPoint, measurement, delta)
            %Error
            error = setPoint - measurement;
            
            %Componente proporcional
            proportional = obj.kp * error;
            
            %Componente integral
            obj.integrator = obj.integrator + 0.5 * obj.ki * delta * (error + obj.prevError);
            if obj.integrator > obj.limMaxInt
                obj.integrator = obj.limMaxInt;
            elseif obj.integrator < obj.limMinInt
                obj.integrator = obj.limMinInt;
            end

            %Componente derivada
            obj.differentiator = -(2.0 * obj.kd * (measurement - obj.prevMeasurement) + (2.0 * obj.tau - delta) * obj.differentiator);
            
            %Calcular salida y aplicar limites
            obj.out = proportional + obj.integrator + obj.differentiator;
            if obj.out > obj.limMax
                obj.out = obj.limMax;
            elseif obj.out < obj.limMin
                obj.out = obj.limMin;
            end

            %Guardar las medidas y errores
            obj.prevError = error;
            obj.prevMeasurement = measurement;
        end
    end
end

