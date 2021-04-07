package patronsingletron;

public final class Singletron {
    private static final Singletron singletron = new Singletron();
    private static int cantidad;
    
    public Singletron(){
        cantidad = 0;
        System.out.println("Solo existo una vez");
    }
    
    public static void operacionSingletron(){
        System.out.println("Me has llamado "+cantidad+" veces");
    }
    
    public static Singletron obtenerDatosSingletron(){
        cantidad++;
        return singletron;
    }
}
