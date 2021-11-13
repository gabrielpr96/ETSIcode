package com.b0ve.solucionintegraciongenerica.adapters;

import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.Process.PORTS;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class AdapterMySQLmultyQuery extends Adapter {

    private Connection conn;
    private final String ip, db, user, pass;
    private final int puerto;

    public AdapterMySQLmultyQuery(String ip, int puerto, String db, String user, String pass) {
        this.ip = ip;
        this.puerto = puerto;
        this.db = db;
        this.user = user;
        this.pass = pass;
    }

    @Override
    public Document sendApp(Message m) {
        try {
            NodeList queries = m.evaluateXPath("/queries/sql");
            for (int i = 0; i < queries.getLength(); i++) {
                String sql = queries.item(i).getTextContent();
                Statement stmt = conn.createStatement();
                stmt.executeQuery(sql);
                stmt.close();
            }
        } catch (SQLException ex) {
            handleException(new SIGException("SQL Exception ", m, ex));
        } catch (SIGException ex) {
            handleException(ex);
        }
        return null;
    }

    @Override
    public void iniciate() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + puerto + "/" + db, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            conn = null;
        }
    }

    @Override
    public void halt() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdapterMySQLmultyQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public PORTS getCompatiblePortType() {
        return PORTS.REQUEST;
    }

}
