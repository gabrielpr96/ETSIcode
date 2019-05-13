/* 
 * La clase TestBusqueda contiene los metodos para:
 * 1. Comprobar que los métodos de búsqueda de la clase AlgoritmosBusqueda funcionan adecuadamente.
 * 2. Calcular la eficiencia para el caso medio de un método de búsqueda,
 *    permitiendo guardar los datos e imprimir la gráfica correspondiente con ajuste al Orden de complejidad.
 * 3. Comparar el coste temporal de dos métodos de búsqueda
 *    permitiendo guardar los datos e imprimir la gráfica correspondiente.
 */
#include "TestBusqueda.h"
#include "Constantes.h"
#include "AlgoritmosBusqueda.h"
#include "AlgoritmosOrdenacion.h"
#include "ConjuntoInt.h"
#include "Graficas.h"
#include "Mtime.h"
#include <string>
#include <fstream>
#include <iomanip>
#include <iostream>
using namespace std;

TestBusqueda::TestBusqueda()
{
	nombreAlgoritmo.push_back("SecuencialI");
	complegidadAlgoritmo.push_back(N);
	nombreAlgoritmo.push_back("BinariaR");
	complegidadAlgoritmo.push_back(log2N);
	nombreAlgoritmo.push_back("TernariaR");
	complegidadAlgoritmo.push_back(log3N);
} 
TestBusqueda::~TestBusqueda()
{
}

/*
 * Busca en un array de enteros según el método indicado
 * param v: el array de enteros a ordenar
 * param size: tamaño del array de enteros a ordenar
 * param metodo: Metodo de búsqueda a utilizar
 * return Tiempo empleado en la busqueda (en milisegundos)
 */
double TestBusqueda::buscaEnArrayDeInt(int key,int v[],int size,int metodo,int &posicion) 
{
    AlgoritmosBusqueda strategia;
	Mtime t;
	LARGE_INTEGER t_inicial, t_final;
	// Invoca al método de búsqueda elegido
	QueryPerformanceCounter(&t_inicial);
    switch (metodo)	{
		case SECUENCIALIt: posicion=strategia.busquedaSecuencialIt(v, size, key);
            break;
		case BINARIARc: posicion=strategia.busquedaBinariaRc(v, size, key); 
            break;
		case TERNARIARc: posicion=strategia.busquedaTernariaRc(v, size, key); 
            break;
	}
	QueryPerformanceCounter(&t_final);
    return t.performancecounter_diff(&t_final, &t_inicial) * 1000000;   
}

/*
 * Comprueba los metodos de búsqueda
 */
void TestBusqueda::comprobarMetodosBusqueda(){
	int talla, clave, pos;
	cout << endl << endl << "Introduce la talla: ";
	cin >> talla;

	int antiguoCaso = ConjuntoInt::getCaso();
	ConjuntoInt::setCaso(MEJOR);/// De lo contrario la busqueda binaria no funcionaria
	ConjuntoInt *v = new ConjuntoInt(talla);
	v->GeneraVector(talla);
	cout << endl << endl << "Vector aleatorio: " << endl << endl;
	v->escribe();

	cout << endl << endl << "Introduce la clave de busqueda: ";
	cin >> clave;
	system("cls");
	for (int metodo = 0; metodo < nombreAlgoritmo.size(); metodo++)
	{
		double secs = buscaEnArrayDeInt(clave, v->getDatos(), talla, metodo, pos);
		cout << endl << endl << "Tiempo empleado por " << nombreAlgoritmo[metodo] << ": " << secs << endl;
		if (pos == -1)
			cout << "La clave no se encuentra en el vector.";
		else cout << "Posicion de la clave: " << pos+1;
		cout << endl << endl;
	} /* fin del for */
	delete v;
	ConjuntoInt::setCaso(antiguoCaso);
	system("pause");
	system("cls");
}
    
/*
 * Compara dos metodos de búsqueda. 
 * Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
 * param metodo1: Primer metodo de búsqueda a comparar
 * param metodo2: Segundo metodo de búsqueda a comparar
 */
void TestBusqueda::comparar(int metodo1, int metodo2) 
{
	cout << "\t  Comparativa del algoritmo " << nombreAlgoritmo[metodo1] << " y " << nombreAlgoritmo[metodo2] << "\n\n"
		<< "\t             \t\t       \t      TIEMPO (ms)\n"
		<< "\t       TALLA \t\t       \t" << nombreAlgoritmo[metodo1] << "  \t" << nombreAlgoritmo[metodo2] << "\n";

	ofstream file("t" + nombreAlgoritmo[metodo1] + nombreAlgoritmo[metodo2] + ".dat");
	if (file.fail())
		cout << "Error al abrir al crear el archivo.\nNo se guardaran los datos.\n";

	ConjuntoInt::setCaso(MEJOR);
	double tiempo1, tiempo2; int tmp;
	for (int talla = TALLA_INI; talla <= TALLA_FIN; talla += TALLA_DELTA) {
		ConjuntoInt *v = new ConjuntoInt(talla);

		tiempo1 = 0;
		tiempo2 = 0;
		for (int i = 0; i < REPETICIONES*5; i++) {
			v->GeneraVector(talla);
			tiempo1 += buscaEnArrayDeInt(v->GeneraKey(), v->getDatos(), talla, metodo1, tmp);
			v->GeneraVector(talla);
			tiempo2 += buscaEnArrayDeInt(v->GeneraKey(), v->getDatos(), talla, metodo2, tmp);
			v->vaciar();
		}
		tiempo1 /= REPETICIONES*5;
		tiempo2 /= REPETICIONES*5;

		delete v;


		//Mostrar los datos
		cout.precision(4);
		cout << "\t\t" << talla << "\t\t    " << setw(10) << fixed << setprecision(4) << tiempo1
			<< "\t     " << setw(10) << fixed << setprecision(4) << tiempo2 << "\n";
		if(talla != TALLA_INI)
			file << talla << "\t" << tiempo1 << "\t" << tiempo2 << "\n";
	}
	file.close();

	//Generar grafica
	char opt;
	cout << "\nGenerar grafica (s, n): ";
	cin >> opt;
	if (opt == 's' || opt == 'S') {
		Graficas g;
		g.generarGraficaCMP(nombreAlgoritmo[metodo1], nombreAlgoritmo[metodo2]);
		cout << "La grafica fue generada.\n\n";
		system("start grafica.gpl");
	} else cout << "No se generara la grafica.\n\n";

	system("pause");
}
/*
 * Calcula el caso medio de un metodo de búsqueda,
 * Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
 * param metodo: metodo de búsqueda a estudiar.
 */
