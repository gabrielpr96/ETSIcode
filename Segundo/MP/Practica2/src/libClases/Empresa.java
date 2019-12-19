package libClases;

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
    
}
