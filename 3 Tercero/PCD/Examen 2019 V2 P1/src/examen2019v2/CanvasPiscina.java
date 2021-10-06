package examen2019v2;

import java.awt.Canvas;
import java.util.ArrayList;
import static examen2019v2.Piscina.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

class Cliente {

    public final long id;
    public boolean piscina, cogiendo, palas, aletas;

    public Cliente(long id) {
        this.id = id;
        piscina = false;
        cogiendo = false;
        palas = false;
        aletas = false;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != Cliente.class) {
            return false;
        }
        return ((Cliente) o).id == id;
    }
}

public class CanvasPiscina extends Canvas {

    private final ArrayList<Cliente> clientes;
    private int libreAletas, librePalas;
    private final Font fTexto = new Font("Courier New", Font.PLAIN, 20), fNumero = new Font("Consolas", Font.BOLD, 30);

    public CanvasPiscina() {
        clientes = new ArrayList<>();
        libreAletas = 6;
        librePalas = 5;
    }
    
    public void entraSistema(){
        clientes.add(new Cliente(Thread.currentThread().getId()));
        repaint();
    }
    
    public void entraPiscina(){
        getCliente().piscina = true;
        repaint();
    }
    
    public void comienzaBusqueda(){
        getCliente().cogiendo = true;
        repaint();
    }
    
    public void obtienePalas(){
        getCliente().palas = true;
        librePalas -= 2;
        repaint();
    }
    
    public void obtieneAletas(){
        getCliente().aletas = true;
        libreAletas -= 2;
        repaint();
    }
    
    public void saleSistema(){
        System.out.println("Sale");
        clientes.remove(getCliente());
        librePalas += 2;
        libreAletas += 2;
        repaint();
    }
    
    
    @Override
    public void update(Graphics g){
        paint(g);
    }
    
    public void paint(Graphics og){
        Image img = createImage(getWidth(), getHeight());
        Graphics g = img.getGraphics();
        
        g.setColor(Color.red);
        g.setFont(fTexto);
        g.drawString("Aletas", 20, 40);
        for (int i = 0; i < libreAletas; i++) {
            g.fillOval(30, 50+40*i, 30, 30);
        }
        
        g.setColor(Color.blue);
        g.setFont(fTexto);
        g.drawString("Palas", 15, 320);
        for (int i = 0; i < librePalas; i++) {
            g.fillOval(30, 330+40*i, 30, 30);
        }
        
        g.setColor(Color.black);
        g.setFont(fTexto);
        g.drawString("Piscina", 120, 40);
        int i = 0;
        for (Cliente cliente : clientes) {
            if(cliente.cogiendo){
                drawCliente(g, cliente, 160, 75+40*i);
                i++;
            }
        }
        
        g.setColor(Color.black);
        g.setFont(fTexto);
        g.drawString("Calentando", 220, 40);
        i = 0;
        for (Cliente cliente : clientes) {
            if(cliente.piscina && !cliente.cogiendo){
                drawCliente(g, cliente, 260, 75+40*i);
                i++;
            }
        }
        
        g.setColor(Color.black);
        g.setFont(fTexto);
        g.drawString("Esperando", 360, 40);
        i = 0;
        for (Cliente cliente : clientes) {
            if(!cliente.piscina){
                drawCliente(g, cliente, 380, 75+40*i);
                i++;
            }
        }
        
        og.drawImage(img, 0, 0, null);
    }
    
    public void drawCliente(Graphics g, Cliente c, int x, int y){
        g.setColor(c.palas?Color.blue:(c.aletas?Color.red:Color.black));
        g.setFont(fNumero);
        g.drawString(Long.toString(c.id), x, y);
    }
    
    private Cliente getCliente(){
        long id = Thread.currentThread().getId();
        return clientes.stream().filter(cliente -> cliente.id == id).findAny().get();
    }
}
