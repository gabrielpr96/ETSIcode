package ejercicio3;

import com.b0ve.solucionintegraciongenerica.adaptadores.Adaptador;
import com.b0ve.solucionintegraciongenerica.utils.excepciones.ExecutionException;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
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

public class AdaptadorCRM3Entrada extends Adaptador {

    private Connection conn;
    private Thread hilo;
    private final List<String> conocidos;

    public AdaptadorCRM3Entrada() {
        conocidos = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejercicio3", "root", "");
            hilo = new Thread() {
                @Override
                public void run() {
                    while (!isInterrupted()) {
                        try {
                            synchronized (AdaptadorCRM3Entrada.this) {
                                List<String> nuevos = new ArrayList<>();
                                List<String> eliminados = new ArrayList<>(conocidos);
                                Statement stmt = conn.createStatement();
                                ResultSet rs = stmt.executeQuery("SELECT `DNI` FROM `clientes`");
                                while (rs.next()) {
                                    String dni = rs.getString(1);
                                    if (!conocidos.contains(dni)) {
                                        nuevos.add(dni);
                                        conocidos.add(dni);
                                    } else {
                                        eliminados.remove(dni);
                                    }
                                }
                                conocidos.removeAll(eliminados);
                                rs.close();
                                stmt.close();
                                if (!nuevos.isEmpty() || !eliminados.isEmpty()) {
                                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                                    Document doc = docBuilder.newDocument();
                                    Element root = doc.createElement("cambios");
                                    doc.appendChild(root);
                                    for (String dni : nuevos) {
                                        createCambio(doc, root, dni, "crear");
                                    }
                                    for (String dni : eliminados) {
                                        createCambio(doc, root, dni, "eliminar");
                                    }
                                    Mensaje mensaje = new Mensaje(Mensaje.serialiceXML(doc));
                                    enviarPuerto(mensaje);
                                }
                            }
                            sleep(1000);
                        } catch (InterruptedException | SQLException | ParserConfigurationException | TransformerException ex) {
                            Logger.getLogger(AdaptadorCRM3Entrada.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            };
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void createCambio(Document doc, Element root, String dni, String tipo) throws SQLException {
        Element eCambio = doc.createElement("cambio");
        root.appendChild(eCambio);

        Element eTipo = doc.createElement("tipo");
        eTipo.setTextContent(tipo);
        eCambio.appendChild(eTipo);

        Element eFuente = doc.createElement("fuente");
        eFuente.setTextContent("CRM3");
        eCambio.appendChild(eFuente);

        Element eDatos = doc.createElement("datos");
        eCambio.appendChild(eDatos);

        Element eDNI = doc.createElement("dni");
        eDNI.setTextContent(dni);
        eDatos.appendChild(eDNI);

        if(tipo == "crear"){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `Nombre` FROM `clientes` WHERE `DNI` = '" + dni + "'");
            rs.next();
            Element eNombre = doc.createElement("nombre");
            eNombre.setTextContent(rs.getString(1));
            eDatos.appendChild(eNombre);

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT `Direccion` FROM `direcciones` WHERE `Cliente` = '" + dni + "'");
            while (rs.next()) {
                Element eDireccion = doc.createElement("direccion");
                eDireccion.setTextContent(rs.getString(1));
                eDatos.appendChild(eDireccion);
            }
        }

    }

    @Override
    public void enviarApp(Mensaje m) {
        throw new ExecutionException("Este puerto es solo de entrada");
    }

    @Override
    public void iniciar() {
        if (hilo != null) {
            hilo.start();
        }
    }

    @Override
    public void detener() {
        if (conn != null) try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdaptadorCRM3Entrada.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (hilo != null) {
            hilo.interrupt();
        }
    }
    
    public synchronized void addConocido(String dni){
        conocidos.add(dni);
    }
    
    public synchronized void removeConocido(String dni){
        conocidos.remove(dni);
    }

}
