package libClases;

public class Cuenta {
    
    private final int mesa;
    private final Item[] items;
    int nItems;
    
    public Cuenta(int mesa, int maxItems){
        this.mesa = mesa;
        items = new Item[maxItems];
        nItems = 0;
    }
    
    public void addItem(Item p){
        if(nItems < items.length)
            items[nItems++] = p;
    }
    
    public int getMesa(){
        return mesa;
    }
    
    public float precioCompra(){
        float precio = 0;
        for(int i = 0; i < nItems; i++)
            precio += items[i].getCompra()*items[i].getCantidad();
        return precio;
    }
    public float precioVenta(){
        float precio = 0;
        for(int i = 0; i < nItems; i++)
            precio += items[i].getVenta()*items[i].getCantidad();
        return precio;
    }
    
    public String descripcion(){
        StringBuffer s = new StringBuffer();
        
        for(int i = 0; i < nItems; i++)
            if(!(items[i].getNombre().startsWith("Propina") || items[i].getNombre().startsWith("Cubiertos")))
                s.append(items[i].getNombre()).append("\t").append(items[i].getCantidad()).append("\t\t").append(items[i].getCompra()).append("\t").append(items[i].getCompra()*items[i].getCantidad()).append("\n");
        
        
        return s.toString();
    }
    
    public double ganancia(){
        return precioVenta()-precioCompra();
    }
}
