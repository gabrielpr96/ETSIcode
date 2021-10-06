package Persistencia;

import java.sql.*;

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
    }

    /**
     * Implementa la desconexión con el servidor
     *
     * @throws SQLException si ocurre cualquier anormalidad
     */
    public void desconexion() throws SQLException {
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

}
