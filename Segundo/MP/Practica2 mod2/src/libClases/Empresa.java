package libClases;

import java.util.ArrayList;
import java.util.Scanner;



public class Empresa implements Cloneable, Proceso{

    private Cliente[] clientes;
    private int nClientes;
    private final int INCREMENTO = 2;
    
    public Empresa(){
        this.clientes = new Cliente[INCREMENTO];
        this.nClientes = 0;
    }
    
    public void alta(Cliente c){
        if (c == null || buscarCliente(c.getNif()) != -1) return;
        clientes[nClientes++] = c;
        if(nClientes == clientes.length){
            Cliente[] tmp = new Cliente[clientes.length+INCREMENTO];
            for(int i = 0; i < clientes.length; i++)
                tmp[i] = clientes[i];
            clientes = tmp;
        }
    }
    public void alta(){
        Cliente c;
        String NIF, nombre;
        Fecha fNac, fAlta;
        float mHablados;
        Scanner s = new Scanner(System.in);
        
        System.out.print("DNI: ");
        NIF = s.nextLine();
        
        int pos = buscarCliente(NIF);
        if(pos != -1){
            System.out.println("Ya existe un cliente con ese DNI:\n"+clientes[pos]+"\n");
        }else{
            System.out.print("Nombre: ");
            nombre = s.nextLine();
            System.out.println("Fecha de nacimiento: ");
            fNac = Fecha.pedirFecha();
            System.out.println("Fecha de alta: ");
            fAlta = Fecha.pedirFecha();
            System.out.print("Minutos que habla al mes: ");
            mHablados = s.nextFloat();

            System.out.print("Indique tipo de cliente (1-Movil, 2-Tarifa Plana): ");
            if(s.nextInt() == 1){
                float pMinuto;
                Fecha fPermanencia;
                System.out.print("Precio por minuto: ");
                pMinuto = s.nextFloat();
                System.out.println("Fecha fin permanencia:");
                fPermanencia = Fecha.pedirFecha();

                c = new ClienteMovil(NIF, nombre, fNac, fAlta, fPermanencia, mHablados, pMinuto);
            }else{
                String nacionalidad;
                System.out.print("Nacionalidad: ");
                nacionalidad = s.nextLine();

                c = new ClienteTarifaPlana(NIF, nombre, fNac, fAlta, mHablados, nacionalidad);
            }

            alta(c);
        }
    }
    
    public void baja(String codigo){
        int pos = buscarCliente(codigo);
        if(pos != -1){
            for(int i = pos; i < nClientes-1; i++)
                clientes[i] = clientes[i+1];
            nClientes--;
        }
        if(nClientes < clientes.length - INCREMENTO){
            Cliente[] tmp = new Cliente[clientes.length-INCREMENTO];
            for(int i = 0; i < tmp.length; i++)
                tmp[i] = clientes[i];
            clientes = tmp;
        }
    }
    public void baja(){
        Scanner s = new Scanner(System.in);
        
        System.out.print("Introduzca nif cliente a dar de baja: ");
        int pos = buscarCliente(s.nextLine());
        if(pos == -1)
            System.out.println("No se ha encontrado el cliente.");
        else{
            Cliente c = clientes[pos];
            System.out.println(c);
            System.out.print("¿Seguro que desea eliminarlo (s/n)? ");
            if(s.nextLine().equals("s")){
                baja(c.getNif());
                System.out.println("El cliente "+c.getNombre()+" con nif "+c.getNif()+" ha sido eliminado.\n");
            }else{
                System.out.println("El cliente con nif "+c.getNif()+" no se elimina.\n");
            }
        }
    }
    
    public float factura(){
        float factura = 0;
        
        for(int i = 0; i  < nClientes; i++)
            factura += clientes[i].factura();
        
        return factura;
    }
    public void descuento(int porcentaje){
        float descuento = (float)((100-porcentaje))/100.0f;
        for(int i = 0; i  < nClientes; i++)
            if(clientes[i] instanceof ClienteMovil){
                ClienteMovil c = (ClienteMovil)clientes[i];
                c.setPrecioMinuto(c.getPrecioMinuto()*descuento);
            }
    }
    
