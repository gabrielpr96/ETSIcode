package practica.pkg1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.DATA_CONVERSION;

public class Practica1 {
    private static final String DATSET_DIR = "C:\\PROJECTOS\\ETSIcode\\Tercero\\AMC\\Practica 1\\datos\\";

    private static Triangulo exaustivo(Punto[] puntos){
        return exaustivo(puntos, 0, puntos.length-1);
    }
    private static Triangulo exaustivo(Punto[] puntos, int inicio, int fin){
        Triangulo mejor = new Triangulo(puntos[0], puntos[1], puntos[2]), tmp;
        for(int i = inicio; i <= fin; i++){
            for(int j = i+1; j <= fin; j++){
                for(int k = j+1; k <= fin; k++){
                    tmp = new Triangulo(puntos[i], puntos[j], puntos[k]);
                    if(tmp.perimetro() < mejor.perimetro() || (tmp.perimetro() == mejor.perimetro() && tmp.area() > mejor.area())){
                        mejor = tmp;
                    }
                }
            }
        }
        
        return mejor;            
    }
    
    private static Triangulo DyV(Punto[] puntos){
        ordenaQuick(puntos, true);
        Punto[] puntosY = new Punto[puntos.length];
        System.arraycopy(puntos, 0, puntosY, 0, puntos.length);
        ordenaQuick(puntosY, false);
        return DyV(puntos, puntosY, 0, puntos.length-1);
    }
    private static Triangulo DyV(Punto[] puntosX, Punto[] puntosY, int izq, int der){
        //Controlar que haya suficiente para la division, en caso contrario se hace por metodo exaustivo
        int n = der-izq+1;
        if(n <= 3)
            return exaustivo(puntosX, izq, der);
        //Dividir la nube en dos mitades
        int mid = (der+izq)/2;
        //Agreguar los puntos ya ordenados en Y en las mitades
        Punto[] puntosYizq = new Punto[mid-izq+1];
        Punto[] puntosYder = new Punto[n-(mid-izq+1)];
        int ii = 0, di = 0;
        for (int i = 0; i < puntosY.length; i++){
            if (puntosY[i].getX() <= puntosX[mid].getX() && ii <= mid)
               puntosYizq[ii++] = puntosY[i];
            else
               puntosYder[di++] = puntosY[i];
        }
        //Obtener el triangulo menor de cada mitad
        Triangulo minIzq = DyV(puntosX, puntosYizq, izq, mid), minDer = DyV(puntosX, puntosYder, mid+1, der);
        //Sacar la linea menor de las dos mitades
        Triangulo min = mejorTriangulo(minIzq, minDer)?minIzq:minDer;
        //Extraer la zona crossover ordenada por Y
        Punto[] crossover = new Punto[n];
        int nCrossover = 0;
        for (int i = 0; i < puntosY.length; i++)
            if (Math.abs(puntosY[i].getX() - puntosX[mid].getX()) < min.perimetro()/2)
                crossover[nCrossover++] = puntosY[i];
        //Busqueda optimizada en la zona crossover
        Triangulo tmp;
        for (int i = 0; i < n; ++i){
            for (int j = i+1; j < n && (puntosY[j].getY() - puntosY[i].getY()) < min.perimetro()/2; ++j){
                for (int k = j+1; k < n && (puntosY[k].getY() - puntosY[i].getY()) < min.perimetro()/2; ++k){
                    tmp = new Triangulo(puntosY[i], puntosY[j], puntosY[k]);
                    if(mejorTriangulo(tmp, min))
                        min = tmp;
                }
            }
        }
        return min;
    }
        
    private static Linea exaustivoLinea(Punto[] puntos){
        return exaustivoLinea(puntos, 0, puntos.length-1);
    }
    private static Linea exaustivoLinea(Punto[] puntos, int inicio, int fin){
        Linea mejor = new Linea(puntos[0], puntos[1]), tmp;
        for(int i = inicio; i <= fin; i++){
            for(int j = i+1; j <= fin; j++){
                tmp = new Linea(puntos[i], puntos[j]);
                if(tmp.longitud()< mejor.longitud()){
                    mejor = tmp;
                }
            }
        }
        
        return mejor;
    }
    
