package patronprototypepractica;

import static patronprototypepractica.Creador.TipoSerVivo;
import patronprototypepractica.interfaz.MenuPrincipal;

public class PatronPrototypePractica {

    public static void main(String[] args) {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.setLocationRelativeTo(null);
        menuPrincipal.setVisible(true);
        
        Creador creador = new CreadorSeresVivos();

        //Crear una nueva persona
        Persona adam = (Persona) creador.crearServivo(TipoSerVivo.Persona);
        adam.setNombre("Adam");
        adam.setApellidos("Whitney Savage");
        adam.setTelefono("689140827");
        adam.setDireccion("Calle las bocas");
        adam.setCodigoPostal("21001");
        adam.setCiudad("Huelva");
        adam.setNivelEconomico("Muy alto");
        adam.setCorreoElectronico("mythbusters@yahoo.es");
        adam.setNivelDeEstudios("EGB");
        
        //Clonar a adam para agregar a sofia a la unidad familiar
        Persona sofia = (Persona) adam.clonar();
        sofia.setNombre("Sofia");
        sofia.setApellidos("Savage");
        sofia.setCorreoElectronico("sofiavergara@hotmail.es");
        sofia.setNivelDeEstudios("Grado superior");
        
        //Crear la primera mascota de adam
        Mascota shebu = (Mascota) creador.crearServivo(TipoSerVivo.Mascota);
        shebu.setNombre("Shebu");
        shebu.setDireccion("Japón");
        shebu.setDueno(adam);
        
        //Crear la segunda mas cota de adam
        Mascota devy = (Mascota)shebu.clonar();
        devy.setNombre("Devy");
        
        //Crear la mascota de sofia, pertenece a sofia pero su cabeza de familia es adam porque es la cabeza de familia de sofia
        Mascota sonny = (Mascota) creador.crearServivo(TipoSerVivo.Mascota);
        sonny.setNombre("Sonny Boop");
        sonny.setDireccion("Australia");
        sonny.setDueno(sofia);
        
        menuPrincipal.addSerVivo(adam);
        menuPrincipal.addSerVivo(sofia);
        menuPrincipal.addSerVivo(shebu);
        menuPrincipal.addSerVivo(devy);
        menuPrincipal.addSerVivo(sonny);
    }

}
