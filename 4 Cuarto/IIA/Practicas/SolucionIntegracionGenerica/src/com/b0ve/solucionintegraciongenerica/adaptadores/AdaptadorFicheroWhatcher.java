package com.b0ve.solucionintegraciongenerica.adaptadores;

import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdaptadorFicheroWhatcher extends Adaptador {

    private final String destdir;
    private final Thread watcher;

    public AdaptadorFicheroWhatcher(String watchDir, String destdir) {
        this.destdir = destdir;

        if (watchDir != null) {
            File folder = new File(watchDir);
            watcher = new Thread() {
                @Override
                public void run() {
                    try {
                        while (!isInterrupted()) {
                            for (final File fileEntry : folder.listFiles()) {
                                if (fileEntry.isFile()) {
                                    enviarPuerto(new Mensaje(new String(Files.readAllBytes(fileEntry.toPath()), StandardCharsets.UTF_8)));
                                    fileEntry.delete();
                                }
                            }
                            sleep(1000);
                        }
                    } catch (InterruptedException | IOException ex) {
                        Logger.getLogger(AdaptadorFicheroWhatcher.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
        } else {
            watcher = null;
        }
    }

    @Override
    public void enviarApp(Mensaje m) {
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter(destdir + "/" + m.getID() + ".xml");
            myWriter.write(m.getBody());
            myWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(AdaptadorFicheroWhatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void detener() {
        if (watcher != null) {
            watcher.interrupt();
        }
    }

    public void iniciar() {
        if (watcher != null) {
            watcher.start();
        }
    }

}
