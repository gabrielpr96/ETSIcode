package ejercicio2;

import com.b0ve.solucionintegraciongenerica.adapters.Adapter;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.ExecutionException;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.Process;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AdaptadorBD extends Adapter {

    private Connection conn;
    private Thread hilo;
    private final List<String> conocidos;

    public AdaptadorBD() {
        conocidos = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejercicio2", "root", "");
            hilo = new Thread() {
                @Override
                public void run() {
                    while (!isInterrupted()) {
                        try {
                            synchronized (AdaptadorBD.this) {
                                List<String> nuevos = new ArrayList<>();
                                Statement stmt = conn.createStatement();
                                ResultSet rs = stmt.executeQuery("SELECT `TS` FROM `medidas`");
                                while (rs.next()) {
                                    String ts = rs.getString(1);
                                    if (!conocidos.contains(ts)) {
                                        nuevos.add(ts);
                                        conocidos.add(ts);
                                    }
                                }
                                rs.close();
                                stmt.close();
                                if (!nuevos.isEmpty()) {
                                    for (String ts : nuevos) {
                                        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                                        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                                        Document doc = docBuilder.newDocument();
                                        Element root = doc.createElement("medida");
                                        doc.appendChild(root);

                                        stmt = conn.createStatement();
                                        rs = stmt.executeQuery("SELECT `Lugar`, `Valor` FROM `medidas` WHERE `TS` = '" + ts + "'");
                                        rs.next();

                                        Element eTS = doc.createElement("ts");
                                        eTS.setTextContent(ts);
                                        root.appendChild(eTS);
                                        Element eLugar = doc.createElement("lugar");
                                        eLugar.setTextContent(rs.getString(1));
                                        root.appendChild(eLugar);
                                        Element eValor = doc.createElement("valor");
                                        eValor.setTextContent(rs.getString(2));
                                        root.appendChild(eValor);

                                        sendPort(doc);
                                    }
                                }
                            }
                            sleep(1000);
                        } catch (InterruptedException | SQLException | ParserConfigurationException  ex) {
                            Logger.getLogger(AdaptadorBD.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            };
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void iniciate() {
        if (hilo != null) {
            hilo.start();
        }
    }

    @Override
    public void halt() {
        if (conn != null) try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdaptadorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (hilo != null) {
            hilo.interrupt();
        }
    }

    @Override
    public Document sendApp(Message m) {
        throw new ExecutionException("Este puerto es solo de entrada");
    }

    @Override
    public Process.PORTS getCompatiblePortType() {
        return Process.PORTS.OUTPUT;
    }

}
