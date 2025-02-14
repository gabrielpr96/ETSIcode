package Persistencia;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class manejaColabora {

    // Creamos un objeto de tipo "conexionOracle"
    conexionOracle conexion = null;

    // Creamos un PreparedStatement como atributo de la clase manejaExperto para
    // utilizarlo en los diferentes métodos
    PreparedStatement ps = null;

    /**
     * Implementa operaciones sobre la tabla COLABORA
     *
     * @param c conexión con Oracle
     */
    public manejaColabora(conexionOracle c) {
        conexion = c;
    }

    /**
     * Comprueba si existe una colaboración en la tabla de COLABORA dado su
     * código
     *
     * @param codExperto, codCaso caso
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
     * Inserta una colaboración en la tabla COLABORA
     *
     * @param codExperto, codCaso caso
     * @throws SQLException si ocurre alguna anomalía
     */
    public void insertaColaboracion(colabora col) throws SQLException {
        ps = conexion.getConexionOracle().prepareStatement("INSERT INTO COLABORA VALUES (?,?,?,?)");
        ps.setString(1, col.getCodExperto());
        ps.setString(2, col.getCodCaso());
        ps.setString(3, col.getFecha());
        ps.setString(4, col.getDescripcionColaboracion());
        ps.executeUpdate();
        ps.close();
    }
}
