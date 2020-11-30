package modelo;

public class Punto {
	private int n;
	private double x;
	private double y;
	
	public Punto(int n,double x,double y) {
		this.n = n;
		this.x = x;
		this.y = y;
	}
	
	public static Punto getPunto(int n, Punto[] puntos) {
		Punto punto = null;
		for(int i=0;i<puntos.length;i++) {
			if(puntos[i].getN() == n) {
				punto = puntos[i];
				break;
			}
		}
		return punto;
	}
	
	public String toString() {
		return "Nº:" + n + " X:" + x + " Y:" + y;
	}
	
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
}
