package libClases;

import static java.lang.Math.abs;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Fecha implements Cloneable, Proceso {
 
    private int dia, mes, anio;
    
    public Fecha(int dia, int mes, int anio){
        setFecha(dia, mes, anio);
    }
    public Fecha(Fecha f){
        this(f.dia, f.mes, f.anio);
    }
    
    public int getDia(){
        return dia;
    }
    public int getMes(){
        return mes;
    }
    public int getAnio(){
        return anio;
    }
    public boolean bisiesto(){
        return anio%400 == 0 || (anio%4 == 0 && anio%100 != 0);
    }
    
    public void setDia(int dia){
        this.dia = dia;
    }
    public void setMes(int mes){
        this.mes = mes;
    }
    public void setAnio(int anio){
        this.anio = anio;
    }
    
    public void setFecha(int dia, int mes, int anio){
        this.dia = dia;
        this.mes = mes;
        this.anio = abs(anio);

        if(mes < 1)
            this.mes = 1;
        else if(mes > 12)
            this.mes = 12;

        int maxDias[] = {0,31,bisiesto()?29:28,31,30,31,30,31,31,30,31,30,31};

        //Redondeo al día mas cercano
        if(dia < 1)
            this.dia = 1;
        else if(dia > maxDias[this.mes])
            this.dia = maxDias[this.mes];
    }
    
    public Fecha diaSig(){
        Fecha f = (Fecha)clone();
        
        f.dia ++;

        int maxDias[] = {0,31,bisiesto()?29:28,31,30,31,30,31,31,30,31,30,31};

        //El incremento de dias afecta al mes y al año
        if(f.dia > maxDias[f.mes]){
            f.dia -= maxDias[f.mes];
            f.mes++;
            if(f.mes > 12){
                f.mes = 1;
                f.anio++;
            }
        }
        
        return f;
    }
    
    public static boolean mayor(Fecha f1, Fecha f2){
        return  f1.anio > f2.anio ||
                (f1.anio == f2.anio && f1.mes > f2.mes) ||
                (f1.anio == f2.anio && f1.mes == f2.mes && f1.dia > f2.dia);
    }
    public static Fecha pedirFecha(){
        Scanner s = new Scanner(System.in);
        Fecha f = null;
        boolean valida;
        
        do{
            System.out.print("Introduzca una fecha[dia mes anio]: ");
            String[] datos = s.nextLine().split("/");
            if(datos.length != 3){
                valida = false;
            }else{
                int fDia = Integer.parseInt(datos[0]), fMes = Integer.parseInt(datos[1]), fAnio = Integer.parseInt(datos[2]);
                int maxDias[] = {0,31,(fAnio%400 == 0 || (fAnio%4 == 0 && fAnio%100 != 0))?29:28,31,30,31,30,31,31,30,31,30,31};
                valida = fMes >= 1 && fMes <= 12 && fDia >= 1 && fDia <= maxDias[fMes];
                if(valida)
                    f = new Fecha(fDia, fMes, fAnio);
            }
            if(!valida)
                System.out.println("Fecha no valida");
        }while(!valida);
        s.close();
        return f;
    }
    
    @Override
    public String toString(){
        return (dia<10?"0":"")+dia+"/"+(mes<10?"0":"")+mes+"/"+anio;
    }
    @Override
    public Object clone(){
        Object obj=null;
        try{
            obj=super.clone();
        }catch(CloneNotSupportedException ex){
            
        }
        return obj;
    }
    
    @Override
    public void ver() {
        System.out.println(this);
    }
    @Override
    public boolean equals(Object o){
        if(!(o instanceof Fecha)) return false;
        Fecha f = (Fecha)o;
        return dia == f.dia && mes == f.mes && anio == f.anio;
    }
    
    
    
    public static void main(String[] args) {
        Fecha f1 = new Fecha(29,2,2001), f2 = new Fecha(f1), f3 = new Fecha(29,2,2004);
        final Fecha f4=new Fecha(05,12,2023); //es constante la referencia f4
        System.out.println("Fechas: " + f1.toString() + ", "+f2+ ", " +f3+ ", " +f4+ "\n");
        f1=new Fecha(31,12,2016); //31/12/2016
        f4.setFecha(28, 2, 2008); //pero no es constante el objeto al que apunta
        System.out.println(f1 +" "+ f2.toString() +" " + f3 +" "+ f4+" "+ f1);
        f1=new Fecha(f4.getDia()-10, f4.getMes(), f4.getAnio()-7); //f1=18/02/2001
        f3=Fecha.pedirFecha(); //pide una fecha por teclado
        if (f3.bisiesto() && Fecha.mayor(f2,f1))
            System.out.print("El " + f3.getAnio() + " fue bisiesto, " + f1 + ", " + f3);
    } 
}