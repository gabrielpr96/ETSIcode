package modelo;

import java.util.Arrays;

public class Alg_DyV {
	public static Triangulo Buscar(Punto[] puntos) {
		puntos = quick_sort(puntos);
		Triangulo tri = DyV(puntos, 0, puntos.length-1);
		return tri;
	}
	public static Triangulo DyV(Punto[] T, final int i, final int d) {
		if(d-i+1 <= 6) {
			return Alg_Exhaustivo.Buscar(Arrays.copyOfRange(T, i, d+1));
		}
		int m = (i+d)/2;
		Triangulo ti = DyV(T, i, m);
		Triangulo td = DyV(T, m+1, d);
		Triangulo tmin = td;
		if(ti.Perimetro() < td.Perimetro() || (ti.Perimetro() == td.Perimetro() && ti.Area()>td.Area())) {
			tmin = ti;
		}
		int x = 0;
		for(x = m+1;x<=d;x++) {
			if(T[x].getX()-T[m].getX()>tmin.Perimetro()) {
				break;
			}
		}
		int y = 0;
		for(y = m;y>=i;y--) {
			if(T[m+1].getX()-T[y].getX()>tmin.Perimetro()) {
				break;
			}
		}
		Triangulo aux = null;
		for (int si = y+1;si <= m;si++)  {
			for(int sj = m+1;sj<=x-1;sj++) {
				for(int sk = sj+1;sk<=x-1;sk++) {
					aux = new Triangulo(T[si],T[sj],T[sk]);
					if(aux.Perimetro()<tmin.Perimetro() || (aux.Perimetro() == tmin.Perimetro() && aux.Area()>tmin.Area())) {
						tmin = aux;
					}
				}
			}
		}
		for (int sii = m+1;sii <= x-1;sii++)  {
			for(int sjj = y+1;sjj <= m;sjj++) {
				for(int skk = sjj+1;skk <= m;skk++) {
					aux = new Triangulo(T[sii],T[sjj],T[skk]);
					if(aux.Perimetro()<tmin.Perimetro() || (aux.Perimetro() == tmin.Perimetro() && aux.Area()>tmin.Area())) {
						tmin = aux;
					}
				}
			}
		}
		return tmin;
	}

	public static Punto[] quick_sort(Punto[] puntos) {
		quick_sort(puntos,0,puntos.length-1);
		return puntos;
	}
	public static void quick_sort(Punto[] puntos,final int e, final int d) {
		if(e<d) {
			int q = partition(puntos,e,d);
			quick_sort(puntos,e,q);
			quick_sort(puntos,q+1,d);
		}
	}
	public static int partition (Punto[] T,final int e, final int d) {
		double x = T[e].getX(); int i = e-1; int j = d+1;
		for(;;) {
			while (x < T[--j].getX());
			while (T[++i].getX() < x);
			if (i>=j) return j;
			swap(T,i,j);
		}
	}
	public static void swap(Punto[] T, final int i, final int j) {
		Punto aux =  T[i];
		T[i] = T[j];
		T[j] = aux;
	}
	
}
