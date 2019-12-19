package libClases;

public class ClienteMovil extends Cliente{
    
    private Fecha fechaPermanencia;
    private float minutosHablados, precioMinuto;
    
    public ClienteMovil(String NIF, String nom, Fecha fNac, Fecha fAlta, Fecha fPermanencia, float minutosHablados, float precioMinuto) {
        super(NIF, nom, fNac, fAlta);
        this.minutosHablados = minutosHablados;
        this.precioMinuto = precioMinuto;
        this.fechaPermanencia = (Fecha)fPermanencia.clone();
    }

    public ClienteMovil(String NIF, String nom, Fecha fNac, Fecha fAlta, float minutosHablados, float precioMinuto) {
        this(NIF, nom, fNac, fAlta, new Fecha(fAlta.getDia(), fAlta.getMes(), fAlta.getAnio()+1), minutosHablados, precioMinuto);
    }
    
    public ClienteMovil(String NIF, String nom, Fecha fNac, float minutosHablados, float precioMinuto) {
        this(NIF, nom, fNac, getFechaPorDefecto(), minutosHablados, precioMinuto);
    }

    public ClienteMovil(ClienteMovil c) {
        super(c);
        this.minutosHablados = c.minutosHablados;
        this.precioMinuto = c.precioMinuto;
        this.fechaPermanencia = (Fecha)fechaPermanencia.clone();
    }
    
    public Fecha getFPermanencia(){
        return (Fecha)fechaPermanencia.clone();
    }
    public float getMinutos(){
        return minutosHablados;
    }
    public float getPrecioMinuto(){
        return precioMinuto;
    }
    
    public void setFPermanencia(Fecha f){
        this.fechaPermanencia = (Fecha)f.clone();
    }
    public void setMinutos(float m){
        minutosHablados = m;
    }
    public void setPrecioMinuto(float p){
        precioMinuto = p;
    }
    
    public float factura(){
        return minutosHablados*precioMinuto;
    }
    
    
    @Override
    public String toString(){
        return super.toString()+" "+fechaPermanencia+" "+minutosHablados+" x "+precioMinuto+" ---> "+factura();
    }
    @Override
    public Object clone(){
        return new ClienteMovil(getNif(), getNombre(), getFechaNac(), getFechaAlta(), getFPermanencia(), getMinutos(), getPrecioMinuto());
    }
    @Override
    public boolean equals(Object o){
        return o instanceof ClienteMovil && getNif().equals(((Cliente)o).getNif());
    }
}
