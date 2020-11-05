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
        try {
            ArrayList<experto> expertos = me.listaExpertosPorPais(pais);
            System.out.println("CODIGO\tNOMBRE\tPAIS\tSEXO\tESPECIALIDAD");
            for (experto e : expertos) {
                System.out.println(e.getCodExperto() + "\t" + e.getNombre() + "\t" + e.getPais() + "\t" + e.getSexo() + "\t" + e.getEspecialidad());
            }
        } catch (SQLException ex) {
            System.out.println("No se ha podido obtener la lista de expertos\n" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void ejercicio2() {
        manejaExperto me = new manejaExperto(co);
        try {
            me.insertaExperto(introducirExperto(null));
            System.out.println("Experto insertado correctamente");
        } catch (SQLException ex) {
            System.out.println("No se ha podido insertar el experto\n" + ex.getMessage());
        }
    }

    public static void ejercicio3() {
        manejaColabora mc = new manejaColabora(co);
        manejaExperto me = new manejaExperto(co);
        manejaCaso ma = new manejaCaso(co);
        colabora c = introducirColabora();
        try {
            co.inicioTransaccion();
            if (!me.existeExperto(c.getCodExperto())) {
                System.out.println("No existe ningun experto con el codigo: " + c.getCodExperto() + ", introduzca sus datos.");
                me.insertaExperto(introducirExperto(c.getCodExperto()));
            }
            if (!ma.existeCaso(c.getCodCaso())) {
                System.out.println("No existe ningun caso con el codigo: " + c.getCodCaso() + ", introduzca sus datos.");
                ma.insertaCaso(introducirCaso(c.getCodCaso()));
            }
            mc.insertaColaboracion(c);
            System.out.println("Colaboracion insertada correctamente");
        } catch (SQLException ex) {
            try {
                co.finTransaccionRollback();
            } catch (SQLException ex1) {
                System.out.println("No se ha podido cancelar la transaccion\n" + ex1.getMessage());
            }
            System.out.println("No se ha podido insertar la colaboracion\n" + ex.getMessage());
        }
    }

    public static experto introducirExperto(String codigo) {
        Scanner sc = new Scanner(System.in);
        experto e = new experto();
        if (codigo == null) {
            System.out.print("Codigo: ");
            e.setCodExperto(sc.nextLine());
        } else {
            e.setCodExperto(codigo);
        }
        System.out.print("Nombre: ");
        e.setNombre(sc.nextLine());
        System.out.print("Pais: ");
        e.setPais(sc.nextLine());
        System.out.print("Sexo: ");
        e.setSexo(sc.nextLine());
        System.out.print("Especialidad: ");
        e.setEspecialidad(sc.nextLine());
        return e;
    }
    public static caso introducirCaso(String codigo) {
        Scanner sc = new Scanner(System.in);
        caso a = new caso();
        if (codigo == null) {
            System.out.print("Codigo: ");
            a.setCodCaso(sc.nextLine());
        } else {
            a.setCodCaso(codigo);
        }
        System.out.print("Nombre: ");
        a.setNombre(sc.nextLine());
        System.out.print("Fecha Inicio: ");
        a.setFechaInicio(sc.nextLine());
        System.out.print("Fecha Fin: ");
        a.setFechaFin(sc.nextLine());
        return a;
    }
    public static colabora introducirColabora() {
        Scanner sc = new Scanner(System.in);
        colabora c = new colabora();
        System.out.print("Codigo experto: ");
        c.setCodExperto(sc.nextLine());
        System.out.print("Codigo caso: ");
        c.setCodcaso(sc.nextLine());
        System.out.print("Fecha: ");
        c.setFecha(sc.nextLine());
        System.out.print("Descripcion: ");
        c.setDescripcionColaboracion(sc.nextLine());
        return c;
    }
}