    private static Linea DyVlinea(Punto[] puntos){
        ordenaQuick(puntos, true);
        Punto[] puntosY = new Punto[puntos.length];
        System.arraycopy(puntos, 0, puntosY, 0, puntos.length);
        ordenaQuick(puntosY, false);
        return DyVlinea(puntos, puntosY, 0, puntos.length-1);
    }
    private static Linea DyVlinea(Punto[] puntosX, Punto[] puntosY, int izq, int der){
        //Controlar que haya suficiente para la division, en caso contrario se hace por metodo exaustivo
        int n = der-izq+1;
        if(n <= 3)
            return exaustivoLinea(puntosX, izq, der);
        //Dividir la nube en dos mitades
        int mid = (der+izq)/2;
        //Agreguar los puntos ya ordenados en Y en las mitades
        Punto[] puntosYizq = new Punto[mid-izq+1];
        Punto[] puntosYder = new Punto[n-(mid-izq+1)];
        int ii = 0, di = 0;
        for (int i = 0; i < puntosY.length; i++){
            if (puntosY[i].getX() <= puntosX[mid].getX() && ii <= mid)
               puntosYizq[ii++] = puntosY[i];
            else
               puntosYder[di++] = puntosY[i];
        }
        //Obtener la linea menor de cada mitad
        Linea minIzq = DyVlinea(puntosX, puntosYizq, izq, mid), minDer = DyVlinea(puntosX, puntosYder, mid+1, der);
        //Sacar la linea menor de las dos mitades
        Linea min = minIzq.longitud()<minDer.longitud()?minIzq:minDer;
        //Extraer la zona crossover ordenada por Y
        Punto[] crossover = new Punto[n];
        int nCrossover = 0;
        for (int i = 0; i < puntosY.length; i++)
            if (Math.abs(puntosY[i].getX() - puntosX[mid].getX()) < min.longitud())
                crossover[nCrossover++] = puntosY[i];
        //Busqueda optimizada en la zona crossover
        Linea tmp;
        for (int i = 0; i < n; ++i){
            for (int j = i+1; j < n && (puntosY[j].getY() - puntosY[i].getY()) < min.longitud(); ++j){
                tmp = new Linea(puntosY[i], puntosY[j]);
                if(tmp.longitud() < min.longitud())
                    min = tmp;
            }
        }
        return min;
    }
    
    private static void ordenaQuick(Punto[] puntos, boolean sortX){
        ordenaQuick(puntos, 0, puntos.length-1, sortX);
    }
    private static int ordenaQuickPart(Punto puntos[], int izq, int der, boolean sortX) { 
        Punto med = puntos[der]; 
        int i = (izq-1);
        for (int j=izq; j<der; j++){
            if ((sortX && puntos[j].getX() <= med.getX()) || (!sortX && puntos[j].getY() <= med.getY())){
                i++;
                ordenaSwap(puntos, i, j);
            }
        }
        ordenaSwap(puntos, i+1, der);
  
        return i+1;
    }
    private static void ordenaQuick(Punto[] puntos, int izq, int der, boolean sortX){
        if (izq < der){
            int med = ordenaQuickPart(puntos, izq, der, sortX);
            ordenaQuick(puntos, izq, med-1, sortX);
            ordenaQuick(puntos, med+1, der, sortX);
        }
    }
    private static void ordenaSwap(Punto[] puntos, int a, int b){
        Punto temp = puntos[a];
        puntos[a] = puntos[b];
        puntos[b] = temp;
    }
    
    private static void ordenaHeap(Punto[] puntos, boolean sortX){
        int n = puntos.length;
  
        for (int i = n / 2 - 1; i >= 0; i--)
            ordenaHeapHundir(puntos, i, n, sortX);
  
        for (int i=n-1; i>0; i--){ 
            ordenaSwap(puntos, 0, i);
            ordenaHeapHundir(puntos, 0, i, sortX);
        }
    }
    private static void ordenaHeapHundir(Punto[] puntos, int raiz, int n, boolean sortX){
        int mayor = raiz;
        int izq = 2 * raiz + 1;
        int der = 2 * raiz + 2;
  
        if (izq < n && ((sortX && puntos[izq].getX() > puntos[mayor].getX()) || (!sortX && puntos[izq].getY() > puntos[mayor].getY())))
            mayor = izq;
  
        if (der < n && ((sortX && puntos[der].getX() > puntos[mayor].getX()) || (!sortX && puntos[der].getY() > puntos[mayor].getY())))
            mayor = der;
        
        if (mayor != raiz){
            ordenaSwap(puntos, raiz, mayor);
            ordenaHeapHundir(puntos, mayor, n, sortX);
        }
    }
    
    //Verdadero si A es mejor triangulo que B
    private static boolean mejorTriangulo(Triangulo a, Triangulo b){
        return (a.perimetro()<b.perimetro() || (a.perimetro()==b.perimetro() && a.area()>b.area()));
    }
    
