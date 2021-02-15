package Persistencia;

/**
 * Mapeo de la entidad Experto
 */
public class Experto {

    private String codExperto;
    private String nombre;
    private String pais;
    private String sexo;
    private String especialidad;

    /**
     * Constructor por defecto
     */
    public Experto() {
        codExperto = null;
        nombre = null;
        pais = null;
        sexo = null;
        especialidad = null;

    }

    /**
     * Constructor con parametros
     *
     * @param c Código de Experto
     * @param n Nombre
     * @param p País
     * @param s Sexo
     * @param e Especialidad
     */
    public Experto(String c, String n, String p, String s, String e) {
        codExperto = c;
        nombre = n;
        pais = p;
        sexo = s;
        especialidad = e;
    }

    /**
     * Devuelve codExperto
     *
     * @return Codigo del Experto
     */
    public String getCodExperto() {
        return codExperto;
    }

    /**
     * Devuelve Nombre
     *
     * @return Nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve País
     *
     * @return País
     */
    public String getPais() {
        return pais;
    }

    /**
     * Devuelve Sexo
     *
     * @return Sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Devuelve Especialidad
     *
     * @return Especialidad
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Establece codExperto
     *
     * @param c Código del Experto
     */
    public void setCodExperto(String c) {
        codExperto = c;
    }

    /**
     * Establece el Nombre
     *
     * @param n Nombre
     */
    public void setNombre(String n) {
        nombre = n;
    }

    /**
     * Establece País
     *
     * @param p País
     */
    public void setPais(String p) {
        pais = p;
    }

    /**
     * Establece Sexo
     *
     * @param s Sexo F Femenino o M Masculino
     */
    public void setSexo(String s) {
        sexo = s;
    }

    /**
     * Establece Especialidad
     *
     * @param e Especialidad
     */
    public void setEspecialidad(String e) {
        especialidad = e;
    }

    /**
     *
     * @return El Nombre
     */
    @Override
    public String toString() {
        return nombre;
    }
}
