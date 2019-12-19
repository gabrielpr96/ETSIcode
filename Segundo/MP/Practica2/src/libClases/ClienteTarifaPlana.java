package libClases;

public class ClienteTarifaPlana extends Cliente{
    
    private static float tarifa = 20f;
    private static int limiteMinutos = 300;
    private static float sobreCoste = 0.15f;
    
    private float minutosHablados;
    private String nacionalidad;
    
    public ClienteTarifaPlana(String NIF, String nom, Fecha fNac, Fecha fAlta, float minutosHablados, String nacionalidad) {
        super(NIF, nom, fNac, fAlta);
        this.minutosHablados = minutosHablados;
        this.nacionalidad = nacionalidad;
    }
    
    public ClienteTarifaPlana(String NIF, String nom, Fecha fNac, float minutosHablados, String nacionalidad) {
        super(NIF, nom, fNac);
        this.minutosHablados = minutosHablados;
        this.nacionalidad = nacionalidad;
    }
    
    public ClienteTarifaPlana(ClienteTarifaPlana c){
        super(c);
        this.minutosHablados = c.minutosHablados;
        this.nacionalidad = c.nacionalidad;
    }
    
    public float getMinutos(){
        return minutosHablados;
    }
    public String getNacionalidad(){
        return nacionalidad;
    }
    
    public void setMinutos(float m){
        minutosHablados = m;
    }
    public void setNacionalidad(String nac){
        this.nacionalidad = nac;
    }
    
    public float factura(){
        return tarifa + (minutosHablados>limiteMinutos?(minutosHablados-limiteMinutos)*sobreCoste:0);
    }
    
    
    public static float getTarifa(){
        return tarifa;
    }
    public static int getLimite(){
        return limiteMinutos;
    }
    public static void setTarifa(int l, float t){
        limiteMinutos = l;
        tarifa = t;
    }
    
    
    @Override
    public String toString(){
        return super.toString()+" "+nacionalidad+" ["+limiteMinutos+" por "+tarifa+"] "+minutosHablados+" ---> "+factura();
    }
    @Override
    public Object clone(){
        return new ClienteTarifaPlana(getNif(), getNombre(), getFechaNac(), getFechaAlta(), getMinutos(), getNacionalidad());
    }
    @Override
    public boolean equals(Object o){
        return o instanceof ClienteTarifaPlana && getNif().equals(((Cliente)o).getNif());
    }
}
