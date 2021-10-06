package Persistencia;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

/**
 * Implementa operaciones sobre la tabla COLABORA
 */
public class ManejaColabora {

    // Creamos un objeto de tipo "conexionOracle"
    ConexionOracle conexion = null;

    // Creamos un PreparedStatement como atributo de la clase manejaExperto para
    // utilizarlo en los diferentes métodos
    PreparedStatement ps = null;

    /**
     *
     * @param c Conexión a la BD
     */
    public ManejaColabora(ConexionOracle c) {
        conexion = c;
    }

    /**
     * Devuelve la lista de las Colaboraciones filtradas por Experto
     *
     * @param codExperto Código de Experto para filtrar
     * @return Lista de Colaboraciones
     * @throws SQLException
     */
    public ArrayList<Colabora> listaColaboraPorExperto(String codExperto) throws SQLException {
        ArrayList<Colabora> lista = new ArrayList<>();
        ps = conexion.getConexionOracle().prepareStatement("SELECT * FROM COLABORA WHERE CODEXPERTO=?");
        ps.setString(1, codExperto);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lista.add(new Colabora(rs.getString("CODEXPERTO"), rs.getString("CODCASO"), rs.getString("FECHA"), rs.getString("DESCRIPCION_COLABORACION")));
        }
        ps.close();
        return lista;
    }

    /**
     * Comprueba si existe una Colaboración en la tabla de COLABORA dado su
     * código
     *
     * @param codExperto Código del Experto
     * @param codCaso Código del Caso
     * @return Verdadero si la colaboración existe
     * @throws SQLException si ocurre alguna anomalía
     */
    public boolean existeColaboracion(String codExperto, String codCaso) throws SQLException {
        ps = conexion.getConexionOracle().prepareStatement("SELECT CODEXPERTO, CODCASO FROM COLABORA WHERE CODEXPERTO=? CODCASO=?");
        ps.setString(1, codExperto);
        ps.setString(2, codCaso);
        ResultSet rs = ps.executeQuery();
        boolean existe = rs.next();
        ps.close();
        return existe;
    }

    /**
     * Inserta una Colaboración en la tabla COLABORA
     *
     * @param col Colaboración a Insertar
     * @throws SQLException si ocurre alguna anomalía
     */
    public void insertaColaboracion(Colabora col) throws SQLException {
        ps = conexion.getConexionOracle().prepareStatement("INSERT INTO COLABORA VALUES (?,?,?,?)");
        ps.setString(1, col.getCodExperto());
        ps.setString(2, col.getCodCaso());
        ps.setString(3, col.getFecha());
        ps.setString(4, col.getDescripcionColaboracion());
        ps.executeUpdate();
        ps.close();
        conexion.crearLog("Inserta", "COLABORA");
    }

    /**
     * Devuelve la lista de Colaboraciones filtrada por Caso
     *
     * @param codCaso Código del Caso
     * @return Lista de Colaboraciones por Caso
     * @throws SQLException si ocurre alguna anomalía
     */
    public ResultSet listaColaboradoresPorCaso(String codCaso) throws SQLException {
        CallableStatement stmt = conexion.getConexionOracle().prepareCall("{call pColaboradoresCaso(?, ?)}");
        stmt.setString(1, codCaso);
        stmt.registerOutParameter(2, OracleTypes.CURSOR);
        stmt.executeUpdate();
        return (ResultSet) stmt.getObject(2);
    }

    /**
     * Devuelve la lista de Colaboraciones sin filtrar
     *
     * @return Lista de Colaboraciones
     * @throws SQLException si ocurre alguna anomalía
     */
    public ArrayList<Colabora> listaColabora() throws SQLException {
        ArrayList<Colabora> lista = new ArrayList<>();
        ResultSet rs = conexion.getConexionOracle().createStatement().executeQuery("SELECT * FROM COLABORA");
        while (rs.next()) {
            lista.add(new Colabora(rs.getString("CODEXPERTO"), rs.getString("CODCASO"), rs.getString("FECHA"), rs.getString("DESCRIPCION_COLABORACION")));
        }
        return lista;
    }

    /**
     * Elimina una Colaboración
     *
     * @param codExperto Código del Experto
     * @param codCaso Código del Caso
     * @param fecha Fecha de la Colaboración
     * @throws SQLException si ocurre alguna anomalía
     */
    public void eliminaColabora(String codExperto, String codCaso, String fecha) throws SQLException {
        ps = conexion.getConexionOracle().prepareStatement("DELETE FROM COLABORA WHERE CODEXPERTO = ? AND CODCASO = ? AND FECHA = ?");
        ps.setString(1, codExperto);
        ps.setString(2, codCaso);
        ps.setString(3, fecha);
        ps.executeUpdate();
        ps.close();
        conexion.crearLog("Elimina", "COLABORA");
    }
}
