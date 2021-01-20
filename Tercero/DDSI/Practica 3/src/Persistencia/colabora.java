package Persistencia;

/**
 * Colabora
 */
public class colabora {

    private String codExperto;
    private String codCaso;
    private String fecha;
    private String descripcionColaboracion;

    /**
     * Constructor por defecto
     */
    public colabora() {
        codExperto = null;
        codCaso = null;
        fecha = null;
        descripcionColaboracion = null;
    }

    /**
     * Constructor con parametros
     *
     * @ param ce, cc, f, dc
     */
    public colabora(String ce, String cc, String f, String dc) {
        codExperto = ce;
        codCaso = cc;
        fecha = f;
        descripcionColaboracion = dc;
    }

    public String getCodExperto() {
        return codExperto;
    }

    public String getCodCaso() {
        return codCaso;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDescripcionColaboracion() {
        return descripcionColaboracion;
    }

    public void setCodExperto(String ce) {
        codExperto = ce;
    }

    public void setCodcaso(String cc) {
        codCaso = cc;
    }

    public void setFecha(String f) {
        fecha = f;
    }

    public void setDescripcionColaboracion(String dc) {
        descripcionColaboracion = dc;
    }
    
    @Override
    public String toString(){
        return descripcionColaboracion;
    }
}
