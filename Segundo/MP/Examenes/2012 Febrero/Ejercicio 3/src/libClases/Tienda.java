package libClases;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tienda {
    
    private final String nombre, nif;
    private Almacen almacen;
    
    ArrayList<Cuenta> cuentas;
    
    public Tienda(String nombre, String nif, int capacidadAlmacen){
        this.nombre = nombre;
        this.nif = nif;
        almacen = new Almacen(capacidadAlmacen);
        cuentas = new ArrayList<>();
    }
    
    public void addProducto(Producto p){
        almacen.addProducto(p);
    }
    
    public void addCuenta(int mesa, int nItems){
        cuentas.add(new Cuenta(mesa, nItems));
    }
    public void addItem(int mesa, String codigo, int cantidad){
        addItem(mesa, almacen.getProducto(codigo), cantidad);
    }
    public void addItem(int mesa, Producto p){
        addItem(mesa, p, 1);
    }
    public void addItem(int mesa, Producto p, int cantidad){
        try {
            if(!(p.getClass().equals(Propina.class) || p.getClass().equals(Cubiertos.class)))
                p.reduceStock(1);
            getCuenta(mesa).addItem(new Item(p.getCodigo(), p.getNombre(), p.getVenta(), p.getCompra(), cantidad));
        } catch (NoStockExcepcion e) {
            System.out.println("Error: "+e);
        }
    }
    private Cuenta getCuenta(int mesa){
        Cuenta c = null;
        int i = 0;
        while(i < cuentas.size() && c == null)
            if(cuentas.get(i).getMesa() == mesa)
                c = cuentas.get(i);
            else i++;
        return c;
    }
    
    public double calcularGanancia(){
        double ganancia = 0;
        for(Cuenta c : cuentas)
            ganancia += c.ganancia();
        return ganancia;
    }
    
    public String hacerPedido(){
        StringBuffer s = new StringBuffer();
        float totalPedido = 0;
        
        s.append(nombre).append(" Nif:").append(nif).append("\n");
        s.append("Descripcion\tcantidad\tprecio\tTotal\n");
        for(Cuenta c : cuentas){
            s.append(c.descripcion()).append("\n");
            totalPedido += c.precioCompra();
        }
        s.append("----------------------------------\n");
        s.append("Total pedido: ").append(totalPedido).append(" Euros");
        
        return s.toString();
    }
}
