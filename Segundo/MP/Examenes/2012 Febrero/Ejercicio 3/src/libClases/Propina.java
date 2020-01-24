
package libClases;

public class Propina extends Producto{
    
    public Propina(float cantidad) {
        super("0000A", "Propina", 0, 0, cantidad, 0f);
    }
    public Propina(){
        this(1f);
    }
    
}
