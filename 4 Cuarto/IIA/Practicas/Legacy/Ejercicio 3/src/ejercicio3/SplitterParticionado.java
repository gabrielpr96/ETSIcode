package ejercicio3;

import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.tasks.transformers.Splitter;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.ExecutionException;
import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.FragmentInfo;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.ConfigurationException;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.XPathEvaluationException;
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

public class SplitterParticionado extends Task {

    private final String xpathDivisor, xpathParticion;
    private int contador;

    public SplitterParticionado(String xpathDivisor, String xpathParticion) {
        super(1, 1);
        contador = 0;
        this.xpathDivisor = xpathDivisor;
        this.xpathParticion = xpathParticion;
    }

    @Override
    public final void process() throws SIGException {
        Buffer salida = output(0);
        Buffer entrada = input(0);
        while (!entrada.empty()) {
            Message mensaje = entrada.retrive();
            Map<String, List<String>> parts = split(mensaje);
            for (Map.Entry<String, List<String>> entry : parts.entrySet()) {
                List<String> mensajes = entry.getValue();
                for (String doc : mensajes) {
                    Message parte;
                    parte = new Message(doc);
                    parte.addFragmentInfo(new FragmentInfo(contador, mensajes.size()));
                    salida.push(parte);
                }
                contador++;
            }
        }
    }

    protected Map<String, List<String>> split(Message mensaje) throws SIGException {
        Map<String, List<String>> divisiones = new HashMap<>();
        NodeList lista = mensaje.evaluateXPath(xpathDivisor);
        for (int i = 0; i < lista.getLength(); i++) {
            Document doc = Message.node2document(lista.item(i));
            String fragmento = Message.evaluateXPath(doc, xpathParticion).item(0).getTextContent();
            List<String> particion = divisiones.get(fragmento);
            if (particion == null) {
                particion = new ArrayList<>();
                divisiones.put(fragmento, particion);
            }
            particion.add(Message.serialiceXML(doc));
        }
        return divisiones;
    }

}
