/* 
 * Clase Graficas, contiene métodos crear los ficheros de comandos para dibujar 
 * la gráfica que corresponda. 
 */
#include "Graficas.h"
#include "Constantes.h"
#include <fstream>
#include <iostream>
using namespace std;

/*
 * Método generarGraficaMEDIO, genera el fichero de comandos para GNUPlot 
 * y dibuja la gráfica del caso medio de un método de
 * ordenación y su ajuste a la función correspondiente.  
 * param nombre_metodo: metodo de ordenacion.
 * param orden: Orden del metodo de ordenacion.
 */
void Graficas::generarGraficaMEDIO(string nombre_metodo,int orden)
{
	ofstream file("grafica.gpl");
	file << "#Archivo auto-generado, cortesia de B0vE\n";

	file << "set title \"Tiempo de ordenación por " << nombre_metodo << "\"\n"
		<< "set key top left vertical inside\n"
		<< "set grid\n"
		<< "set xlabel \"Talla (n)\"\n"
		<< "set ylabel \"Tiempo(ms)\"\n"
		<< "\n"
		<< "N(x) = ";

	switch (orden) {
		case N:			file << "a*x + b";	break;
		case CUADRADO:	file << "a*x*x + b*x + c";	break;
		case NlogN:		file << "a*x*(log(x)/log(2))"; break;
		case AXpB:		file << "a*x + b";	break;
		case AX3t2pB:	file << "a*sqrt(x*x*x) + b"; break;
		case log2N:		file << "a+ b*(log(x)/log(2))"; break;
		case log3N:		file << "a+ b*(log(x)/log(3))"; break;
	}
	file << "\nfit N(x) \"t" << nombre_metodo << ".dat\" using 1:2 via ";
	switch (orden) {
		case CUADRADO:	file << "a,b,c"; break;
		case NlogN:		file << "a";	break;
		case log2N:
		case log3N:
		case N:
		case AXpB:
		case AX3t2pB:		file << "a,b";	break;
	}

	file << "\nplot N(x) title \"Aproximación\", \"t" << nombre_metodo << ".dat\" using 1:2 title \"Resultado empírico\"\n"
		 << "\n"
		 << "set terminal pdf\n"
		 << "set output \"" << nombre_metodo << ".pdf\"\n"
		 << "replot\n"
		 << "#pause -1 \"Pulsa Enter para continuar...\"\n"
		 << "unset output";

	file.close();
}	

/*
 * Método generarGraficaCMP, genera el fichero de comandos para GNUPlot.
 * param nombre1: es el nombre del fichero de datos del primer método de ordenación 
 * param nombre2: es el nombre del fichero de datos del segundo método de ordenación 
 */
void  Graficas::generarGraficaCMP(string nombre1,string nombre2)
{
	ofstream file("grafica.gpl");
	file << "#Archivo auto-generado, cortesia de B0vE\n";

	file << "set title \"Comparativa " << nombre1 << " con " << nombre2 << "\"\n"
		<< "set key top left vertical inside\n"
		<< "set grid\n"
		<< "set xlabel \"Talla (n)\"\n"
		<< "set ylabel \"Tiempo(ms)\"\n"
		<< "\n";

	file << "plot \"t" << nombre1 << nombre2 << ".dat\" using 1:2 with lines title \""<< nombre1 <<"\", \"t"
		<< nombre1 << nombre2 << ".dat\" using 1:3 with lines title \"" << nombre2 << "\"\n"
		<< "\n"
		<< "set terminal pdf\n"
		<< "set output \"" << nombre1 << nombre2 << ".pdf\"\n"
		<< "replot\n"
		<< "#pause -1 \"Pulsa Enter para continuar...\"\n"
		<< "unset output";

	file.close();
}

void  Graficas::generarGraficaCMPtodos(vector<string> nombres)
{
	ofstream file("grafica.gpl");
	file << "#Archivo auto-generado, cortesia de B0vE\n";

	file << "set title \"Comparativa de todos los algoritmos\"\n"
		<< "set key top left vertical inside\n"
		<< "set grid\n"
		<< "set xlabel \"Talla (n)\"\n"
		<< "set ylabel \"Tiempo(ms)\"\n"
		<< "\n";

	file << "plot ";
	for (int algoritmo = 0; algoritmo < nombres.size(); algoritmo++)
		file << "\"tTodos.dat\" using 1:" << (algoritmo + 2) << " with lines title \"" << nombres[algoritmo] << "\"" << (algoritmo == nombres.size()-1?"\n":", ");
	file << "\n"
		<< "set terminal pdf\n"
		<< "set output \"ComparativaTodos.pdf\"\n"
		<< "replot\n"
		<< "#pause -1 \"Pulsa Enter para continuar...\"\n"
		<< "unset output";

	file.close();
}