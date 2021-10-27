package com.b0ve.solucionintegraciongenerica.adaptadores;

import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import com.b0ve.solucionintegraciongenerica.puertos.Puerto;
import com.github.hindol.commons.file.DirectoryWatcher;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdaptadorFicheroWhatcher extends Adaptador {

    private final String destdir;
    private final DirectoryWatcher watcher;

    public AdaptadorFicheroWhatcher(String watchDir, String destdir) {
        this.destdir = destdir;

        watcher = new DirectoryWatcher.Builder()
                .addDirectories(watchDir)
                .setPreExistingAsCreated(true)
                .build((DirectoryWatcher.Event event, Path path) -> {
                    switch (event) {
                        case ENTRY_CREATE:
                            try {
                                enviarPuerto(new Mensaje(new String(Files.readAllBytes(path), StandardCharsets.UTF_8)));
                                (new File(path.toUri())).delete();
                            } catch (IOException ex) {
                            }
                            break;

                    }
                });
        try {
            watcher.start();
        } catch (Exception ex) {
            Logger.getLogger(AdaptadorFicheroWhatcher.class.getName()).log(Level.SEVERE, null, ex);
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
        watcher.stop();
    }

}
