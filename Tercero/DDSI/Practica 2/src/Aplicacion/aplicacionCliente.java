package Aplicacion;

import Persistencia.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class aplicacionCliente {

    static conexionOracle co;

    public static void main(String[] args) {

        try {
            co = new conexionOracle();
            System.out.println("Conexion realizada con éxito\n");
            System.out.println("Menú de Opciones");
            System.out.println("----------------");
            System.out.println("1. Expertos por nacionalidad");
            System.out.println("2. Insertar un experto");
            System.out.println("3. Insertar una colaboración");
            System.out.println();
            System.out.print("¿Qué quieres hacer?  ");
            Scanner sc = new Scanner(System.in);
            String opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    ejercicio1();
                    break;
                case "2":
                    ejercicio2();
                    break;
                case "3":
                    ejercicio3();
                    break;
            }

            co.desconexion();
        } catch (SQLException e) {
            System.out.println("Error al operar con la BD: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error en el programa principal: " + e.getMessage());
        }
    }

    public static void ejercicio1() {
        Scanner sc = new Scanner(System.in);
        manejaExperto me = new manejaExperto(co);
        System.out.print("Pais: ");
        String pais = sc.next();
        ArrayList<experto> expertos;
        try {
            expertos = me.listaExpertosPorPais(pais);
            System.out.println("CODIGO\tNOMBRE\tPAIS\tSEXO\tESPECIALIDAD");
            for(experto e : expertos)
                System.out.println(e.getCodExperto()+"\t"+e.getNombre()+"\t"+e.getPais()+"\t"+e.getSexo()+"\t"+e.getEspecialidad());
        } catch (SQLException ex) {
            System.out.println("No se ha podido obtener la lista de expertos\n"+ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void ejercicio2() {
        Scanner sc = new Scanner(System.in);
        manejaExperto me = new manejaExperto(co);
        experto e = new experto();
        System.out.print("Codigo: ");
        e.setCodExperto(sc.next());
        System.out.print("Nombre: ");
        e.setNombre(sc.next());
        System.out.print("Pais: ");
        e.setPais(sc.next());
        System.out.print("Sexo: ");
        e.setSexo(sc.next());
        System.out.print("Especialidad: ");
        e.setEspecialidad(sc.next());
        try {
            me.insertaExperto(e);
            System.out.println("Experto insertado correctamente");
        } catch (SQLException ex) {
            System.out.println("No se ha podido insertar el experto\n"+ex.getMessage());
        }
    }

    public static void ejercicio3() {
        Scanner sc = new Scanner(System.in);
        manejaColabora mc = new manejaColabora(co);
        colabora c = new colabora();
        System.out.print("Codigo experto: ");
        c.setCodExperto(sc.next());
        System.out.print("Codigo caso: ");
        c.setCodcaso(sc.next());
        System.out.print("Fecha: ");
        c.setFecha(sc.next());
        System.out.print("Descripcion: ");
        c.setDescripcionColaboracion(sc.next());
        try {
            mc.insertaColaboracion(c);
            System.out.println("Colaboracion insertada correctamente");
        } catch (SQLException ex) {
            System.out.println("No se ha podido insertar la colaboracion\n"+ex.getMessage());
        }
    }
}
