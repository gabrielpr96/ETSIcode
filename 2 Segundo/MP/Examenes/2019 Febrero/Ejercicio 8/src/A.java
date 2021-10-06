public class A implements IA{
    private int at;
    public int met(){  //Si no se pone nada, por defecto es privado y eso no concuerda con la definición de la interfaz.
        met(at);    //Esta definido en la interfaz, pero no implementado en la clase.
        return at;
    }
    //La clase A no implementa todos los metodos de la interfaz IA. (de hecho ninguno) A podria hacer esto si fuera abstracta, pero no lo es
}
