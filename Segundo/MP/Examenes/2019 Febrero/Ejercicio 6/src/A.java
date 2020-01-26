public class A {
    private final int at = 5;
    
    public static int met(){return at;}             //No se puede acceder a at porque no es estatica.
    public void met(){System.out.println(at++);}    //at no se puede modificar porque es final
}
