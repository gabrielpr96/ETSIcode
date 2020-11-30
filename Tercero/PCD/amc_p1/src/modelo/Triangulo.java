package modelo;

public class Triangulo {
	Punto VerticeA;
	Punto VerticeB;
	Punto VerticeC;
	
	public Triangulo(Punto puntoA, Punto puntoB, Punto puntoC) {
		this.VerticeA = puntoA;
		this.VerticeB = puntoB;
		this.VerticeC = puntoC;
	}
	
	public String toString() {
		return "[Triangulo] Pto.A("+VerticeA+") - Pto.B("+VerticeB+") - Pto.C("+VerticeC+")";
	}
	
	public double Perimetro() {
		double perimetro = Utils.Distancia(VerticeA, VerticeB)+Utils.Distancia(VerticeB, VerticeC)+Utils.Distancia(VerticeC, VerticeA);
		return perimetro;
	}
	
	public double Area() {
		double area =  -(
				  (VerticeA.getX() * (VerticeB.getY() - VerticeC.getY()))
				+ (VerticeB.getX() * (VerticeC.getY() - VerticeA.getY()))
				+ (VerticeC.getX() * (VerticeA.getY() - VerticeB.getY()))
				)/2;
		return area;
	}
	
	public Punto getVerticeA() {
		return VerticeA;
	}
	public void setVerticeA(Punto verticeA) {
		VerticeA = verticeA;
	}
	public Punto getVerticeB() {
		return VerticeB;
	}
	public void setVerticeB(Punto verticeB) {
		VerticeB = verticeB;
	}
	public Punto getVerticeC() {
		return VerticeC;
	}
	public void setVerticeC(Punto verticeC) {
		VerticeC = verticeC;
	}
}
