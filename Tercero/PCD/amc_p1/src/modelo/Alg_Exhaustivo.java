package modelo;

public class Alg_Exhaustivo {
	public static Triangulo Buscar(Punto[] puntos) {
		Triangulo triangulo, aux;
		triangulo = new Triangulo(puntos[0],puntos[1],puntos[2]);
		for(int i = 0;i<puntos.length;i++) {
			for(int j = i+1;j<puntos.length;j++) {
				for(int k = j+1;k<puntos.length;k++) {
					aux = new Triangulo(puntos[i],puntos[j],puntos[k]);
					if(aux.Perimetro()<triangulo.Perimetro() || (aux.Perimetro() 
							== triangulo.Perimetro() && aux.Area()>triangulo.Area())) {
						triangulo = aux;
					}
				}
			}
		}
		return triangulo;
	}
}
