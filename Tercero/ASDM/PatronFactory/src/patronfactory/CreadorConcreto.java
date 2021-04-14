package patronfactory;

public class CreadorConcreto extends Creador{
    protected String tipo;

    public CreadorConcreto(String tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public Conexion creaConexion(){
        switch(tipo.toLowerCase()){
            case "mysql": return new ConexionMySQL();
            case "oracle": return new ConexionOracle();
            case "postgresql": return new ConexionPostgreSQL();
            default: return new Conexion();
        }
    }
    
}
