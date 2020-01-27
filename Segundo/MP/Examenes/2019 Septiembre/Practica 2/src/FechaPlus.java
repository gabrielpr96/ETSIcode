
import java.util.logging.Level;
import java.util.logging.Logger;

public class FechaPlus extends Fecha implements Cloneable{
    private int nCambios;
    
    public FechaPlus(int d, String m, int a){
        super(d, m, a);
        nCambios = 2;
    }
    
    @Override
    public void setFecha(int d, String m, int a){
        if(!(getDia() == d && getMes().equals(m) && getAnio() == a)){
            if(nCambios > 0){
                super.setFecha(d, m, a);
                nCambios--;
            }
        }
    }
    @Override
    public void setFecha(int d, String m){
        if(!(getDia() == d && getMes().equals(m))){
            if(nCambios > 0){
                super.setFecha(d, m);
                nCambios--;
            }
        }
    }
    @Override
    public void setFecha(int d){
        if(getDia() != d){
            if(nCambios > 0){
                super.setFecha(d);
                nCambios--;
            }
        }
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }else{
            if(obj.getClass().equals(FechaPlus.class)){
                FechaPlus f = (FechaPlus)obj;
                return getDia() == f.getDia() && getMes().equals(f.getMes()) && getAnio() == f.getAnio();
            }else{
                return false;
            }
        }
    }
    
    public static int diferencia(FechaPlus f1, FechaPlus f2){
        int dist = 0;
        
        if(f1.getDia() != f2.getDia()) dist++;
        if(!f1.getMes().equals(f2.getMes())) dist++;
        if(f1.getAnio() != f2.getAnio()) dist++;
        
        return dist;
    }
    
    public static boolean identicos(Object f1, Object f2){
        return f1.getClass().equals(f2.getClass()) && ((!f1.getClass().equals(FechaPlus.class)) || ((f1.getClass().equals(FechaPlus.class)) && ((FechaPlus)f1).nCambios==((FechaPlus)f2).nCambios));
    }
    
    @Override
    public Object clone(){
        FechaPlus f = new FechaPlus(getDia(), getMes(), getAnio());
        f.nCambios = nCambios;
        return f;
    }
    
    public static void main(String[] args){
        FechaPlus f1 = new FechaPlus(2, "feb", 2000), f2 = new FechaPlus(3, "mar", 2000);
        int n = FechaPlus.diferencia(f1, f2);
        boolean b = f1.equals(f2);
        b = f1.equals("feb");
        b = f1.equals(new Fecha(2, "feb", 2000));
        
        FechaPlus f3 = new FechaPlus(3, "ene", 2001), f4;
        f3.setFecha(3, "ene", 2001);
        f3.setFecha(1, "feb", 2009);
        f3.setFecha(2, "ene");
        f3.setFecha(5, "mar");
        
        f4 = (FechaPlus)f3.clone();
        f4.setFecha(5, "may", 2005);
        System.out.println(f4);
        
        f4 = new FechaPlus(2, "ene", 2009);
        b = f3.equals(f4);
        b=FechaPlus.identicos(f3, f4);
        
        b = FechaPlus.identicos(12, f4);
        b = FechaPlus.identicos(f4, new Fecha(2, "feb", 2000));
    }
}
