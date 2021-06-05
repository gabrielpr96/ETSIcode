package patronfactorypractica;

public class FactoriaRelojes extends Factoria{
    protected String tipo;

    public FactoriaRelojes(String tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public Reloj crearReloj(){
        switch(tipo.toLowerCase()){
            case "dma12h": return new RelojDMA12();
            case "dma24h": return new RelojDMA24();
            case "mda12h": return new RelojMDA12();
            case "mda24h": return new RelojMDA24();
            case "iso": return new RelojISO();
            case "isoutc": return new RelojISOUTC();
            default: return new Reloj();
        }
    }

    @Override
    public String toString() {
        return "Factoria "+tipo;
    }
    
    
}
