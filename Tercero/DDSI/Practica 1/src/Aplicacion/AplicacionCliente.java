package Aplicacion;

import Persistencia.ConexionOracle;
import java.sql.*;

public class AplicacionCliente {

    public static void main(String[] args) {
        try {
            ConexionOracle conn = new ConexionOracle();
            try {
                DatabaseMetaData datos = conn.informacionBD();
                System.out.println("Version de la base de datos: " + datos.getDatabaseProductVersion()
                        + "\nURL de la conexion: " + datos.getURL()
                        + "\nNombre del driver: " + datos.getDriverName()
                        + "\nNombre de usuario: " + datos.getUserName()
                        + "\nNumero maximo de caracteres para el nombre de usuario: " + datos.getMaxUserNameLength()
                        + "\nNumero maximo de columnas en una tabla: " + datos.getMaxColumnsInTable());
            } catch (SQLException e) {
                logError(e);
            }
            conn.DesconexionOracle();
        } catch (SQLException e) {
            logError(e);
        }
    }

    private static void logError(SQLException e) {
        System.err.println("Codigo de error: " + e.getErrorCode()
                + "\nCodigo de estado: " + e.getSQLState()
                + "\nMensaje: " + e.getMessage());
        e.printStackTrace();
    }
}
