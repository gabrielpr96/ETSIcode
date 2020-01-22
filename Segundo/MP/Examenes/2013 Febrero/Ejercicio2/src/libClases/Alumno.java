package libClases;

import java.util.ArrayList;

public class Alumno {
    private final int id;
    private String nombre, apellido1, direccion;
    private ArrayList<Asignatura> asignaturas;
    private Alumno[] alumnos;
    
    public Alumno(int id, String nombre, String apellido1, String direccion){
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.direccion = direccion;
        asignaturas = new ArrayList<>();
        alumnos = new Alumno[10];
    }
    
    public void setNombre(String s){
        nombre = s;
    }
    public void setApellido1(String s){
        apellido1 = s;
    }
    public void setDireccion(String s){
        direccion = s;
    }
    
    public String getNombre(){
        return nombre;
    }
    public String getApellido1(){
        return apellido1;
    }
    public String getDireccion(){
        return direccion;
    }
    public int getID(){
        return id;
    }
    
    public void addAsignatura(Asignatura a){
        asignaturas.add(a);
    }
    public Asignatura getAsignatura(int codigo){
        Asignatura a = null;
        int i = 0;
        while(i < asignaturas.size() && a == null)
            if(asignaturas.get(i).getCodigo() == codigo)
                a = asignaturas.get(i);
        return a;
    }
    public boolean deleteAsignatura(int codigo){
        Asignatura a = getAsignatura(codigo);
        if(a == null)
            return false;
        else{
            asignaturas.remove(a);
            return true;
        }
    }
    
    public boolean addAlumno(Alumno a){
        boolean insertado = false;
        int i = 0;
        while(i < alumnos.length && insertado == false){
            if(alumnos[i] == null){
                alumnos[i] = a;
                insertado = true;
            }else i++;
        }
        
        return insertado;
    }
    public boolean deleteAlumno(int codigo){
        boolean eliminado = false;
        int i = 0;
        while(i < alumnos.length && eliminado == false){
            if(alumnos[i] != null && alumnos[i].getID() == id){
                alumnos[i] = null;
                eliminado = true;
            }else i++;
        }
        
        return eliminado;
    }
    public Alumno buscarAlumno(int posicion) throws ExcepcionNoexiste{
        if(posicion < 0 || posicion >= alumnos.length || alumnos[posicion] == null)
            throw new ExcepcionNoexiste(posicion);
        else return alumnos[posicion];
    }
    
    @Override
    public String toString(){
        StringBuffer b = new StringBuffer();
        b.append(id).append(" ").append(nombre).append(" ").append(apellido1).append(" asignaturas: \n");
        for(Asignatura a : asignaturas)
            b.append(a).append("\n");
        return b.toString();
    }
    
}
