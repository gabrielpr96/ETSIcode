package libClases;

public class Revista extends Volumen{
    
    private int nNumeros;
    private int[] numeros;
    
    public Revista(String titulo, String autor, long ISBN, int numero) {
        super(titulo, autor, ISBN);
        nNumeros = 1;
        numeros = new int[1];
        numeros[0] = numero;
    }
    
    public void agregarNumero(){
        agregarNumero(numeros[nNumeros-1]+1);
    }
    public void agregarNumero(int n){
        if(nNumeros == numeros.length){
            int[] aux = new int[numeros.length*2];
            for(int i = 0; i < nNumeros; i++)
                aux[i] = numeros[i];
            numeros = aux;
        }
        numeros[nNumeros] = n;
        nNumeros++;
    }
    
    @Override
    public String toString(){
        StringBuffer b = new StringBuffer();
        b.append(super.toString()+" ("+nNumeros+" numeros: ");
        for(int i = 0; i < nNumeros-1; i++)
            b.append(numeros[i]+",");
        b.append(numeros[nNumeros-1]+")");
        return b.toString();
    }
    
}
