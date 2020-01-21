package libProfesor;

public class ClaseA {
    private int n;
    private int[] tabla;
    
    public ClaseA(int n){
        this.n = n;
        tabla = null;
        if(n > 0){
            tabla = new int[n];
            for(int i = 0; i < n; i++)
                tabla[i] = i*2+1;
        }
    }
    
    public ClaseA(){
        this.n = 0;
        this.tabla = null;
    }
    
    public int get(int n){
        if(n >= 0 && n < tabla.length)
            return tabla[n];
        return -1;
    }
    public int getN(){
        return n;
    }
    
    @Override
    public String toString(){
        if(tabla == null) return "";
        StringBuffer b = new StringBuffer();
        for(int i = 0; i < tabla.length-1; i++)
            b.append(tabla[i]+"-");
        b.append(tabla[tabla.length-1]);
        return b.toString();
    }
    
    public static boolean mayor(ClaseA a, ClaseA b){
        return a.n>b.n;
    }
}
