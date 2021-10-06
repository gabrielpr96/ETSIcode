
public class Dos extends Uno{
    @Override
    public Dos yoMismo(){return super.yoMismo();}
    //No funciona porque super.yoMismo() devuelve un objeto de tipo Uno, que no puede ser referenciado por un tipo Dos. 
}
