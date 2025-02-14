package com.b0ve.solucionintegraciongenerica.adapters;

import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.Process.PORTS;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.UnknownHostException;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;

/**
 * Adapter that makes request to a Web API. Endpoint is specified in constructor, the body of the message is sent in json and response is received in json but transformed to xml before it is returned in a message.
 * @author borja
 */
public class AdapterWebAPI extends Adapter {

    private final String gateway;

    public AdapterWebAPI(String gateway) {
        this.gateway = gateway;
    }

    @Override
    public Document sendApp(Message m) {
        try {
            //System.out.println("Consulta PHP "+m.evaluateXPath("/call/nombre").item(0).getTextContent());
            JSONObject xmlJSONObj = XML.toJSONObject(m.getBodyString());
            String request = xmlJSONObj.toString(4);
            String response = request(request);
            JSONObject json = new JSONObject(response);
            String responseXML = XML.toString(json);
            return Message.parseXML(responseXML);
        } catch (SIGException ex) {
            handleException(ex);
        }
        return null;
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
    public void halt() {
    }

    @Override
    public PORTS getCompatiblePortType() {
        return PORTS.REQUEST;
    }

}
