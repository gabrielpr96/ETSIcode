package ejercicio3;

import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.tareas.transformers.Splitter;
import com.b0ve.solucionintegraciongenerica.utils.excepciones.ExecutionException;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SplitterParticionado extends Tarea {

    private final String xpathDivisor, xpathParticion;
    private int contador;

    public SplitterParticionado(String xpathDivisor, String xpathParticion) {
        super(1, 1);
        contador = 0;
        this.xpathDivisor = xpathDivisor;
        this.xpathParticion = xpathParticion;
    }

    @Override
    public final void procesar() {
        Buffer salida = salidas.get(0);
        Buffer entrada = entradas.get(0);
        while (!entrada.empty()) {
            Mensaje mensaje = entrada.retrive();
            if (mensaje.getSequenceSize() != 0) {
                throw new ExecutionException("No se puede fragmentar un fragmento de mensaje");
            }
            Map<String, List<String>> parts = split(mensaje);
            for (Map.Entry<String, List<String>> entry : parts.entrySet()) {
                List<String> mensajes = entry.getValue();
                for (String doc: mensajes) {
                    Mensaje parte = new Mensaje(doc, contador, mensajes.size());
                    salida.push(parte);
                }
                contador++;
            }
        }
    }

    protected Map<String, List<String>> split(Mensaje mensaje) {
        Map<String, List<String>> divisiones = new HashMap<>();
        try {
            NodeList lista = mensaje.evaluateXPath(xpathDivisor);
            for (int i = 0; i < lista.getLength(); i++) {
                Document doc = Mensaje.node2document(lista.item(i));
                String fragmento = Mensaje.evaluateXPath(doc, xpathParticion).item(0).getTextContent();
                List<String> particion = divisiones.get(fragmento);
                if(particion == null){
                    particion = new ArrayList<>();
                    divisiones.put(fragmento, particion);
                }
                particion.add(Mensaje.serialiceXML(doc));
            }
        } catch (TransformerException | ParserConfigurationException | XPathExpressionException | SAXException | IOException ex) {
            Logger.getLogger(SplitterParticionado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return divisiones;
    }

}
