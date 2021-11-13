package com.b0ve.solucionintegraciongenerica.adapters;

import com.b0ve.solucionintegraciongenerica.utils.Process;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class AdapterDirWhatcher extends Adapter {

    private final Thread watcher;

    public AdapterDirWhatcher(String watchDir) {
        if (watchDir != null) {
            File folder = new File(watchDir);
            watcher = new Thread() {
                @Override
                public void run() {
                    try {
                        while (!isInterrupted()) {
                            for (final File fileEntry : folder.listFiles()) {
                                if (fileEntry.isFile()) {
                                    sendPort(new String(Files.readAllBytes(fileEntry.toPath()), StandardCharsets.UTF_8));
                                    fileEntry.delete();
                                }
                            }
                            sleep(1000);
                        }
                    } catch (InterruptedException | IOException | ParserConfigurationException | SAXException ex) {
                        Logger.getLogger(AdapterDirWhatcher.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
        } else {
            watcher = null;
        }
    }

    @Override
    public void halt() {
        if (watcher != null) {
            watcher.interrupt();
        }
    }

    @Override
    public void iniciate() {
        if (watcher != null) {
            watcher.start();
        }
    }

    @Override
    public Process.PORTS getCompatiblePortType() {
        return Process.PORTS.INPUT;
    }

    @Override
    public Document sendApp(Message m) {
        throw new UnsupportedOperationException("DirWhatcher is now input only. Please use DirOutputter to create files in a directory.");
    }

}
