/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author borja
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Punto[] tExaustivo, tDyV;
        ArrayList<Punto> puntos;
        int repeticiones = 1;
        for (int taya = 10; taya <= 100; taya += 10) {
            System.out.println(taya);
            for (int repeticion = 0; repeticion < repeticiones; repeticion++) {
                puntos = randomMap(taya, -100, 100, -100, 100);

                tExaustivo = exhaustivo(puntos);
                tDyV = AlgoritmoDyV(puntos);

                if (Math.abs(per(tDyV[0], tDyV[1], tDyV[2]) - per(tExaustivo[0], tExaustivo[1], tExaustivo[2])) > 0.00001) {
                    System.err.println("ERROR CRITICO: Exaustivo y DyV no han devuelto el mismo triangulo");
                    System.out.print("Exaustivo: ");
                    for (Punto punto : tExaustivo) {
                        System.out.print(punto + " ");
                    }
                    System.out.println("  "+(per(tExaustivo[0], tExaustivo[1], tExaustivo[2])));
                    System.out.print("DyV      : ");
                    for (Punto punto : tDyV) {
                        System.out.print(punto + " ");
                    }
                    System.out.println("  "+(per(tDyV[0], tDyV[1], tDyV[2])));
                    break;
                }
            }
        }
    }

    public static ArrayList<Punto> randomMap(int n, float resMinX, float resMaxX, float resMinY, float resMaxY) {
        Random r = new Random();
        r.setSeed(4);
        ArrayList<Punto> puntos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            puntos.add(new Punto(i, resMinX + (resMaxX - resMinX) * r.nextFloat(), resMinY + (resMaxY - resMinY) * r.nextFloat()));
        }
        return puntos;
    }

    public static double per(Punto a, Punto b, Punto c) { //Calcula el perimetro del triángulo
        double PaX = a.getX();
        double PbX = b.getX();
        double PcX = c.getX();
        double PaY = a.getY();
        double PbY = b.getY();
        double PcY = c.getY();

        //Distancia entre el punto A y B
        double D1 = Math.sqrt(Math.pow(PaX - PbX, 2) + Math.pow(PaY - PbY, 2));

        //Distancia entre el punto A y C
        double D2 = Math.sqrt(Math.pow(PaX - PcX, 2) + Math.pow(PaY - PcY, 2));

        //Distancia entre el punto B y C
        double D3 = Math.sqrt(Math.pow(PbX - PcX, 2) + Math.pow(PbY - PcY, 2));

        return (D1 + D2 + D3);
    }

    public static double distancia(Punto A, Punto B) {//ditancia
        return Math.sqrt(Math.pow(A.getX() - B.getX(), 2) + Math.pow(A.getY() - B.getY(), 2));
    }

    public static Punto[] exhaustivo(ArrayList<Punto> puntos) {

        return exhaustivo(puntos, 0, puntos.size() - 1);
    }

    public static double area(Punto p1, Punto p2, Punto p3) {

        double are;

        are = (p1.getX() * p2.getY() + p1.getY() * p3.getX() + p2.getX() * p3.getY() - p2.getY() * p3.getX() - p1.getY() * p2.getX() - p1.getX() * p3.getY()) / 2;

        return are;
    }

    public static Punto[] exhaustivo(ArrayList<Punto> p, int izq, int der) { //VERSION EFICIENTE
        double permin = per(p.get(0), p.get(1), p.get(2));
        double areamax = area(p.get(0), p.get(1), p.get(2));
        Punto[] solucion = new Punto[3];
        solucion[0] = p.get(0);
        solucion[1] = p.get(1);
        solucion[2] = p.get(2);

        for (int i = izq; i <= der; i++) { //Primer Punto
            for (int j = i + 1; j <= der; j++) { // Segundo Punto
                // if (permin / 2 >= distancia(p.get(i), p.get(j))) { //Si no se cumple es imposible que exista un triangulo menor
                for (int k = j + 1; k <= der; k++) { // Tercer Punto
                    double pertmp = per(p.get(i), p.get(j), p.get(k));
                    double areatmp = area(p.get(i), p.get(j), p.get(k));
                    if (pertmp < permin || (pertmp == permin && areamax < areatmp)) { // Si la distancia calculada es menor
                        
                        solucion[0] = p.get(i);
                        solucion[1] = p.get(j);
                        solucion[2] = p.get(k);
                        
                        permin = pertmp;
                        areamax = areatmp;
                    }
                }
                //}
            }
        }

        return solucion;
    }

    public static Punto[] AlgoritmoDyV(ArrayList<Punto> p) {
        Collections.sort(p);
        return DyV(p, 0, p.size() - 1);

    }

    public static Punto[] DyV(ArrayList<Punto> p, int izq, int der) {

        Punto[] SOL = new Punto[3];
        if (der - izq >= 6) {

            int mitad = (der + izq) / 2;
            double dizq, dder, dmit = Double.MAX_VALUE;
            double permin = per(p.get(0), p.get(1), p.get(2));
            double areamax = area(p.get(0), p.get(1), p.get(2));

            Punto[] temp1 = DyV(p, izq, mitad); //Lado izquierdo
            Punto[] temp2 = DyV(p, mitad + 1, der); // Lado derecho
            dizq = per(temp1[0], temp1[1], temp1[2]); //Distancia de los puntos de la izquierda
            dder = per(temp2[0], temp2[1], temp2[2]); //Distancia de los puntos de la derecha
            if (dizq < dder) {
                permin = dizq; //Si el minimo es el que corresponde a la izquierda
            } else {
                permin = dder; //Si el minimo corresponde al de la derecha
            }
            /* CASO CENTRO*/
            int i = mitad;

            /*CREAMOS LA ZONA DE BUSQUEDA DESDE I HASTA J SEGÚN DISTANCIA */
            while (i >= izq && p.get(i).getX() > p.get(mitad).getX() - permin / 2) {//si la coordenada esta a una distancia del eje x mayor que el perimetro menor es imposible que eltrisngulo que forme tenga un perimetro menor que ese.
                i--;
            }
            i++;
            int j = mitad + 1;

            while (j <= der && p.get(mitad + 1).getX() + permin / 2 > p.get(j).getX()) {
                j++;
            }
            j--;
            Punto[] temp3 = new Punto[3];

            /*HACEMOS BUSQUEDA EXHAUTIVA ESPECIAL, COJEMOS UNO DE LA IZQUIERDA Y LO COMPARAMOS CON TODOS
            LOS DEMAS, LUEGO COMPARAMOS UNO DE LA DERECHA CON TODOS CON LO DE LA IZQUIERDA
             */
            for (int a = i; a <= mitad; a++) {
                for (int b = a + 1; b <= mitad; b++) {
                    // if (permin / 2 >= distancia(p.get(a), p.get(b))) { //Si no se cumple es imposible que exista un triangulo menor
                    for (int c = j; c > mitad; c--) {
                        if (permin > per(p.get(a), p.get(b), p.get(c)) || (per(p.get(a), p.get(b), p.get(c))) == permin && areamax < area(p.get(a), p.get(b), p.get(c))) {
                            if(a == b || a == c || b == c)
                                   System.out.println("Son iguales");
                            permin = per(p.get(a), p.get(b), p.get(c));
                            dmit = permin;
                            temp3[0] = p.get(a);
                            temp3[1] = p.get(b);
                            temp3[2] = p.get(c);

                        }

                    }
                    //}

                }

            }

            for (int a = j; a > mitad; a--) {
                for (int b = a - 1; b > mitad; b--) {
                    //if (permin / 2 >= distancia(p.get(a), p.get(b))) { //Si no se cumple es imposible que exista un triangulo menor
                    for (int c = i; c <= mitad; c++) {
                        if (permin > per(p.get(a), p.get(b), p.get(c)) || (per(p.get(a), p.get(b), p.get(c))) == permin && areamax < area(p.get(a), p.get(b), p.get(c))) {
                            permin = per(p.get(a), p.get(b), p.get(c));
                            dmit = permin;
                            temp3[0] = p.get(a);
                            temp3[1] = p.get(b);
                            temp3[2] = p.get(c);

                        }

                    }
                    // }

                }

            }
            //NOS QUEDAMOS EN SOLUCION CON EL MENOR DE LAS 3 POSIBILIDADES
            if (dizq == permin) {
                SOL = temp1;
            } else if (dder == permin) {
                SOL = temp2;
            } else if (dmit == permin) {
                SOL = temp3;
            }

        } else if (der - izq < 6) { //CASO BASE, CUANDO HAY MENOS DE 6 PUNTOS, EJECUTAMOS DIRECTAMENTE LA BUSQUEDA N3
            SOL = exhaustivo(p, izq, der);
        }
        return SOL;

    }

}
