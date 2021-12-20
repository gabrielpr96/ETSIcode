package com.b0ve.daw.proyecto.service.helpers;

import com.b0ve.daw.proyecto.helpers.InstallationConstants;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class B0vEMailingApiServicio {

    private static final String ENDPOINT = "https://mailer.b0ve.com/api.php";
    private static final String API_KEY = InstallationConstants.B0VE_MAILING_API_KEY;

    private final static B0vEMailingApiServicio instance = new B0vEMailingApiServicio();

    public static B0vEMailingApiServicio instance() {
        return instance;
    }

    public boolean sendMail(String email, String subject, String body) {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                @Override
                public boolean verify(String string, SSLSession ssls) {
                    return true;
                }
            };
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

            URL url = new URL(ENDPOINT);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString((API_KEY + ":").getBytes()));

            con.setDoOutput(true);
            con.setConnectTimeout(60000);
            con.setReadTimeout(60000);

            JsonObject reqObj = Json.createObjectBuilder()
                    .add("action", "sendMail")
                    .add("email", email)
                    .add("subject", subject)
                    .add("body", body)
                    .build();
            String bodyJson = reqObj.toString();

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = bodyJson.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            JsonObject resObj;
            try (JsonReader reader = Json.createReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                resObj = reader.readObject();
            }

            try {
                return resObj.getString("status").equals("success") && resObj.getBoolean("msg");
            } catch (Exception e) {
                return false;
            }
        } catch (MalformedURLException ex) {
            throw new IllegalStateException("Endpoint incorrecto: " + ex.getMessage());
        } catch (IOException | NoSuchAlgorithmException | KeyManagementException ex) {
            throw new IllegalStateException("No se ha podido comunicar con la API: " + ex.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        B0vEMailingApiServicio servicio = B0vEMailingApiServicio.instance();
        System.out.println(servicio.sendMail("borjainlive@gmail.com", "test", "Test test"));
    }
}
