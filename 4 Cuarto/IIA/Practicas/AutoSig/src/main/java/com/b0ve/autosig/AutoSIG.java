package com.b0ve.autosig;

import com.b0ve.autosig.gui.MainWindow;
import java.nio.file.Paths;

public class AutoSIG {

    //TODO: Probar cuando MySQL esta apagado, controlar las excepciones.
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
        
        mainWindow.load(Paths.get("C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\Cafe.xml"));
        //mainWindow.load(Paths.get("C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\WP-MC.xml"));
        //mainWindow.load(Paths.get("C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\RedesSociales.xml"));
        //mainWindow.load(Paths.get("C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\RedesSocialesInterrups.xml"));
        //mainWindow.load(Paths.get("C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\PS-CSV.xml"));
        //mainWindow.load(Paths.get("C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\PS-CSVInterrupts.xml"));
    }
}
