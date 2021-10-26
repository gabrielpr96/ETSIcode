package com.b0ve.solucionintegraciongenerica.adaptadores;

import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import com.b0ve.solucionintegraciongenerica.puertos.Puerto;
import java.util.Scanner;

public class AdaptadorTeclado extends Adaptador{
    private final Thread hilo;
    
    public AdaptadorTeclado() {
        
        hilo = new Thread(){
            @Override
            public void run() {
                Scanner es = new Scanner(System.in);
                String texto = null;
                do{
                    texto = es.nextLine();
                    enviarPuerto(new Mensaje(texto));
                }while(!texto.equals("salir") && !isInterrupted());
            }
        };
        hilo.start();
    }
    
    

    @Override
    public void enviarApp(Mensaje m) {
        System.out.println(m.getBody());
    }

    @Override
    public void detener() {
        hilo.interrupt();
    }
    
}