    public int getN(){
        return nClientes;
    }
    public int nClienteMovil(){
        int n = 0;
        for(int i = 0; i < nClientes; i++)
            if(clientes[i] instanceof ClienteMovil)
                n++;
        return n;
    }
    public int getClienteTarifaPlana(){
        int n = 0;
        for(int i = 0; i < nClientes; i++)
            if(clientes[i] instanceof ClienteTarifaPlana)
                n++;
        return n;
    }
    
    private int buscarCliente(String dni){
        boolean encontrado = false;
        
        int i = 0;
        while(i < nClientes && !encontrado)
            if(clientes[i].getNif().equals(dni))
                encontrado = true;
            else i++;
        
        if(encontrado)
            return i;
        else return -1;
    }
    
    
    @Override
    public void ver() {
        System.out.println(this);
    }
    
    @Override
    public Object clone(){
        Empresa obj=null;
        try{
            obj= (Empresa)super.clone();
            obj.clientes = clientes.clone();
            for(int i = 0; i < nClientes; i++)
                obj.clientes[i] = (Cliente)clientes[i].clone();
        }catch(CloneNotSupportedException ex){
            
        }
        return (Object)obj;
    }
    
    @Override
    public String toString(){
        String texto = "";
        
        for(int i = 0; i < nClientes; i++)
            texto += clientes[i]+"\n";
        
        return texto;
    }
 
    
    //2.a) Mostrar al cliente de cada tipo que tiene mayor factura.
    public void mostrarMejores(){
        float mejorM = -1, mejorTP = -1;
        
        for(int i = 0; i < nClientes; i++)
            if(clientes[i].getClass().equals(ClienteMovil.class) && (mejorM < 0 || clientes[i].factura() > mejorM))
                mejorM = clientes[i].factura();
            else if(clientes[i].getClass().equals(ClienteTarifaPlana.class) && (mejorTP < 0 || clientes[i].factura() > mejorTP))
                mejorTP = clientes[i].factura();
        
        System.out.println("Mejor cliente movil:");
        for(int i = 0; i < nClientes; i++)
            if(clientes[i].getClass().equals(ClienteMovil.class) && clientes[i].factura() == mejorM)
                System.out.println(clientes[i]);
        System.out.println("Mejor cliente tarifa plana:");
        for(int i = 0; i < nClientes; i++)
            if(clientes[i].getClass().equals(ClienteTarifaPlana.class) && clientes[i].factura() == mejorTP)
                System.out.println(clientes[i]);
    }
    
    //2.b) Un metdo estatico para empresa al que se le pasan dos objetos empresa. A la primera empresa se le agregan los clientes que hablen más de los minutos que tienen contratados y se quitan de la segunda empresa. (Se los roba)
    public static void rovarExtraHabladoresTP(Empresa destino, Empresa origen){
        ArrayList<Cliente> rovados = new ArrayList<>();
        for(int i = 0; i < origen.nClientes; i++)
            if(origen.clientes[i].getClass().equals(ClienteTarifaPlana.class) && ((ClienteTarifaPlana)origen.clientes[i]).getMinutos() > ClienteTarifaPlana.getLimite())
                rovados.add(origen.clientes[i]);
        for(Cliente c : rovados){
            destino.alta(origen.clientes[origen.buscarCliente(c.getNif())]);
            origen.baja(c.getNif());
        }
    }

