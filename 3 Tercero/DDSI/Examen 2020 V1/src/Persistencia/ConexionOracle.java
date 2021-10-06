package Persistencia;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ConexionOracle {

    private Connection conn = null;

    /**
     * Establece la conexión con el servidor
     *
     * @param user Usuario
     * @param pass Contraseña
     * @throws Exception si ocurre cualquier anormalidad
     */
    public ConexionOracle(String user, String pass) throws Exception {
        conn = DriverManager.getConnection("jdbc:oracle:thin:@172.17.20.75:1521:rabida", user, pass);
        //conn = DriverManager.getConnection("jdbc:oracle:thin:@b0ve.com:1539:XE", user, pass);
        crearLog("Conexion", null);
    }

    /**
     * Implementa la desconexión con el servidor
     *
     * @throws SQLException si ocurre cualquier anormalidad
     */
    public void desconexion() throws SQLException {
        crearLog("Desconexion", null);
        conn.close();
    }

    /**
     * Devuelve la propiedad Connection
     *
     * @return conn
     * @throws SQLException si ocurre cualquier anormalidad
     */
    public Connection getConexionOracle() throws SQLException {
        return conn;
    }

    /**
     * Inicia una transacción
     *
     * @throws SQLException si ocurre cualquier anormalidad
     */
    public void inicioTransaccion() throws SQLException {
        conn.setAutoCommit(false);
    }

    /**
     * Finaliza una transacción con commint
     *
     * @throws SQLException si ocurre cualquier anormalidad
     */
    public void finTransaccionCommit() throws SQLException {
        conn.commit();
        conn.setAutoCommit(true);
    }

    /**
     * Finaliza una transacción con rollback
     *
     * @throws SQLException si ocurre cualquier anormalidad
     */
    public void finTransaccionRollback() throws SQLException {
        conn.rollback();
        conn.setAutoCommit(true);
    }

    public void crearLog(String operacion, String tabla) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO LOG VALUES (?, ?, ?, ?)");
        ps.setString(1, conn.getMetaData().getUserName());
        ps.setString(2, (new SimpleDateFormat("dd/MM/yyyy' 'hh:mm:ss")).format(new Date()));
        ps.setString(3, operacion);
        ps.setString(4, tabla);
        ps.executeUpdate();
        ps.close();
    }

    public ArrayList<Log> listarLog() throws SQLException {
        ArrayList<Log> logs = new ArrayList<>();
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM LOG ORDER BY FECHA");
        while (rs.next()) {
            logs.add(new Log(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
        }
        return logs;
    }

}
