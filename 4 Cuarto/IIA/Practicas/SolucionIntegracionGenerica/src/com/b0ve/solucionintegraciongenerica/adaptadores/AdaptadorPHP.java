package com.b0ve.solucionintegraciongenerica.adaptadores;

import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.json.JSONObject;
import org.json.XML;
import org.json.JSONException;
import org.xml.sax.SAXException;

public class AdaptadorPHP extends Adaptador {

    private final String gateway;

    public AdaptadorPHP(String gateway) {
        this.gateway = gateway;
    }

    @Override
    public void enviarApp(Mensaje m) {
        try {
            //System.out.println("Consulta PHP "+m.evaluateXPath("/call/nombre").item(0).getTextContent());
            JSONObject xmlJSONObj = XML.toJSONObject(m.getBodyString());
            String request = xmlJSONObj.toString(4);
            String response = request(request);
            JSONObject json = new JSONObject(response);
            String responseXML = XML.toString(json);
            Mensaje r = new Mensaje(responseXML);
            r.setCorrelationID(r.getCorrelationID());
            enviarPuerto(r);
        } catch (JSONException | ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(AdaptadorPHP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String request(String data) {
        try {
            URL url = new URL(gateway);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            con.setConnectTimeout(60000);
            con.setReadTimeout(60000);

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = data.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                con.disconnect();
                return response.toString();
            }
        } catch (MalformedURLException | ProtocolException | UnknownHostException ex) {
        } catch (IOException ex) {
        }
        return null;
    }

    @Override
    public void detener() {
    }

}
