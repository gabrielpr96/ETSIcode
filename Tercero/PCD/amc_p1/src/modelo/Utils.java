package modelo;

public class Utils {
	public static double Distancia(Punto puntoA, Punto puntoB) {
		double distancia = Math.sqrt(
				  (puntoB.getX()-puntoA.getX())*(puntoB.getX()-puntoA.getX())
				+ (puntoB.getY()-puntoA.getY())*(puntoB.getY()-puntoA.getY())
				);
		return distancia<0?-distancia:distancia;
	}
	
	public static double Distancia(double a, double b) {
		double distancia = a-b;
		return distancia<0?-distancia:distancia;
	}
	
	public static double[] getMinXY(Punto[] puntos) {
		double[] xy = new double[2];
		xy[0] = puntos[0].getX();
		xy[1] = puntos[0].getY();
		for (int i=1;i<puntos.length;i++) {
			if(puntos[i].getX()<xy[0]) {
				xy[0] = puntos[i].getX();
			}
			if(puntos[i].getY()<xy[1]) {
				xy[1] = puntos[i].getY();
			}
		}
		return xy;
	}
	
	public static double[] getMaxXY(Punto[] puntos) {
		double[] xy = new double[2];
		xy[0] = puntos[0].getX();
		xy[1] = puntos[0].getY();
		for (int i=1;i<puntos.length;i++) {
			if(puntos[i].getX()>xy[0]) {
				xy[0] = puntos[i].getX();
			}
			if(puntos[i].getY()>xy[1]) {
				xy[1] = puntos[i].getY();
			}
		}
		return xy;
	}
}
