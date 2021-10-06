package patronprototypemasfactory;

public class CreadorConcreto extends Creador{
    private int tipo;
    private Figura f;
    private boolean circulo, cuadrado;
    
    public CreadorConcreto(){
        circulo = false;
        cuadrado = false;
    }
    
    @Override
    public Figura Factory_method(int t) {
        switch(t){
            case 1:
                f = new Circulo();
                circulo = true;
                cuadrado = false;
                return f;
            case 2:
                if(circulo)
                    return f.clonar();
            break;
            case 3:
                f = new Cuadrado();
                cuadrado = true;
                circulo = false;
                return f;
            case 4:
                if(cuadrado)
                    return f.clonar();
            break;
        }
        System.out.println("No se ha podido crear");
        return null;
    }
    
}
