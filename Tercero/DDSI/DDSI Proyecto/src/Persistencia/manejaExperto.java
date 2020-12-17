package Persistencia;

import java.sql.*;
import java.util.ArrayList;

/**
 * Implementa operaciones sobre la tabla EXPERTO
 */
public class ManejaExperto {

    // Se crea un objeto de tipo "conexionOracle"
    ConexionOracle conexion = null;

    // Se crea el PreparedStatement como atributo de la clase manejaExperto para
    // utilizarlo en los diferentes métodos
    PreparedStatement ps = null;

    /**
     * @param c Conexión a la BD
     */
    public ManejaExperto(ConexionOracle c) {
        conexion = c;
    }

    /**
     * Devuelve una lista con todos los Expertos cuyo País se pase por parámetro
     *
     * @param pais País a filtrar
     * @return Lista de Expertos
     * @throws SQLException si ocurre alguna anomalía
     */
    public ArrayList<Experto> listaExpertosPorPais(String pais) throws SQLException {
        ArrayList<Experto> lista = new ArrayList<>();
        ps = conexion.getConexionOracle().prepareStatement("SELECT * FROM EXPERTO WHERE PAIS=?");
        ps.setString(1, pais);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lista.add(new Experto(rs.getString("CODEXPERTO"), rs.getString("NOMBRE"), rs.getString("PAIS"), rs.getString("SEXO"), rs.getString("ESPECIALIDAD")));
        }
        ps.close();
        return lista;
    }

    /**
     * Devuelve una lista con todos los Expertos sin filtrar
     *
     * @return Lista de Expertos
     * @throws SQLException si ocurre alguna anomalía
     */
    public ArrayList<Experto> listaExpertos() throws SQLException {
        ArrayList<Experto> lista = new ArrayList<>();
        ResultSet rs = conexion.getConexionOracle().createStatement().executeQuery("SELECT * FROM EXPERTO");
        while (rs.next()) {
            lista.add(new Experto(rs.getString("CODEXPERTO"), rs.getString("NOMBRE"), rs.getString("PAIS"), rs.getString("SEXO"), rs.getString("ESPECIALIDAD")));
        }
        return lista;
    }

    /**
     * Comprueba si existe un Experto por su Código
     *
     * @param codExperto Código de Experto
     * @return Verdadero si el Experto existe
     * @throws SQLException si ocurre alguna anomalía
     */
    public boolean existeExperto(String codExperto) throws SQLException {
        ps = conexion.getConexionOracle().prepareStatement("SELECT CODEXPERTO FROM EXPERTO WHERE CODEXPERTO=?");
        ps.setString(1, codExperto);
        ResultSet rs = ps.executeQuery();
        boolean existe = rs.next();
        ps.close();
        return existe;
    }

    /**
     * inserta un experto
     *
     * @param exp Experto a insertar
     * @throws SQLException si ocurre alguna anomalía
     */
    public void insertaExperto(Experto exp) throws SQLException {
        ps = conexion.getConexionOracle().prepareStatement("INSERT INTO EXPERTO (CODEXPERTO, NOMBRE, PAIS, SEXO, ESPECIALIDAD) VALUES (?,?,?,?,?)");
        ps.setString(1, exp.getCodExperto());
        ps.setString(2, exp.getNombre());
        ps.setString(3, exp.getPais());
        ps.setString(4, exp.getSexo());
        ps.setString(5, exp.getEspecialidad());
        ps.executeUpdate();
        ps.close();
    }

    /**
     * Cuenta la cantidad de Expertos de un Sexo determinado
     *
     * @param sexo Sexo
     * @return Cantidad de Expertos con el Sexo especificado
     * @throws SQLException si ocurre alguna anomalía
     */
    public int sexoExperto(String sexo) throws SQLException {
        CallableStatement stmt = conexion.getConexionOracle().prepareCall("{? = call fSexoExperto(?)}");
        stmt.setString(2, sexo);
        stmt.registerOutParameter(1, Types.INTEGER);
        stmt.executeUpdate();
        int res = stmt.getInt(1);
        stmt.close();
        return res;
    }

    /**
     * Elimina a un Experto por su Código de Experto
     *
     * @param codExperto Código de Experto
     * @throws SQLException si ocurre alguna anomalía
     */
    public void eliminaExperto(String codExperto) throws SQLException {
        ps = conexion.getConexionOracle().prepareStatement("DELETE FROM EXPERTO WHERE CODEXPERTO = ?");
        ps.setString(1, codExperto);
        ps.executeUpdate();
        ps.close();
    }

    /**
     * Devuelve un Experto por su Código de Experto
     *
     * @param codExperto Cñodigo de Experto
     * @return Experto con el Código especificado
     * @throws SQLException si ocurre alguna anomalía
     */
    public Experto obtenerExpertoPorCodigo(String codExperto) throws SQLException {
        Experto experto = null;
        ps = conexion.getConexionOracle().prepareStatement("SELECT * FROM EXPERTO WHERE CODEXPERTO=?");
        ps.setString(1, codExperto);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            experto = new Experto(rs.getString("CODEXPERTO"), rs.getString("NOMBRE"), rs.getString("PAIS"), rs.getString("SEXO"), rs.getString("ESPECIALIDAD"));
        }
        rs.close();
        ps.close();
        return experto;
    }
}
