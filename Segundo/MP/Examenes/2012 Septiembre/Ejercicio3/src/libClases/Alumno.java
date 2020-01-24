package libClases;

public abstract class Alumno {
    private final String id;
    private String nombre, apellido1, direccion;
    private Asignatura[] asignaturas;
    private Alumno[] alumnos;
    
    public Alumno(String id, String nombre, String apellido1, String direccion, int maxAsignaturas) throws ExceptionFormatoInvalido{
        if(id.length() > 9) throw new ExceptionFormatoInvalido("ID demasiado larga.");
        if(nombre.length() > 20) throw new ExceptionFormatoInvalido("Nombre demasiado largo.");
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.direccion = direccion;
        asignaturas = new Asignatura[maxAsignaturas];
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
    public String getID(){
        return id;
    }
    
    public void addAsignatura(int codigo){
         boolean insertado = false;
        int i = 0;
        while(i < asignaturas.length && insertado == false){
            if(asignaturas[i] == null){
                asignaturas[i] = new Asignatura(codigo, 0, false);
                insertado = true;
            }else i++;
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
    public Alumno buscarAlumno(int posicion){
        return alumnos[posicion];
    }
    
    @Override
    public String toString(){
        StringBuffer b = new StringBuffer();
        b.append(id).append(" ").append(nombre).append(" ").append(apellido1).append(" asignaturas: \n");
        for(Asignatura a : asignaturas)
            if(a != null)
                b.append(a).append("\n");
        return b.toString();
    }
    
}
