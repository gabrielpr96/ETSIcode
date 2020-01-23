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
 
    
    //
    public static void subirPrecioAMP(Empresa e, int anios){
        for(int i = 0; i < e.nClientes; i++){
            if(e.clientes[i].getClass().equals(ClienteMovil.class)){
                ClienteMovil cm = (ClienteMovil)e.clientes[i];
                if(Fecha.diffAnios(cm.getFPermanencia(), cm.getFechaAlta()) >= anios){
                    cm.setPrecioMinuto(cm.getPrecioMinuto()*1.05f);
                }
            }
        }
    }
    
    //Sacar un float con la facturacion total de todos los contratosTP
    public float resumenFacturaTP(){
        float total = 0;
        for(int i = 0; i < nClientes; i++){
            if(clientes[i].getClass().equals(ClienteTarifaPlana.class))
                total += clientes[i].factura();
        }
        return total;
    }
    
    //Eliminar peores, el/los que facturan menos clientes de contrato TP y una nacionalidad en concreto
    public void eliminarPeoresTP(String nacionalidad){
        float peor = -1;
        for(int i = 0; i < nClientes; i++)
            if(clientes[i].getClass().equals(ClienteTarifaPlana.class))
                if(((ClienteTarifaPlana)clientes[i]).getNacionalidad().equals(nacionalidad) && (peor < 0 && clientes[i].factura() < peor))
                    peor = clientes[i].factura();
        
        int i = 0;
        while(i < nClientes){
            if(clientes[i].factura() == peor)
                baja(clientes[i].getNif());
            else i++;
        }
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
        
        //
    } 
}
