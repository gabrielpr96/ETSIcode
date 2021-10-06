package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implementa operaciones sobre la tabla CASO
 */
public class ManejaCaso {

    // Crea un objeto de tipo "conexionOracle"
    ConexionOracle conexion = null;

    // Crea un PreparedStatement como atributo de la clase manejaExperto para
    // utilizarlo en los diferentes métodos
    PreparedStatement ps = null;

    private ManejaLog ml;

    /**
     *
     * @param c Conexión a la BD
     */
    public ManejaCaso(ConexionOracle c) {
        conexion = c;
        ml = new ManejaLog(c);
    }

    /**
     * Comprueba si existe un Caso en la tabla de CASO_POLICIAL dado un Código
     * de caso
     *
     * @param codCaso Código del caso a buscar
     * @return Verdadero si el Caso existe
     * @throws SQLException si ocurre alguna anomalía
     */
    public boolean existeCaso(String codCaso) throws SQLException {
        ps = conexion.getConexionOracle().prepareStatement("SELECT CODCASO FROM CASO_POLICIAL WHERE CODCASO=?");
        ps.setString(1, codCaso);
        ResultSet rs = ps.executeQuery();
        boolean existe = rs.next();
        ps.close();
        return existe;
    }

    /**
     * Devuelve la lista de Casos sin filtrar
     *
     * @return Lista de Casos
     * @throws SQLException si ocurre alguna anomalía
     */
    public ArrayList<Caso> listaCasos() throws SQLException {
        ArrayList<Caso> lista = new ArrayList<>();
        ResultSet rs = conexion.getConexionOracle().createStatement().executeQuery("SELECT * FROM CASO_POLICIAL");
        while (rs.next()) {
            lista.add(new Caso(rs.getString("CODCASO"), rs.getString("NOMBRE"), rs.getString("FECHA_INICIO"), rs.getString("FECHA_FIN")));
        }
        return lista;
    }
    
    public ArrayList<Caso> listaCasosFiltroEnCurso(String fechaFiltro) throws SQLException {
        ArrayList<Caso> lista = new ArrayList<>();
        ps = conexion.getConexionOracle().prepareStatement("SELECT * FROM CASO_POLICIAL WHERE FECHA_FIN IS NULL AND FECHA_INICIO < ?");
        ps.setString(1, fechaFiltro);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lista.add(new Caso(rs.getString("CODCASO"), rs.getString("NOMBRE"), rs.getString("FECHA_INICIO"), rs.getString("FECHA_FIN")));
        }
        return lista;
    }
    
    public ArrayList<Caso> listaCasosEnCurso() throws SQLException {
        ArrayList<Caso> lista = new ArrayList<>();
        ResultSet rs = conexion.getConexionOracle().createStatement().executeQuery("SELECT * FROM CASO_POLICIAL WHERE FECHA_FIN IS NULL");
        while (rs.next()) {
            lista.add(new Caso(rs.getString("CODCASO"), rs.getString("NOMBRE"), rs.getString("FECHA_INICIO"), rs.getString("FECHA_FIN")));
        }
        return lista;
    }

    /**
     * Inserta Caso en la tabla de CASO_POLICIAL
     *
     * @param cs Caso a insertar
     * @throws SQLException si ocurre alguna anomalía
     */
    public void insertaCaso(Caso cs) throws SQLException {
        try {
            conexion.inicioTransaccion();

            ps = conexion.getConexionOracle().prepareStatement("INSERT INTO CASO_POLICIAL VALUES (?,?,?,?)");
            ps.setString(1, cs.getCodCaso());
            ps.setString(2, cs.getNombre());
            ps.setString(3, cs.getFechaInicio());
            ps.setString(4, cs.getFechaFin());
            ps.executeUpdate();
            ps.close();
            
            ml.insertaLog("Insertar", "Caso");

            conexion.finTransaccionCommit();
        } catch (SQLException e) {
            conexion.finTransaccionRollback();
            throw e;
        }
    }

    /**
     * Elimina un Caso por su Código
     *
     * @param codCaso Código del Caso a eliminar
     * @throws SQLException si ocurre alguna anomalía
     */
    public void eliminaCaso(String codCaso) throws SQLException {
        try {
            conexion.inicioTransaccion();

            ps = conexion.getConexionOracle().prepareStatement("DELETE FROM CASO_POLICIAL WHERE CODCASO = ?");
            ps.setString(1, codCaso);
            ps.executeUpdate();
            ps.close();

            ml.insertaLog("Eliminar", "Caso");

            conexion.finTransaccionCommit();
        } catch (SQLException e) {
            conexion.finTransaccionRollback();
            throw e;
        }
    }

    /**
     * Devuelve un Caso buscado por su Código
     *
     * @param CodCaso Código del Caso a buscar
     * @return Caso con el Código proporcuinado
     * @throws SQLException si ocurre alguna anomalía
     */
    public Caso obtenerCasoPorCodigo(String CodCaso) throws SQLException {
        Caso caso = null;
        ps = conexion.getConexionOracle().prepareStatement("SELECT * FROM CASO_POLICIAL WHERE CODCASO = ?");
        ps.setString(1, CodCaso);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            caso = new Caso(rs.getString("CODCASO"), rs.getString("NOMBRE"), rs.getString("FECHA_INICIO"), rs.getString("FECHA_FIN"));
        }
        rs.close();
        ps.close();
        return caso;
    }
}
