package Persistencia;

/**
 * Mapeo de la entidad Caso
 */
public class Caso {

    private String codCaso;
    private String nombre;
    private String fechaInicio;
    private String fechaFin;

    /**
     * Constructor por defecto
     */
    public Caso() {
        codCaso = null;
        nombre = null;
        fechaInicio = null;
        fechaFin = null;
    }

    /**
     * Constructor con parametros
     *
     * @param cc Código del Caso
     * @param n Nombre del Caso
     * @param fi Fecha Inicio
     * @param ff Fecha Fin
     */
    public Caso(String cc, String n, String fi, String ff) {
        codCaso = cc;
        nombre = n;
        fechaInicio = fi;
        fechaFin = ff;
    }

    /**
     * Devuelve codCaso
     *
     * @return Codigo del caso
     */
    public String getCodCaso() {
        return codCaso;
    }

    /**
     * Devuelve el nombre
     *
     * @return Nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve FechaInicio
     *
     * @return Fecha de inicio
     */
    public String getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Devuelve FechaFin
     *
     * @return Fecha de fin
     */
    public String getFechaFin() {
        return fechaFin;
    }

    /**
     * Establece codCaso
     *
     * @param c Código del caso
     */
    public void setCodCaso(String c) {
        codCaso = c;
    }

    /**
     * Establece el nombre
     *
     * @param n Nombre
     */
    public void setNombre(String n) {
        nombre = n;
    }

    /**
     * Establece FechaInicio
     *
     * @param fi Fecha de inicio
     */
    public void setFechaInicio(String fi) {
        fechaInicio = fi;
    }

    /**
     * Establece FechaFin
     *
     * @param ff Fecha de fin
     */
    public void setFechaFin(String ff) {
        fechaFin = ff;
    }

    /**
     * @return El nombre
     */
    @Override
    public String toString() {
        return nombre;
    }

}
