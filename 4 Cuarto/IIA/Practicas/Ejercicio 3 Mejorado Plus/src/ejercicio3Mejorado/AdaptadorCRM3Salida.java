package ejercicio3Mejorado;

import com.b0ve.solucionintegraciongenerica.adapters.Adapter;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.Process;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;

public class AdaptadorCRM3Salida extends Adapter {

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
    public Document sendApp(Message m) {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(m.evaluateXPathString("sql"));
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdaptadorCRM3Salida.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void halt() {
        if (conn != null) try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdaptadorCRM3Salida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Process.PORTS getCompatiblePortType() {
        return Process.PORTS.OUTPUT;
    }

}
