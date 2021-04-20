package patronfactoryfiguras;

public class FactoriaFiguras extends Factoria{
    protected String tipo;

    public FactoriaFiguras(String tipo) {
        this.tipo = tipo;
    }
    @Override
    public Figura creaFigura() {
        switch(tipo.toLowerCase()){
            case "circulo": return new Circulo();
            case "cuadrado": return new Cuadrado();
            case "rectangulo": return new Rectangulo();
            default: return new Figura();
        }
    }
    
}
