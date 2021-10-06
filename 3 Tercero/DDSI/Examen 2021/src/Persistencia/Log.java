package Persistencia;

public class Log {
    private String usuario, fecha, operacion, tabla;

    public Log() {
    }

    public Log(String usuario, String fecha, String operacion, String tabla) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.operacion = operacion;
        this.tabla = tabla;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public String getOperacion() {
        return operacion;
    }

    public String getTabla() {
        return tabla;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    
}
