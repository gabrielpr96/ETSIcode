package com.b0ve.solucionintegraciongenerica.adaptadores;

import com.b0ve.solucionintegraciongenerica.utils.JDBCUtil;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;

public class AdaptadorMySQL extends Adaptador {

    private Connection conn;

    public AdaptadorMySQL(String ip, int puerto, String db, String user, String pass) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://"+ip+":"+puerto+"/"+db+"", user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            conn = null;
        }
    }

    @Override
    public void enviarApp(Mensaje m) {
        //TODO: <sql>SELECT * FROM `cafe`</sql>
        String sql = m.evaluateXPath("/sql").item(0).getTextContent();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            Document doc = JDBCUtil.toDocument(rs);
            rs.close();
            stmt.close();
            enviarPuerto(new Mensaje(doc, m.getCorrelationID()));
        } catch (SQLException | ParserConfigurationException | TransformerException ex) {
            Logger.getLogger(AdaptadorMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void detener() {
        if(conn != null) try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdaptadorMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
