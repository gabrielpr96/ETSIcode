package patronprototypepractica;

public class CreadorSeresVivos extends Creador{

    @Override
    public SerVivo crearServivo(TipoSerVivo tipo) {
        switch(tipo){
            case Persona:
                return new Persona();
            case Mascota:
                return new Mascota();
        }
        return null;
    }
    
}
