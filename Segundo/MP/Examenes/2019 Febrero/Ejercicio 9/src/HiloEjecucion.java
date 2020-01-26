public class HiloEjecucion {
    public static boolean[] datos = new boolean[3];
    
    public static String met(int i, boolean valor){
        String salida = "";
        try {
            salida += datos[i];
            datos[i] = valor;
            salida += "OK";
        } catch (Exception e) {
            salida += "Excepcion ";
        }finally{
            salida += "Finally ";
        }
        salida += "--";
        return salida;
    }
    
    public static void main(String[] args){ //Datos 1 0 0
        System.out.println(met(0, true));       //Salida: falseOKFinally --
        System.out.println(met(3, false));      //Salida: Excepcion Finally --
    }
}
