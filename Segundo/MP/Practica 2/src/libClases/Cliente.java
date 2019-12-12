package libClases;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente implements Cloneable, Proceso {
    
    private static int contador = 1;
    private static final Fecha FECHA_POR_DEFECTO = new Fecha(1, 1, 2018);
    
    private final String nif;       //dni del cliente (letra incluida) (NO puede cambiar)
    private final int codCliente;   //codigo único (y fijo) generado por la aplicación
    private String nombre;          //nombre completo del cliente (SI se puede modificar)
    private final Fecha fechaNac;   //fecha nacimiento del cliente (NO se puede cambiar)
    private final Fecha fechaAlta;  //fecha de alta del cliente (SI se puede modificar)
    
    public Cliente(String NIF, String nom, Fecha fNac, Fecha fAlta){
        this.nif = NIF;
        this.nombre = nom;
        this.fechaNac = new Fecha(fNac);
        this.fechaAlta = new Fecha(fAlta);
        this.codCliente = contador++;
    }
    public Cliente(String NIF, String nom, Fecha fNac){
        this.nif = NIF;
        this.nombre = nom;
        this.fechaNac = new Fecha(fNac);
        this.fechaAlta = new Fecha(FECHA_POR_DEFECTO);
        this.codCliente = contador++;
    }
    public Cliente(Cliente c){
        this.nif = c.nif;
        this.nombre = c.nombre;
        this.fechaNac = new Fecha(c.fechaNac);
        this.fechaAlta = new Fecha(c.fechaAlta);
        this.codCliente = contador++;
    }
    
    public String getNombre(){
        return nombre;
    }
    public String getNif(){
        return nif;
    }
    public Fecha getFechaAlta(){
        return new Fecha(fechaAlta);
    }
    public Fecha getFechaNac(){
        return new Fecha (fechaNac);
    }
    public int getCodCliente(){
        return codCliente;
    }
    
    public void setNombre(String nom){
        this.nombre = nom;
    }
    public void setFechaAlta(Fecha f){
        this.fechaAlta.setFecha(f.getDia(), f.getMes(), f.getAnio());
    }
    
    @Override
    public void ver(){
        System.out.println(this);
    }
    @Override
    public boolean equals(Object o){
        return o.getClass() == Cliente.class && nif.equals(((Cliente)o).nif);
    }
    
    @Override
    public String toString(){
        return nif+" "+fechaNac+": "+nombre+" ("+codCliente+" - "+fechaAlta+")";
    }
    @Override
    public Cliente clone(){
        return new Cliente(nif, nombre, fechaNac, fechaAlta);
    }
    
    public static Fecha getFechaPorDefecto(){
        return new Fecha(FECHA_POR_DEFECTO);
    }
    public static void setFechaPorDefecto(Fecha f){
        FECHA_POR_DEFECTO.setFecha(f.getDia(), f.getMes(), f.getAnio());
    }
    
}
