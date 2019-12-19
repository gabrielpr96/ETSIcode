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
        int mHablados;
        Scanner s = new Scanner(System.in);
        
        System.out.println("DNI: ");
        NIF = s.nextLine();
        System.out.println("Nombre: ");
        nombre = s.nextLine();
        System.out.println("Fecha de nacimiento: ");
        fNac = Fecha.pedirFecha();
        System.out.println("Fecha de alta: ");
        fAlta = Fecha.pedirFecha();
        System.out.println("Minutos que habla al mes: ");
        mHablados = s.nextInt();
        
        System.out.println("Indique tipo de cliente (1-Movil, 2-Tarifa Plana): ");
        if(s.nextInt() == 1){
            int pMinuto;
            Fecha fPermanencia;
            System.out.println("Precio por minuto:");
            pMinuto = s.nextInt();
            System.out.println("Fecha fin permanencia:");
            fPermanencia = Fecha.pedirFecha();
            
            c = new ClienteMovil(NIF, nombre, fNac, fAlta, fPermanencia, mHablados, pMinuto);
        }else{
            String nacionalidad;
            System.out.println("Nacionalidad: ");
            nacionalidad = s.nextLine();
            
            c = new ClienteTarifaPlana(NIF, nombre, fNac, fAlta, mHablados, nacionalidad);
        }
        
        
        s.close();
        alta(c);
    }
    
    public void baja(String codigo){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public void baja(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public float factura(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public void descuento(int porcentaje){
        throw new UnsupportedOperationException("Not supported yet.");
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
        while(i < nClientes)
            if(clientes[i].getNif().equals(dni))
                encontrado = true;
            else i++;
        
        if(encontrado)
            return i;
        else return -1;
    }
    
    
    @Override
    public void ver() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public Object clone(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public String toString(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