    public static void main(String[] args) {
        //unitTest();
        profile();
        //resolverDataset();
    }
    
    private static void unitTest(){
        System.out.println("Probando la clase Punto:");
        Punto p1 = new Punto(1, 2), p2 = new Punto(-3, 4), p3 = new Punto(-3, 0), p4 = new Punto(-4, 6), p5 = new Punto(2, 3), p6 = new Punto(4, 3);
        System.out.println(p1+" <---> "+p2+" = "+p1.distancia(p2)+" <---- 4.472135955");
        System.out.println(p3+" <---> "+p4+" = "+Punto.distancia(p3, p4)+" <---- 6.0827625303");
        System.out.println(p5+" <---> "+p6+" = "+p5.distancia(p6)+" <---- 2");
        
        System.out.println("\nProbando la clase Linea");
        Linea l1 = new Linea(p1, p2), l2 = new Linea(p3, p4), l3 = new Linea(p5, p6);
        System.out.println(l1+" longitud: "+l1.longitud()+" <---- longitud: 4.472135955");
        System.out.println(l2+" longitud: "+Linea.longitud(l2)+" <---- longitud: 6.0827625303");
        System.out.println(l3+" longitud: "+Linea.longitud(l3)+" <---- longitud: 2");
        
        System.out.println("\nProbando la clase Triangulo");
        Triangulo t1 = new Triangulo(p1, p2, p3), t2 = new Triangulo(p4, p5, p6);
        System.out.println(t1+" perimetro: "+t1.perimetro()+" area: "+t1.area()+" <---- perimetro: 12.94427191 area: 8");
        System.out.println(t2+" perimetro: "+Triangulo.perimetro(t2)+" area: "+Triangulo.area(t2)+" <---- perimetro: 17.25220767 area: 3");
        
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
        Random r = new Random();
        double rangeMin = -1000, rangeMax = 1000;
        puntos = new Punto[100];
        for(int i = 0; i < 100; i++)
            puntos[i] = new Punto(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), rangeMin + (rangeMax - rangeMin) * r.nextDouble());
        
        System.out.println("\nOrdenado por QuickSort:");
        ordenaQuick(puntos, true);
        for(int i = 0; i < 100; i++)
            System.out.println(puntos[i].toString());
        
        for(int i = 0; i < 100; i++)
            puntos[i] = new Punto(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), rangeMin + (rangeMax - rangeMin) * r.nextDouble());
        
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
        Punto[] puntos1 = TSPparser.parse(DATSET_DIR+"berlin52.tsp");
        for(Punto punto: puntos1)
            System.out.println(punto.toString());
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
            Random r = new Random();
            int repeticiones = 10;
            double rangeMin = -1000, rangeMax = 1000;
            for(int taya = 10; taya <= 1000; taya += 10){
                System.out.println(taya);
                accExaustivo = 0;
                accDyV = 0;
                for(int repeticion = 0; repeticion < repeticiones; repeticion++){
                    puntos = new Punto[taya];
                    for(int i = 0; i < taya; i++)
                        puntos[i] = new Punto(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), rangeMin + (rangeMax - rangeMin) * r.nextDouble());

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
    }
    
    private static void resolverDataset(){
        Punto[] puntos;
        try {
            puntos = TSPparser.parse(DATSET_DIR+"berlin52.tsp");
            System.out.println("berlin52 Exaustivo: "+exaustivo(puntos));
            System.out.println("berlin52 DyV      : "+DyV(puntos));
            puntos = TSPparser.parse(DATSET_DIR+"ch130.tsp");
            System.out.println("ch130 Exaustivo: "+exaustivo(puntos));
            System.out.println("ch130 DyV      : "+DyV(puntos));
            puntos = TSPparser.parse(DATSET_DIR+"ch150.tsp");
            System.out.println("ch150 Exaustivo: "+exaustivo(puntos));
            System.out.println("ch150 DyV      : "+DyV(puntos));
            puntos = TSPparser.parse(DATSET_DIR+"d493.tsp");
            System.out.println("d493 Exaustivo: "+exaustivo(puntos));
            System.out.println("d493 DyV      : "+DyV(puntos));
            puntos = TSPparser.parse(DATSET_DIR+"d657.tsp");
            System.out.println("d657 Exaustivo: "+exaustivo(puntos));
            System.out.println("d657 DyV      : "+DyV(puntos));
        } catch (Exception ex) {
            System.out.println("Error al calcular solucion");
            ex.printStackTrace();
        }
    }
    
}
