package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ManejaLog {

    private ConexionOracle conexion = null;

    private PreparedStatement ps = null;
    
    private static final SimpleDateFormat espFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public ManejaLog(ConexionOracle conexion) {
        this.conexion = conexion;
    }

    public ArrayList<Log> listaLogs() throws SQLException {
        ArrayList<Log> lista = new ArrayList<>();
        ResultSet rs = conexion.getConexionOracle().createStatement().executeQuery("SELECT * FROM LOG ORDER BY FECHA");
        while (rs.next()) {
            lista.add(new Log(rs.getString("USUARIO"), rs.getString("FECHA"), rs.getString("OPERACION"), rs.getString("TABLA")));
        }
        return lista;
    }

    public void insertaLog(String operacion, String tabla) throws SQLException {
        ps = conexion.getConexionOracle().prepareStatement("INSERT INTO LOG (USUARIO, FECHA, OPERACION, TABLA) VALUES (?,?,?,?)");
        ps.setString(1, conexion.getConexionOracle().getMetaData().getUserName());
        ps.setString(2, espFecha.format(new Date()));
        ps.setString(3, operacion);
        ps.setString(4, tabla);
        ps.executeUpdate();
        ps.close();
    }

    public void limpiarLogs() throws SQLException {
        conexion.getConexionOracle().createStatement().executeUpdate("DELETE FROM LOG");
    }
}