void TestBusqueda::casoMedio(int metodo)
{
	cout << "\t  Medicion del algoritmo " << nombreAlgoritmo[metodo] << "\n"
		<< "\t       TALLA \t       TIEMPO (ms)\n";

	ofstream file("t" + nombreAlgoritmo[metodo] + ".dat");
	if (file.fail())
		cout << "Error al abrir al crear el archivo.\nNo se guardaran los datos.\n";

	ConjuntoInt::setCaso(MEJOR);
	double tiempo; int tmp;
	for (int talla = TALLA_INI; talla <= TALLA_FIN; talla += TALLA_DELTA) {
		ConjuntoInt *v = new ConjuntoInt(talla);

		tiempo = 0;
		for (int i = 0; i < REPETICIONES*5; i++) {
			v->GeneraVector(talla);
			tiempo += buscaEnArrayDeInt(v->GeneraKey() ,v->getDatos(), talla, metodo, tmp);
			v->vaciar();
		}
		tiempo /= REPETICIONES*5;

		delete v;


		//Mostrar los datos
		cout << "\t\t" << talla << "\t     " << setw(10) << fixed << setprecision(4) << tiempo << "\n"; ///TODO: Poner el setw, fixed y setpresition 4 a todo.
		if (talla != TALLA_INI)
			file << talla << "\t" << tiempo << "\n";
	}
	file.close();

	//Generar grafica
	char opt;
	cout << "\nGenerar grafica (s, n): ";
	cin >> opt;
	if (opt == 's' || opt == 'S') {
		Graficas g;
		g.generarGraficaMEDIO(nombreAlgoritmo[metodo], complegidadAlgoritmo[metodo]);
		cout << "La grafica fue generada.\n\n";
		system("start grafica.gpl");
	} else cout << "No se generara la grafica.\n\n";

	system("pause");
}	

void TestBusqueda::compararTodos()
{
	cout << "\t  Comparativa de todos los algoritmos \t"
		<< "\n\n"
		<< "             \t\t       \t      TIEMPO (ms)\n"
		<< "       TALLA \t\t       \t";
	for (int algoritmo = 0; algoritmo < nombreAlgoritmo.size(); algoritmo++)
		cout << nombreAlgoritmo[algoritmo] << "  \t";
	cout << "\n";

	ofstream file("tTodos.dat");
	if (file.fail())
		cout << "Error al abrir al crear el archivo.\nNo se guardaran los datos.\n";

	ConjuntoInt::setCaso(MEJOR);
	double *tiempo = new double[nombreAlgoritmo.size()]; int tmp;
	for (int talla = TALLA_INI/10; talla <= TALLA_FIN/10; talla += TALLA_DELTA/10) {
		ConjuntoInt *v = new ConjuntoInt(talla);

		for (int algoritmo = 0; algoritmo < nombreAlgoritmo.size(); algoritmo++) {
			tiempo[algoritmo] = 0;
			for (int i = 0; i < REPETICIONES*10; i++) {
				v->GeneraVector(talla);
				tiempo[algoritmo] += buscaEnArrayDeInt(v->GeneraKey(), v->getDatos(), talla, algoritmo, tmp);
				v->vaciar();
			}
			tiempo[algoritmo] /= REPETICIONES*10;
		}

		delete v;


		//Mostrar los datos
		cout.precision(4);
		cout << "\t" << talla << "\t";
		for (int algoritmo = 0; algoritmo < nombreAlgoritmo.size(); algoritmo++)
			cout << "\t    " << setw(10) << fixed << setprecision(4) << tiempo[algoritmo];
		cout << "\n";
		if (talla != TALLA_INI/10) {
			file << talla;
			for (int algoritmo = 0; algoritmo < nombreAlgoritmo.size(); algoritmo++)
				file << "\t" << tiempo[algoritmo];
			file << "\n";
		}
	}
	file.close();

	//Generar grafica
	char opt;
	cout << "\nGenerar grafica (s, n): ";
	cin >> opt;
	if (opt == 's' || opt == 'S') {
		Graficas g;
		g.generarGraficaCMPtodos(nombreAlgoritmo);
		cout << "La grafica fue generada.\n\n";
		system("start grafica.gpl");
	} else cout << "No se generara la grafica.\n\n";

	system("pause");
}