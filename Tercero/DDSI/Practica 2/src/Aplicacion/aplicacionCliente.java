package Aplicacion;

import Persistencia.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String pais = sc.nextLine();
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
        e.setCodExperto(sc.nextLine());
        System.out.print("Nombre: ");
        e.setNombre(sc.nextLine());
        System.out.print("Pais: ");
        e.setPais(sc.nextLine());
        System.out.print("Sexo: ");
        e.setSexo(sc.nextLine());
        System.out.print("Especialidad: ");
        e.setEspecialidad(sc.nextLine());
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
        manejaExperto me = new manejaExperto(co);
        manejaCaso ma = new manejaCaso(co);
        colabora c = new colabora();
        System.out.print("Codigo experto: ");
        c.setCodExperto(sc.nextLine());
        System.out.print("Codigo caso: ");
        c.setCodcaso(sc.nextLine());
        System.out.print("Fecha: ");
        c.setFecha(sc.nextLine());
        System.out.print("Descripcion: ");
        c.setDescripcionColaboracion(sc.nextLine());
        try {
            co.inicioTransaccion();
            if(!me.existeExperto(c.getCodExperto())){
                experto e = new experto();
                System.out.println("No existe ningun experto con el codigo: "+c.getCodExperto()+", introduzca sus datos.");
                e.setCodExperto(c.getCodExperto());
                System.out.print("Nombre: ");
                e.setNombre(sc.nextLine());
                System.out.print("Pais: ");
                e.setPais(sc.nextLine());
                System.out.print("Sexo: ");
                e.setSexo(sc.nextLine());
                System.out.print("Especialidad: ");
                e.setEspecialidad(sc.nextLine());
                me.insertaExperto(e);
            }
            if(!ma.existeCaso(c.getCodCaso())){
                caso a = new caso();
                System.out.println("No existe ningun caso con el codigo: "+c.getCodCaso()+", introduzca sus datos.");
                a.setCodCaso(c.getCodCaso());
                System.out.print("Nombre: ");
                a.setNombre(sc.nextLine());
                System.out.print("Fecha Inicio: ");
                a.setFechaInicio(sc.nextLine());
                System.out.print("Fecha Fin: ");
                a.setFechaFin(sc.nextLine());
                ma.insertaCaso(a);
            }
            mc.insertaColaboracion(c);
            System.out.println("Colaboracion insertada correctamente");
        } catch (SQLException ex) {
            try {
                co.finTransaccionRollback();
            } catch (SQLException ex1) {
                System.out.println("No se ha podido cancelar la transaccion\n"+ex1.getMessage());
            }
            System.out.println("No se ha podido insertar la colaboracion\n"+ex.getMessage());
        }
    }
}
