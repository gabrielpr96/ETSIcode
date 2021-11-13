package com.b0ve.solucionintegraciongenerica.adapters;

import com.b0ve.solucionintegraciongenerica.utils.Process;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;

public class AdapterDirOutputter extends Adapter {

    private final String destdir;

    public AdapterDirOutputter(String destdir) {
        this.destdir = destdir;
    }

    @Override
    public Document sendApp(Message m) {
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter(destdir + "/" + m.getID() + ".xml");
            myWriter.write(m.getBodyString());
            myWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(AdapterDirOutputter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Process.PORTS getCompatiblePortType() {
        return Process.PORTS.OUTPUT;
    }

}
