package Persistencia;

import java.sql.*;
import java.util.ArrayList;

public class manejaExperto {
    // Se crea un objeto de tipo "conexionOracle"
    conexionOracle conexion = null;
    
    // Se crea el PreparedStatement como atributo de la clase manejaExperto para
    // utilizarlo en los diferentes métodos
    PreparedStatement ps = null;
    

    /**
    * Implementa operaciones sobre la tabla EXPERTO
    * @param c conexión con Oracle
    */    
    public manejaExperto(conexionOracle c) {
      conexion = c;
    }
    
    /**
    * Devuelve una lista con todos los expertos cuyo país se pase por parámetro
    * @param pais
    * @throws SQLException si ocurre alguna anomalía
    * @return ArrayList<experto>
    */
    public ArrayList<experto> listaExpertosPorPais(String pais) throws SQLException {
        ArrayList<experto> lista = new ArrayList<>();
        ps = conexion.getConexionOracle().prepareStatement("SELECT * FROM EXPERTO WHERE PAIS=?");
        ps.setString(1, pais);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
            lista.add(new experto(rs.getString("CODEXPERTO"), rs.getString("NOMBRE"), rs.getString("PAIS"), rs.getString("SEXO"), rs.getString("ESPECIALIDAD")));
        ps.close();
        return lista;
    }    
    
    /**
    * Comprueba si existe un experto
    * @param codExperto
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
    * @param exp
    * @throws SQLException si ocurre alguna anomalía
     */
    public void insertaExperto(experto exp) throws SQLException {
        ps = conexion.getConexionOracle().prepareStatement("INSERT INTO EXPERTO (CODEXPERTO, NOMBRE, PAIS, SEXO, ESPECIALIDAD) VALUES (?,?,?,?,?)");
        ps.setString(1, exp.getCodExperto());
        ps.setString(2, exp.getNombre());
        ps.setString(3, exp.getPais());
        ps.setString(4, exp.getSexo());
        ps.setString(5, exp.getEspecialidad());
        ps.executeUpdate();
        ps.close();
    }
}