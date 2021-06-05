package patronsingletron;

public class PatronSingletron {

    public static void main(String[] args) {
        Singletron miSingletron1 = Singletron.obtenerDatosSingletron();
        Singletron miSingletron2 = Singletron.obtenerDatosSingletron();
        Singletron miSingletron3 = Singletron.obtenerDatosSingletron();
        Singletron miSingletron4 = Singletron.obtenerDatosSingletron();
        Singletron miSingletron5 = Singletron.obtenerDatosSingletron();
        Singletron miSingletron6 = Singletron.obtenerDatosSingletron();
        
        miSingletron1.operacionSingletron();
        miSingletron6.operacionSingletron();
    }
    
}
