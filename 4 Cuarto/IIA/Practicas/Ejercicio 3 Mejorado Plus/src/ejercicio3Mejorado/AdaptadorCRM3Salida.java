package ejercicio3Mejorado;

import com.b0ve.solucionintegraciongenerica.adaptadores.Adaptador;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdaptadorCRM3Salida extends Adaptador {

    private Connection conn;

    public AdaptadorCRM3Salida() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejercicio3?allowMultiQueries=true", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void enviarApp(Mensaje m) {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(m.evaluateXPathString("sql"));
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdaptadorCRM3Salida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void detener() {
        if (conn != null) try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdaptadorCRM3Salida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
