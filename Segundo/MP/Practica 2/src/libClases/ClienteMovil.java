package libClases;

public class ClienteMovil extends Cliente{
    
    private Fecha fechaPermanencia;
    private float minutosHablados, precioMinuto;
    
    public ClienteMovil(String NIF, String nom, Fecha fNac, Fecha fAlta, Fecha fPermanencia, float minutosHablados, float precioMinuto) {
        super(NIF, nom, fNac, fAlta);
        this.minutosHablados = minutosHablados;
        this.precioMinuto = precioMinuto;
        this.fechaPermanencia = new Fecha(fPermanencia);
    }

    public ClienteMovil(String NIF, String nom, Fecha fNac, Fecha fAlta, float minutosHablados, float precioMinuto) {
        super(NIF, nom, fNac, fAlta);
        this.minutosHablados = minutosHablados;
        this.precioMinuto = precioMinuto;
        this.fechaPermanencia = new Fecha(fAlta);
        fechaPermanencia.setAnio(fechaPermanencia.getAnio()+1);
    }
    
    public ClienteMovil(String NIF, String nom, Fecha fNac, float minutosHablados, float precioMinuto) {
        super(NIF, nom, fNac);
        this.minutosHablados = minutosHablados;
        this.precioMinuto = precioMinuto;
        this.fechaPermanencia = getFechaAlta();
        fechaPermanencia.setAnio(fechaPermanencia.getAnio()+1);
    }

    public ClienteMovil(ClienteMovil c) {
        super(c);
        this.minutosHablados = c.minutosHablados;
        this.precioMinuto = c.precioMinuto;
        this.fechaPermanencia = new Fecha(fechaPermanencia);
    }
    
    public Fecha getFPermanencia(){
        return new Fecha(fechaPermanencia);
    }
    public float getMinutos(){
        return minutosHablados;
    }
    public float getPrecioMinuto(){
        return precioMinuto;
    }
    
    public void setFPermanencia(Fecha f){
        this.fechaPermanencia = new Fecha(f);
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
    public Cliente clone(){
        return new ClienteMovil(getNif(), getNombre(), getFechaNac(), getFechaAlta(), getFPermanencia(), getMinutos(), getPrecioMinuto());
    }
    @Override
    public boolean equals(Object o){
        return o.getClass() == ClienteMovil.class && getNif().equals(((Cliente)o).getNif());
    }
}
