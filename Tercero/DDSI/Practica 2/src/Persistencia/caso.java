package Persistencia;

/**
 * Caso
 */

public class caso {

    private String codCaso;
    private String nombre;
    private String fechaInicio;
    private String fechaFin;

    /**
     * Constructor por defecto
     */
    public caso() {
        codCaso = null;
        nombre = null;
        fechaInicio = null;
        fechaFin = null;
    }

    /**
     * Constructor con parametros
     *
     * @param cc, n, fi, ff
     */
    public caso(String cc, String n, String fi, String ff) {
        codCaso = cc;
        nombre = n;
        fechaInicio = fi;
        fechaFin = ff;
    }

    public String getCodCaso() {
        return codCaso;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setCodCaso(String c) {
        codCaso = c;
    }

    public void setNombre(String n) {
        nombre = n;
    }

    public void setFechaInicio(String fi) {
        fechaInicio = fi;
    }

    public void setFechaFin(String ff) {
        fechaFin = ff;
    }

}
