package libClases;

import java.util.Scanner;

public class Persona {
    private int [] telefonos;
    private int edad;
    private String nombre;
    
    public Persona(String nombre, int edad, int nTelefonos){
        this.nombre = nombre;
        this.edad = edad;
        telefonos = new int[nTelefonos];
        Scanner s = new Scanner(System.in);
        for(int i = 0; i < nTelefonos; i++){
            System.out.print("Indica el telefono"+(i+1)+" de "+nombre+": ");
            telefonos[i] = s.nextInt();
        }
    }
    public Persona(){
        Scanner s = new Scanner(System.in);
        System.out.print("Nombre: ");
        nombre = s.nextLine();
        System.out.print("Edad: ");
        edad = s.nextInt();
        System.out.print("Cuantos telefonos tiene: ");
        int nTelefonos = s.nextInt();
        telefonos = new int[nTelefonos];
        for(int i = 0; i < nTelefonos; i++){
            System.out.print("Indica el telefono"+(i+1)+" de "+nombre+": ");
            telefonos[i] = s.nextInt();
        }
    }
    public Persona(Persona p){
        nombre = p.nombre;
        edad = p.edad;
        telefonos = p.telefonos.clone();
    }
    
    @Override
    public String toString(){
        StringBuffer b = new StringBuffer(nombre+" tiene "+edad+" años y tiene "+telefonos.length+" telefonos");
        if(telefonos.length > 0){
            b.append("(");
            for(int i = 0; i < telefonos.length-1; i++)
                b.append(telefonos[i]+" ");
            b.append(telefonos[telefonos.length-1]+")");
        }
        return b.toString();
    }
    
    public String getNombre(){
        return nombre;
    }
    public static boolean mayor(Persona a, Persona b){
        return a.edad>b.edad;
    }
}
