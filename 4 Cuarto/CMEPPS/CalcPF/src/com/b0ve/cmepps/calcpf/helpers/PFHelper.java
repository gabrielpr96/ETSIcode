package com.b0ve.cmepps.calcpf.helpers;

import com.b0ve.cmepps.calcpf.modelo.Estimacion;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import javax.swing.JFrame;

public class PFHelper {

    public static void openFrame(JFrame frame) {
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void saveEstimacion(Estimacion estimacion, File file) throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        try (ObjectOutputStream os = new ObjectOutputStream(bs)) {
            os.writeObject(estimacion);
        }
        byte[] bytes = bs.toByteArray();
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(bytes);
        }
    }

    public static Estimacion loadEstimacion(File file) throws IOException, ClassNotFoundException {
        byte[] bytes = Files.readAllBytes(file.toPath());
        ByteArrayInputStream bs = new ByteArrayInputStream(bytes); // bytes es el byte[]
        Estimacion estimacion;
        try (ObjectInputStream is = new ObjectInputStream(bs)) {
            estimacion = (Estimacion) is.readObject();
        }
        return estimacion;
    }
}
