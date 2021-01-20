package Persistencia;

/**
 * Mapeo de la relación Colabora
 */
public class Colabora {

    private String codExperto;
    private String codCaso;
    private String fecha;
    private String descripcionColaboracion;

    /**
     * Constructor por defecto
     */
    public Colabora() {
        codExperto = null;
        codCaso = null;
        fecha = null;
        descripcionColaboracion = null;
    }

    /**
     * Constructor con parametros
     *
     * @param ce Código del Experto
     * @param cc Código del Caso
     * @param f Fecha de la Colaboración
     * @param dc Descripción de la Colaboración
     */
    public Colabora(String ce, String cc, String f, String dc) {
        codExperto = ce;
        codCaso = cc;
        fecha = f;
        descripcionColaboracion = dc;
    }

    /**
     * Devuelve codExperto
     *
     * @return Código del Experto
     */
    public String getCodExperto() {
        return codExperto;
    }

    /**
     * Devuelve codCaso
     *
     * @return Código del Caso
     */
    public String getCodCaso() {
        return codCaso;
    }

    /**
     * Devuelve Fecha
     *
     * @return Fecha de la Colaboración
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Devuelve DescripciónColaboración
     *
     * @return Descripción de la Colaboración
     */
    public String getDescripcionColaboracion() {
        return descripcionColaboracion;
    }

    /**
     * Establece codExperto
     *
     * @param ce Código del Experto
     */
    public void setCodExperto(String ce) {
        codExperto = ce;
    }

    /**
     * Establece codCaso
     *
     * @param cc Código del Caso
     */
    public void setCodcaso(String cc) {
        codCaso = cc;
    }

    /**
     * Establece Fecha
     *
     * @param f Fecha de la Colaboración
     */
    public void setFecha(String f) {
        fecha = f;
    }

    /**
     * Establece DescripciónColaboración
     *
     * @param dc Descripción de la Colaboración
     */
    public void setDescripcionColaboracion(String dc) {
        descripcionColaboracion = dc;
    }

    /**
     * @return Descripción de la Colaboración
     */
    @Override
    public String toString() {
        return descripcionColaboracion;
    }
}
