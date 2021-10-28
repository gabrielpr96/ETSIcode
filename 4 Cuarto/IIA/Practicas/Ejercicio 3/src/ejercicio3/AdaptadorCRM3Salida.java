package ejercicio3;

import com.b0ve.solucionintegraciongenerica.adaptadores.Adaptador;
import com.b0ve.solucionintegraciongenerica.utils.JDBCUtil;
import com.b0ve.solucionintegraciongenerica.utils.excepciones.ExecutionException;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AdaptadorCRM3Salida extends Adaptador {

    private Connection conn;

    public AdaptadorCRM3Salida() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejercicio3", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void enviarApp(Mensaje m) {
        try {
            NodeList cambios = m.evaluateXPath("/cambios/cambio");
            for (int i = 0; i < cambios.getLength(); i++) {
                Document doc = Mensaje.node2document(cambios.item(0));
                String tipo = Mensaje.evaluateXPath(doc, "/cambio/tipo").item(0).getTextContent();
                String dni = Mensaje.evaluateXPath(doc, "/cambio/datos/dni").item(0).getTextContent();
                if (tipo.equals("crear")) {
                    String nombre = Mensaje.evaluateXPath(doc, "/cambio/datos/nombre").item(0).getTextContent();
                    Statement stmt = conn.createStatement();
                    stmt.execute("INSERT INTO `Clientes` (`DNI`, `Nombre`) VALUES ('" + dni + "', '" + nombre + "')");
                    stmt.close();
                    NodeList direcciones = Mensaje.evaluateXPath(doc, "/cambio/datos/direccion");
                    String dirs = "";
                    for (int j = 0; j < direcciones.getLength(); j++) {
                        dirs += "('" + dni + "', '" + direcciones.item(j).getTextContent() + "'),";
                    }
                    if (!dirs.isEmpty()) {
                        dirs = dirs.substring(0, dirs.length() - 1);
                        stmt = conn.createStatement();
                        stmt.execute("INSERT INTO `Direcciones` (`Cliente`, `Direccion`) VALUES " + dirs);
                        stmt.close();
                    }
                } else if (tipo.equals("eliminar")) {
                    Statement stmt = conn.createStatement();
                    stmt.execute("DELETE FROM `Clientes` WHERE `DNI` = '" + dni + "'");
                    stmt.close();
                }
            }
        } catch (SQLException | ParserConfigurationException  ex) {
            Logger.getLogger(AdaptadorCRM3Entrada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XPathExpressionException | SAXException | IOException ex) {
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
