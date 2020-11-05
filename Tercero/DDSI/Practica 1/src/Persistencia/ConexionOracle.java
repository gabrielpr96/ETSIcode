package Persistencia;

import java.sql.*;

public class ConexionOracle {

    private final Connection link;

    public ConexionOracle() throws SQLException {
        link = DriverManager.getConnection("jdbc:oracle:thin:@172.17.20.75:1521:rabida", "DDSI_012", "DDSI_012");
    }

    public void DesconexionOracle() throws SQLException {
        link.close();
    }

    public DatabaseMetaData informacionBD() throws SQLException {
        return link.getMetaData();
    }
}
