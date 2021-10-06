package pcd2019v1;

public class Piscina {
    public static final int CALLE_LIBRE = 1, CALLE_CLUB = 2, NADADOR_LIBRE = 1, NADADOR_CLUB = 2;
    private int libreLibre, libreClub, esperandoLibre;
    private final CanvasPiscina canvas;
    
    public Piscina(CanvasPiscina canvas){
        libreLibre = 1;
        libreClub = 4;
        esperandoLibre = 0;
        this.canvas = canvas;
    }
    
    public synchronized void entraLibre() throws InterruptedException{
        canvas.espera(NADADOR_LIBRE);
        esperandoLibre++;
        while(libreLibre < 1){
            wait();
        }
        esperandoLibre--;
        libreLibre--;
        canvas.entraLibre();
    }
    public synchronized int entraClub() throws InterruptedException{
        canvas.espera(NADADOR_CLUB);
        while(libreClub < 1 && (libreLibre < 1 || esperandoLibre > 0)){
            wait();
        }
        if(libreClub > 0){
            libreClub--;
            canvas.entraClub();
            return CALLE_CLUB;
        }else{
            libreLibre--;
            canvas.entraLibre();
            return CALLE_LIBRE;
        }
    }
    public synchronized void saleLibre(){
        canvas.sale();
        libreLibre++;
        notifyAll();
    }
    public synchronized void saleClub(int cual){
        canvas.sale();
        if(cual == CALLE_CLUB){
            libreClub++;
        }else{
            libreLibre++;
        }
        notifyAll();
    }
}
