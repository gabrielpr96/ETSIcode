package libClases;

public class Alumno extends Persona{
    String universidad;
    
    public Alumno(String universidad, String nombre, int edad, int nTelefonos){
        super(nombre, edad, nTelefonos);
        this.universidad = universidad;
    }
    public Alumno(String universidad){
        super();
        this.universidad = universidad;
    }
    public Alumno(String universidad, Persona p){
        super(p);
        this.universidad = universidad;
    }
    
    public void cambiarUniversidad(String uni){
        System.out.println(getNombre()+" se ha cambiado de la universidad "+universidad+" a la universidad "+uni);
        universidad = uni;
    }
    
    @Override
    public String toString(){
        return super.toString()+". Estudia en la universidad "+universidad;
    }
}