    //2.c) Hacer el equals se Empresa. Se considera que dos empresas son iguales si tienen los mismos clientes, aunque no esten en el mismo orden.
    @Override
    public boolean equals(Object o){
        if(o.getClass().equals(Empresa.class)){
            if(o == this) return true;
            Empresa e = (Empresa)o;
            if(e.getN() == nClientes){
                int i = 0;
                boolean encontrado = false;
                boolean todoBien = true;
                while(i < nClientes && todoBien){
                    int j = 0;
                    while(j < e.getN() && !encontrado){
                        encontrado = clientes[i].equals(e.clientes[j]);
                        j++;
                    }
                    if(!encontrado)
                        todoBien = false;
                    i++;
                }
                return todoBien;
            }
        }
        return false;
    }
    
    
    public static void main(String[] args) {
        Fecha f1=new Fecha(29,2,2001), f2= new Fecha(f1), f3=new Fecha(29,2,2004);
        Fecha fnac1 = new Fecha(7,3,1980), fnac2 = new Fecha(27,06,1995);
        ClienteTarifaPlana [] ct= new ClienteTarifaPlana[4];
        ClienteMovil cm1 = new ClienteMovil("547B", "Luis Perez", fnac2, 50.50f, 0.03f);
        ClienteMovil cm2 = new ClienteMovil("777F", "Joe Sam", fnac2.diaSig(), 50.50f, 0.02f);
        ClienteMovil cm3 = new ClienteMovil("787J", "Billy Edwawrd", cm2.getFechaNac(), 50.50f, 0.02f); //Este lo he puesto yo
        ct[0] = new ClienteTarifaPlana("805W","Luz Casal", fnac1, f3, 375.09f, "Española");
        ct[1] = new ClienteTarifaPlana("953H","Paz Padilla", fnac2,f2, 290.00f, "Española");
        ct[2] = new ClienteTarifaPlana("106T","Elton John", fnac2, 340.75f, "Inglesa");
        ct[3] = new ClienteTarifaPlana("467X","Messi", fnac2.diaSig(), 300.00f, "Argentina");
        Empresa g=new Empresa();
        g.alta(cm1);
        g.alta(ct[0]);
        g.alta(ct[2]);
        g.alta(new ClienteTarifaPlana("433W","Rosi de Palma", fnac1, f3, 375.09f, "Canaria"));
        g.alta(ct[1]);
        g.alta(cm2);
        g.alta(cm3);
        g.alta(ct[1]);
        
        System.out.println("Grupo g:\n" + g);
        
        //Prueba 2.a
        g.mostrarMejores();
        
        //Prueba 2.b
        Empresa a = new Empresa();
        a.alta(new ClienteTarifaPlana("988H", "Bravuto Bujias", fnac2, 100, "Catalan"));
        a.alta(new ClienteMovil("776E", "Mari Curi", fnac2, 50, 0.35f));
        Empresa.rovarExtraHabladoresTP(a, g);
        System.out.println("Grupo g (perdedor de cleintes):\n"+g);
        System.out.println("Grupo a (ganador de cleintes):\n"+a);
        
        //Prueba 2.c
        System.out.println("Empresa g es "+(g.equals(a)?"igual":"diferente")+" a la empresa a");
        System.out.println("Empresa a es "+(a.equals(g)?"igual":"diferente")+" a la empresa g");
        Empresa c = (Empresa)g.clone();
        System.out.println("Empresa g es "+(g.equals(c)?"igual":"diferente")+" a la empresa c");
        System.out.println("Empresa c es "+(c.equals(g)?"igual":"diferente")+" a la empresa g");
        Cliente tmp = c.clientes[2]; //Desordeno los clientes
        c.baja(tmp.getNif());
        c.alta(tmp);
        tmp = c.clientes[0];
        c.baja(tmp.getNif());
        c.alta(tmp);
        System.out.println("Empresa g es "+(g.equals(c)?"igual":"diferente")+" a la empresa c");
        System.out.println("Empresa c es "+(c.equals(g)?"igual":"diferente")+" a la empresa g");
        System.out.println("Empresa c es "+(c.equals(a)?"igual":"diferente")+" a la empresa a");
        System.out.println("Empresa a es "+(a.equals(c)?"igual":"diferente")+" a la empresa c");
    } 
}
