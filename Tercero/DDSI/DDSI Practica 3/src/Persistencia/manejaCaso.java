package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class manejaCaso {

    // Crea un objeto de tipo "conexionOracle"
    conexionOracle conexion = null;

    // Crea un PreparedStatement como atributo de la clase manejaExperto para
    // utilizarlo en los diferentes métodos
    PreparedStatement ps = null;

    /**
     * Implementa operaciones sobre la tabla CASO
     *
     * @param c conexión con Oracle
     */
    public manejaCaso(conexionOracle c) {
        conexion = c;
    }

    /**
     * Comprueba si existe un caso en la tabla de CASO_POLICIAL dado un código
     * de caso
     *
     * @param codCaso código del caso a buscar
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
     * Inserta caso en la tabla de CASO_POLICIAL
     *
     * @param cs caso a insertar
     * @throws SQLException si ocurre alguna anomalía
     */
    public void insertaCaso(caso cs) throws SQLException {
        ps = conexion.getConexionOracle().prepareStatement("INSERT INTO CASO_POLICIAL VALUES (?,?,?,?)");
        ps.setString(1, cs.getCodCaso());
        ps.setString(2, cs.getNombre());
        ps.setString(3, cs.getFechaInicio());
        ps.setString(4, cs.getFechaFin());
        ps.executeUpdate();
        ps.close();
    }
}
