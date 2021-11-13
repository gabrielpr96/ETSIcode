package ejercicio3;

import com.b0ve.solucionintegraciongenerica.adapters.Adapter;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.Process;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AdaptadorCRM3Salida extends Adapter {

    private Connection conn;
    private final AdaptadorCRM3Entrada adaptadorEntrada;

    public AdaptadorCRM3Salida(AdaptadorCRM3Entrada adaptadorEntrada) {
        this.adaptadorEntrada = adaptadorEntrada;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejercicio3", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Document sendApp(Message m) {
        try {
            //En realidad por cada mensaje solo hay un cambio, pero es faicilmente modificable
            NodeList cambios = m.evaluateXPath("/cambio");
            for (int i = 0; i < cambios.getLength(); i++) {
                Document doc = Message.node2document(cambios.item(0));
                String tipo = Message.evaluateXPath(doc, "/cambio/tipo").item(0).getTextContent();
                String dni = Message.evaluateXPath(doc, "/cambio/datos/dni").item(0).getTextContent();
                if (tipo.equals("crear")) {
                    String nombre = Message.evaluateXPath(doc, "/cambio/datos/nombre").item(0).getTextContent();
                    Statement stmt = conn.createStatement();
                    stmt.execute("INSERT INTO `Clientes` (`DNI`, `Nombre`) VALUES ('" + dni + "', '" + nombre + "')");
                    stmt.close();
                    NodeList direcciones = Message.evaluateXPath(doc, "/cambio/datos/direccion");
                    String dirs = "";
                    for (int j = 0; j < direcciones.getLength(); j++) {
                        dirs += "('" + dni + "', '" + direcciones.item(j).getTextContent() + "'),";
                    }
                    if (!dirs.isEmpty()) {
                        dirs = dirs.substring(0, dirs.length() - 1);
                        stmt = conn.createStatement();
                        stmt.execute("INSERT INTO `Direcciones` (`Cliente`, `Direccion`) VALUES " + dirs);
                        stmt.close();
                        adaptadorEntrada.addConocido(dni);
                    }
                } else if (tipo.equals("eliminar")) {
                    Statement stmt = conn.createStatement();
                    stmt.execute("DELETE FROM `Clientes` WHERE `DNI` = '" + dni + "'");
                    stmt.close();
                    adaptadorEntrada.removeConocido(dni);
                }
            }
        } catch (SQLException | ParserConfigurationException  ex) {
            Logger.getLogger(AdaptadorCRM3Entrada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XPathExpressionException | SAXException | IOException ex) {
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
