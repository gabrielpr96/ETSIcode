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
        this.fechaNac = (Fecha)fNac.clone();
        this.fechaAlta = (Fecha)fAlta.clone();
        this.codCliente = contador++;
    }
    public Cliente(String NIF, String nom, Fecha fNac){
        this(NIF, nom, fNac, FECHA_POR_DEFECTO);
    }
    public Cliente(Cliente c){
        this(c.nif, c.nombre, c.fechaNac, c.fechaAlta);
    }
    
    public final String getNombre(){
        return nombre;
    }
    public final String getNif(){
        return nif;
    }
    public final Fecha getFechaAlta(){
        return (Fecha)fechaAlta.clone();
    }
    public final Fecha getFechaNac(){
        return (Fecha)fechaNac.clone();
    }
    public final int getCodCliente(){
        return codCliente;
    }
    
    public final void setNombre(String nom){
        this.nombre = nom;
    }
    public final void setFechaAlta(Fecha f){
        this.fechaAlta.setFecha(f.getDia(), f.getMes(), f.getAnio());
    }
    
    //Deberia ser abstracto pero hay el main de prueba daria fallo.
    public float factura(){
        throw new UnsupportedOperationException("No se puede faacturar un cliente sin tipo.");
    }
    
    @Override
    public void ver(){
        System.out.println(this);
    }
    @Override
    public boolean equals(Object o){
        return o instanceof Cliente && nif.equals(((Cliente)o).nif);
    }
    
    @Override
    public String toString(){
        return nif+" "+fechaNac+": "+nombre+" ("+codCliente+" - "+fechaAlta+")";
    }
    @Override
    public Object clone(){
        return new Cliente(this);
    }
    
    public final static Fecha getFechaPorDefecto(){
        return (Fecha)FECHA_POR_DEFECTO.clone();
    }
    public final static void setFechaPorDefecto(Fecha f){
        FECHA_POR_DEFECTO.setFecha(f.getDia(), f.getMes(), f.getAnio());
    }
    
}
