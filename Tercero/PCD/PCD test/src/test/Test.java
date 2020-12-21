package test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {
    public static void main(String[] args) throws InterruptedException, ExecutionException{
        ExecutorService thp = Executors.newFixedThreadPool(2);
        
        Future[] f = new Future[5];
        for (int i = 0; i < 5; i++) {
            ConRetorno r = new ConRetorno(i);
            f[i] = thp.submit(r);
        }
        
        thp.shutdown();
        for (int i = 4; i >= 0; i--) {
            System.out.println("El resultado es: "+f[i].get());
        }
        
        System.out.println("Main termina");
    }
}
