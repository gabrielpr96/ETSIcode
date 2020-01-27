public class Fecha extends Object{
    private int dia, anio;
    private String mes;
    public Fecha(int d, String m, int a){
        dia = d;
        mes = m;
        anio =a;
    }
    
    public int getDia(){return dia;}
    public String getMes(){return mes;}
    public int getAnio(){return anio;}
    
    public void setFecha(int d, String m, int a){
        dia = d;
        mes = m;
        anio = a;
    }
    public void setFecha(int d, String m){
        dia = d;
        mes = m;
    }
    public void setFecha(int d){
        dia = d;
    }
    
    @Override
    public String toString(){
        return dia+"/"+mes+"/"+anio;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }else{
            if(obj.getClass().equals(Fecha.class)){
                Fecha f = (Fecha)obj;
                return getDia() == f.getDia() && getMes().equals(f.getMes()) && getAnio() == f.getAnio();
            }else{
                return false;
            }
        }
    }
}
