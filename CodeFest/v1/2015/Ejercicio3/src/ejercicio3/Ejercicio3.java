package ejercicio3;

public class Ejercicio3 {
       
    static int[][] contenedor2 = {{20,73,35},{29,78,31},{90,1,74}};
    
    public static void main(String[] args) {
        int[][][] ordenada = ordenar(contenedor2);
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(ordenada[i][j][0]+"-"+ordenada[i][j][1]+"    ");
            }
            System.out.println("\n");
        }
        int[] seleccionadas = seleccionar(contenedor2, ordenada);
        for(int i = 0; i < 3; i++){
            System.out.println("Seleccionada "+i+" = "+seleccionadas[i]);
        }
    }
    
    public static int[] seleccionar(int[][] contenedor, int[][][] ordenada){
        int[] seleccionadas = new int[3];
        
        int max = -1;
        int tmp = -1;
        boolean puedes;
        for(int j = 0; j < 3; j++){
            for(int i = 0; i < 3; i++){
                puedes = true;
                for(int k = 0; puedes && i < 3 && k != j; i++){
                    if()
                }
            }
        }
        
        
        return seleccionadas;
    }
    
    public static int[][][] ordenar(int[][] contenedor){
        int[][][] ordenada = new int[3][3][2];
        
        int max;
        for(int i = 0; i < 3; i++){
            max = -1;
            for(int j = 0; j < 3; j++){
                if(contenedor[j][i] > max){
                    max = contenedor[j][i];
                    ordenada[0][i][0] = j;
                    ordenada[0][i][1] = max;
                }
            }
            max = -1;
            for(int j = 0; j < 3; j++){
                if(j != ordenada[0][i][0] && contenedor[j][i] > max){
                    max = contenedor[j][i];
                    ordenada[1][i][0] = j;
                    ordenada[1][i][1] = max;
                }
            }
            ordenada[2][i][0] = noEsta(ordenada,i);
            ordenada[2][i][1] = contenedor[noEsta(ordenada,i)][i];
        }
        
        return ordenada;
    }
    
    public static boolean contiene(int[] array, int n){
        for(int i = 0; i < 3; i++)
            if(array[i] == n)
                return true;
        return false;
    }
    
    public static int noEsta(int[][][] ordenada, int i){
        for(int j = 0; j < 3; j++){
            boolean esta = false;
            for(int k = 0; k < 3; k++){
                if(ordenada[j][i][0] == k){
                    esta = true;
                    break;
                }
            }
            if(!esta){
                return j;
            }
        }
        return -1;
    }
}
