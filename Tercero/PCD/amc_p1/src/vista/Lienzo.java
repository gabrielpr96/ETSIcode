package vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

import modelo.Punto;
import modelo.Triangulo;
import modelo.Utils;

public class Lienzo extends JPanel{
	private static final long serialVersionUID = 1L;
	boolean muestraPuntos;
	Punto[] puntos;
	Triangulo triangulo;
	String[] listaNodos;
	String[] bilistanodos;

	public Lienzo(Punto[] p) {
		puntos = p;
		muestraPuntos = false;
		triangulo = null;
		listaNodos = null;
		bilistanodos = null;
	}
	
	public void paintComponent(Graphics g) {
	    g.setColor(Color.black);
	    g.fillRect(0, 0, getWidth(), getHeight());
	    if(puntos!=null) {
	    	double[] escalaXY = getEscala();
	    	double[] minXY = Utils.getMinXY(puntos);
	    	Graphics2D gg = (Graphics2D)g;
	    	for(int i=0;i<puntos.length;i++) {
	    		gg.setColor(Color.yellow);
	    		Punto p = puntos[i];
	    		Ellipse2D.Double shape = new Ellipse2D.Double(((p.getX()-minXY[0])*escalaXY[0])+10, ((p.getY()-minXY[1])*escalaXY[1])+15, 4, 4);
	    		gg.fill(shape);
	    		if(muestraPuntos) {
		    		gg.setColor(Color.white);
		    		gg.drawString("[P:"+p.getN()+"]", (float)((p.getX()-minXY[0])*escalaXY[0])+10, (float)((p.getY()-minXY[1])*escalaXY[1])+10);
	    		}
	    	}
	    	gg.setColor(Color.green);
	    	if(triangulo!=null) {
		    	Line2D l = new Line2D.Double(
		    			((triangulo.getVerticeA().getX()-minXY[0])*escalaXY[0]+12), ((triangulo.getVerticeA().getY()-minXY[1])*escalaXY[1]+17),
		    			((triangulo.getVerticeB().getX()-minXY[0])*escalaXY[0]+12), ((triangulo.getVerticeB().getY()-minXY[1])*escalaXY[1]+17));
		    	gg.draw(l);
		    	l = new Line2D.Double(
		    			((triangulo.getVerticeB().getX()-minXY[0])*escalaXY[0]+12), ((triangulo.getVerticeB().getY()-minXY[1])*escalaXY[1]+17),
		    			((triangulo.getVerticeC().getX()-minXY[0])*escalaXY[0]+12), ((triangulo.getVerticeC().getY()-minXY[1])*escalaXY[1]+17));
		    	gg.draw(l);
		    	l = new Line2D.Double(
		    			((triangulo.getVerticeC().getX()-minXY[0])*escalaXY[0]+12), ((triangulo.getVerticeC().getY()-minXY[1])*escalaXY[1]+17),
		    			((triangulo.getVerticeA().getX()-minXY[0])*escalaXY[0]+12), ((triangulo.getVerticeA().getY()-minXY[1])*escalaXY[1]+17));
		    	gg.draw(l);
	    	}
	    	if(listaNodos!=null) {
	    		for(int j=0;j<listaNodos.length;j++) {
	    			String[] nums = listaNodos[j].split("#");
	    			Punto a = Punto.getPunto(Integer.parseInt(nums[0])+1, puntos);
	    			Punto b = Punto.getPunto(j+1, puntos);
	    			Line2D l = new Line2D.Double(
	    					((a.getX()-minXY[0])*escalaXY[0]+12), ((a.getY()-minXY[1])*escalaXY[1]+17),
			    			((b.getX()-minXY[0])*escalaXY[0]+12), ((b.getY()-minXY[1])*escalaXY[1]+17));
	    			gg.draw(l);
	    		}
	    	}
	    	if(bilistanodos!=null) {
	    		for(int k=0;k<bilistanodos.length-1;k++) {
	    			String[] nums = bilistanodos[k].split("#");
	    			Punto a = Punto.getPunto(Integer.parseInt(nums[0])+1, puntos);
	    			Punto b = Punto.getPunto(Integer.parseInt(nums[1])+1, puntos);
	    			Line2D l = new Line2D.Double(
	    					((a.getX()-minXY[0])*escalaXY[0]+12), ((a.getY()-minXY[1])*escalaXY[1]+17),
			    			((b.getX()-minXY[0])*escalaXY[0]+12), ((b.getY()-minXY[1])*escalaXY[1]+17));
	    			gg.draw(l);
	    		}
	    	}
	    }
	}
	
	public double[] getEscala() {
		double widthLi = getWidth()-40;
		double heigthLi = getHeight()-40;
		double[] maxXY = Utils.getMaxXY(puntos);
		double[] minXY = Utils.getMinXY(puntos);
		double distanciaX = Utils.Distancia(maxXY[0],minXY[0]);
		double distanciaY = Utils.Distancia(maxXY[1],minXY[1]);
		double[] escalaXY = new double[2];
		escalaXY[0] = widthLi/distanciaX;
		escalaXY[1] = heigthLi/distanciaY;
		return escalaXY;
	}
	
	public void cls () {
		Graphics g = getGraphics();
		g.setColor(Color.black);
	    g.fillRect(0, 0, getWidth(), getHeight());
	    puntos = null;
		triangulo = null;
		listaNodos = null;
		bilistanodos = null;
	    repaint();
	}
	
	public String[] getListaNodos() {
		return listaNodos;
	}

	public void setListaNodos(String[] listaNodos) {
		this.listaNodos = listaNodos;
	}
	
	public String[] getBilistanodos() {
		return bilistanodos;
	}

	public void setBilistanodos(String[] bilistanodos) {
		this.bilistanodos = bilistanodos;
	}
	
	public Punto[] getPuntos() {
		return puntos;
	}

	public void setPuntos(Punto[] puntos) {
		this.puntos = puntos;
	}

	public Triangulo getTriangulo() {
		return triangulo;
	}

	public void setTriangulo(Triangulo triangulo) {
		this.triangulo = triangulo;
	}
	
	public boolean getMuestraPuntos() {
		return muestraPuntos;
	}

	public void setMuestraPuntos(boolean muestraPuntos) {
		this.muestraPuntos = muestraPuntos;
	}
}
