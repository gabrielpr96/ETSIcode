package practica;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static practica.Algoritmos.*;

public class Practica1 {
    private static final String DATSET_DIR = "C:\\PROJECTOS\\ETSIcode\\Tercero\\AMC\\Practica 1\\datos\\";
    
    private static Interfaz interfaz;

    public static void main(String[] args) {
        //unitTest();
        //profile();
        //resolverDataset();
        interfaz = new Interfaz();
        JFrame ventana = new JFrame();
        ventana.add(interfaz);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        interfaz.setSize(1024, 720);
        interfaz.setVisible(true);
        ventana.pack();
        ventana.setTitle("AMC Practica 1 Miguel Sanchez y Borja Lopez");
        ventana.setVisible(true);
        while(true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Practica1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private static void unitTest(){
        System.out.println("Probando la clase Punto:");
        Punto p1 = new Punto(1, 2), p2 = new Punto(-3, 4), p3 = new Punto(-3, 0), p4 = new Punto(-4, 6), p5 = new Punto(2, 3), p6 = new Punto(4, 3);
        System.out.println(p1+" <---> "+p2+" = "+p1.distancia(p2)+" <---- 4.472135955");
        System.out.println(p3+" <---> "+p4+" = "+p3.distancia(p4)+" <---- 6.0827625303");
        System.out.println(p5+" <---> "+p6+" = "+p5.distancia(p6)+" <---- 2");
        
        System.out.println("\nProbando la clase Linea");
        Linea l1 = new Linea(p1, p2), l2 = new Linea(p3, p4), l3 = new Linea(p5, p6);
        System.out.println(l1+" longitud: "+l1.longitud()+" <---- longitud: 4.472135955");
        System.out.println(l2+" longitud: "+l2.longitud()+" <---- longitud: 6.0827625303");
        System.out.println(l3+" longitud: "+l3.longitud()+" <---- longitud: 2");
        
        System.out.println("\nProbando la clase Triangulo");
        Triangulo t1 = new Triangulo(p1, p2, p3), t2 = new Triangulo(p4, p5, p6);
        System.out.println(t1+" perimetro: "+t1.perimetro()+" area: "+t1.area()+" <---- perimetro: 12.94427191 area: 8");
        System.out.println(t2+" perimetro: "+t2.perimetro()+" area: "+t2.area()+" <---- perimetro: 17.25220767 area: 3");
        
        System.out.println("\nPrueba de la busqueda:");
        Punto[] puntos = new Punto[6];
        puntos[0] = new Punto(1, 2);
        puntos[1] = new Punto(2, 3);
        puntos[2] = new Punto(4, 5);
        puntos[3] = new Punto(6, 7);
        puntos[4] = new Punto(1, 4);
        puntos[5] = new Punto(4, 7);
        try {
            System.out.println("El triangulo de menor perimetro y mayor area por Exaustivo es: "+exaustivo(puntos)+" <---- [(1.0, 2.0), (2.0, 3.0), (1.0, 4.0)]");
        } catch (Exception ex) {
            System.out.println("No se ha podido obtener el trangulo de menor perimetro y mayor area por Exaustivo");
            ex.printStackTrace();
        }
        try {
            System.out.println("El triangulo de menor perimetro y mayor area por DyV es      : "+DyV(puntos)+" <---- [(1.0, 2.0), (2.0, 3.0), (1.0, 4.0)]");
        } catch (Exception ex) {
            System.out.println("No se ha podido obtener el trangulo de menor perimetro y mayor area por DyV");
            ex.printStackTrace();
        }
        try {
            System.out.println("La linea mas corta por Exaustivo: "+exaustivoLinea(puntos)+" <---- [(1.0, 2.0), (2.0, 3.0)]");
        } catch (Exception ex) {
            System.out.println("No se ha podido obtener la linea mas corta por DyV");
            ex.printStackTrace();
        }
        try {
            System.out.println("La linea mas corta por DyV      : "+DyVlinea(puntos)+" <---- [(1.0, 2.0), (2.0, 3.0)]");
        } catch (Exception ex) {
            System.out.println("No se ha podido obtener la linea mas corta por DyV");
            ex.printStackTrace();
        }
        
        System.out.println("\nPrueba aleatorioa:");
        puntos = randomMap(100, -100, 100, -100, 100);
        
        System.out.println("\nOrdenado por QuickSort:");
        ordenaQuick(puntos, true);
        for(int i = 0; i < 100; i++)
            System.out.println(puntos[i].toString());
        
        puntos = randomMap(100, -100, 100, -100, 100);
        
        System.out.println("\nOrdenado por HeapSort:");
        ordenaHeap(puntos, false);
        for(int i = 0; i < 100; i++)
            System.out.println(puntos[i].toString());
        
        try {
            System.out.println("\nDatos aleatorios, metodo exaustivo: "+exaustivo(puntos));
        } catch (Exception ex) {
            System.out.println("\nNo se ha podido aplicar el metodo exaustivo con los datos aleatorios");
            ex.printStackTrace();
        }
        try {
            System.out.println("Datos aleatorios, metodo DyV      : "+DyV(puntos));
        } catch (Exception ex) {
            System.out.println("\nNo se ha podido aplicar el metodo DyV con los datos aleatorios");
            ex.printStackTrace();
        }
        try {
            System.out.println("La linea mas corta por Exaustivo: "+exaustivoLinea(puntos));
        } catch (Exception ex) {
            System.out.println("No se ha podido obtener la linea mas corta por DyV");
            ex.printStackTrace();
        }
        try {
            System.out.println("La linea mas corta por DyV      : "+DyVlinea(puntos));
        } catch (Exception ex) {
            System.out.println("No se ha podido obtener la linea mas corta por DyV");
            ex.printStackTrace();
        }
        
        System.out.println("\nPrueba de parseo");
        Punto[] puntos1 = TSPlib.parse(DATSET_DIR+"berlin52.tsp");
        for(Punto punto: puntos1)
            System.out.println(punto.toString());
        
        puntos = new Punto[5];
        puntos[0] = new Punto(0, 0);
        puntos[1] = new Punto(0, 1);
        puntos[2] = new Punto(0, 2);
        puntos[3] = new Punto(1, 1);
        puntos[4] = new Punto(2, 2);
        System.out.println("\nPrueba de Kruskal");
        Arista[] aristasK = null;
        try {
            aristasK = kruskal(generarAristas(puntos), puntos);
            System.out.println("Coste: "+costeCamino(aristasK));
            for(Arista arista : aristasK)
                System.out.println(arista);
        } catch (Exception ex) {
            Logger.getLogger(Practica1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("\nPrueba de Prim");
        Arista[] aristasP = null;
        try {
            aristasP = prim(generarAristas(puntos), puntos);
            System.out.println("Coste: "+costeCamino(aristasP));
            for(Arista arista : aristasP)
                System.out.println(arista);
        } catch (Exception ex) {
            Logger.getLogger(Practica1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(!(aristasK == null || aristasP == null))
            System.out.println(costeCamino(aristasP) == costeCamino(aristasK)?"\nPrim y Kruskal devuelven lo mismo":"\nPrim y Kruskal no coinciden");
    }
    private static void profile(){
        //TODO: Hacer pruebas desde n=10 hasta todo lo que de el exaustivo y sacar los datos en csv
        //Y asegurarse de que los datos coinciden usando el metodo equals
        try {
            FileWriter ficheroExaustivo = new FileWriter(DATSET_DIR+"exaustivo.csv");
            FileWriter ficheroDyV = new FileWriter(DATSET_DIR+"DyV.csv");
            ficheroExaustivo.write("Taya,Tiempo(ns)\n");
            ficheroDyV.write("Taya,Tiempo(ns)\n");
            
            Triangulo tExaustivo, tDyV;
            Punto[] puntos;
            long comienzo, accExaustivo, accDyV;
            int repeticiones = 10;
            for(int taya = 10; taya <= 100; taya += 10){
                System.out.println(taya);
                accExaustivo = 0;
                accDyV = 0;
                for(int repeticion = 0; repeticion < repeticiones; repeticion++){
                    puntos = randomMap(taya, -100, 100, -100, 100);

                    comienzo = System.nanoTime();
                    tExaustivo = exaustivo(puntos);
                    accExaustivo += System.nanoTime()-comienzo;

                    comienzo = System.nanoTime();
                    tDyV = DyV(puntos);
                    accDyV += System.nanoTime()-comienzo;
                    
                    if(!tExaustivo.equals(tDyV)){
                        System.err.println("ERROR CRITICO: Exaustivo y DyV no han devuelto el mismo triangulo");
                        System.out.println("Exaustivo: "+tExaustivo);
                        System.out.println("DyV      : "+tDyV);
                        break;
                    }
                }
                ficheroExaustivo.write(taya+","+(accExaustivo/repeticiones)+"\n");
                ficheroDyV.write(taya+","+(accDyV/repeticiones)+"\n");
            }
            ficheroExaustivo.close();
            ficheroDyV.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        try {
            FileWriter ficheroPrim = new FileWriter(DATSET_DIR+"prim.csv");
            FileWriter ficheroKruskal = new FileWriter(DATSET_DIR+"kruskal.csv");
            ficheroPrim.write("Taya,Tiempo(ns)\n");
            ficheroKruskal.write("Taya,Tiempo(ns)\n");
            
            Punto[] puntos;
            double[][] aristas;
            Arista[] rPrim, rKruskal;
            long comienzo, accPrim, accKruskal;
            int repeticiones = 10;
            for(int taya = 10; taya <= 1000; taya += 10){
                System.out.println(taya);
                accPrim = 0;
                accKruskal = 0;
                for(int repeticion = 0; repeticion < repeticiones; repeticion++){
                    puntos = randomMap(taya, -100, 100, -100, 100);
                    aristas = generarMatrizAdyacencia(puntos);

                    comienzo = System.nanoTime();
                    rPrim = prim(aristas, puntos);
                    accPrim += System.nanoTime()-comienzo;

                    comienzo = System.nanoTime();
                    rKruskal = kruskal(aristas, puntos);
                    accKruskal += System.nanoTime()-comienzo;
                    
                    if(Math.abs(costeCamino(rPrim)-costeCamino(rKruskal)) > Punto.MINIMO_COMPARACION){
                        System.err.println("ERROR CRITICO: Prim y Kruskal no han devuelto un grafo con la misma longitud");
                        System.err.println("Prim: "+costeCamino(rPrim));
                        for (Arista arista : rPrim) {
                            System.err.println(arista);
                        }
                        System.err.println("Kruskal: "+costeCamino(rKruskal));
                        for (Arista arista : rKruskal) {
                            System.err.println(arista);
                        }
                        break;
                    }
                }
                ficheroPrim.write(taya+","+(accPrim/repeticiones)+"\n");
                ficheroKruskal.write(taya+","+(accKruskal/repeticiones)+"\n");
            }
            ficheroPrim.close();
            ficheroKruskal.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private static void resolverDataset(){
        Punto[] puntos;
        try {
            puntos = TSPlib.parse(DATSET_DIR+"berlin52.tsp");
            System.out.println("berlin52 Exaustivo: "+exaustivo(puntos));
            System.out.println("berlin52 DyV      : "+DyV(puntos));
            puntos = TSPlib.parse(DATSET_DIR+"ch130.tsp");
            System.out.println("ch130 Exaustivo: "+exaustivo(puntos));
            System.out.println("ch130 DyV      : "+DyV(puntos));
            puntos = TSPlib.parse(DATSET_DIR+"ch150.tsp");
            System.out.println("ch150 Exaustivo: "+exaustivo(puntos));
            System.out.println("ch150 DyV      : "+DyV(puntos));
            puntos = TSPlib.parse(DATSET_DIR+"d493.tsp");
            System.out.println("d493 Exaustivo: "+exaustivo(puntos));
            System.out.println("d493 DyV      : "+DyV(puntos));
            puntos = TSPlib.parse(DATSET_DIR+"d657.tsp");
            System.out.println("d657 Exaustivo: "+exaustivo(puntos));
            System.out.println("d657 DyV      : "+DyV(puntos));
            
            puntos = TSPlib.parse(DATSET_DIR+"berlin52.tsp");
            System.out.println("berlin52 Kruskal: "+costeCamino(kruskal(generarAristas(puntos), puntos)));
            System.out.println("berlin52 Prim   : "+costeCamino(prim(generarAristas(puntos), puntos)));
            puntos = TSPlib.parse(DATSET_DIR+"ch130.tsp");
            System.out.println("ch130 Kruskal: "+costeCamino(kruskal(generarAristas(puntos), puntos)));
            System.out.println("ch130 Prim   : "+costeCamino(prim(generarAristas(puntos), puntos)));
            puntos = TSPlib.parse(DATSET_DIR+"ch150.tsp");
            System.out.println("ch150 Kruskal: "+costeCamino(kruskal(generarAristas(puntos), puntos)));
            System.out.println("ch150 Prim   : "+costeCamino(prim(generarAristas(puntos), puntos)));
            puntos = TSPlib.parse(DATSET_DIR+"d493.tsp");
            System.out.println("d493 Kruskal: "+costeCamino(kruskal(generarAristas(puntos), puntos)));
            System.out.println("d493 Prim   : "+costeCamino(prim(generarAristas(puntos), puntos)));
            puntos = TSPlib.parse(DATSET_DIR+"d657.tsp");
            System.out.println("d657 Kruskal: "+costeCamino(kruskal(generarAristas(puntos), puntos)));
            System.out.println("d657 Prim   : "+costeCamino(prim(generarAristas(puntos), puntos)));
        } catch (Exception ex) {
            System.out.println("Error al calcular solucion");
            ex.printStackTrace();
        }
    }
    
}
