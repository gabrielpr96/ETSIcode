package examen2015;

public class ExcepcionGravedad extends Exception{
    public ExcepcionGravedad(int gravedad){
        super("El valor de gravedad \""+gravedad+"\" no es valido. El rango permitido es [0, 3]");
    }
}
