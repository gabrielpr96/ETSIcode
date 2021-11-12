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
    private final String ip, db, user, pass;
    private final int puerto;

    public AdaptadorMySQL(String ip, int puerto, String db, String user, String pass) {
        this.ip = ip;
        this.puerto = puerto;
        this.db = db;
        this.user = user;
        this.pass = pass;
    }

    @Override
    public void enviarApp(Mensaje m) {
        //TODO: <sql>SELECT * FROM `cafe`</sql>
        String sql = m.evaluateXPathString("/sql");
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            Document doc = JDBCUtil.toDocument(rs);
            rs.close();
            stmt.close();
            Mensaje r = new Mensaje(doc);
            r.setCorrelationID(m.getCorrelationID());
            enviarPuerto(r);
        } catch (SQLException | ParserConfigurationException  ex) {
            Logger.getLogger(AdaptadorMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void iniciar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + puerto + "/" + db + "", user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            conn = null;
        }
    }

    @Override
    public void detener() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdaptadorMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
