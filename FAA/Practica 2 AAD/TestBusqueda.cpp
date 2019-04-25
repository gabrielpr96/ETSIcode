/*
 * La clase TestBusqueda contiene los metodos para:
 * 1. Comprobar que los métodos de ordenacion de la clase Busqueda funcionan adecuadamente.
 * 2. Calcular la eficiencia para el caso medio de un método de ordenación,
 *    guardando los datos y permitiendo imprimir la gráfica correspondiente
 * 3. Comparar el coste temporal de dos de los métodos de busqueda
 *    burbuja, inserción, y selección, guardando los datos y permitiendo imprimir la gráfica correspondiente.
 */
#include "AlgoritmosBusqueda.h"
#include "TestBusqueda.h"
#include "ConjuntoInt.h"
#include "Graficas.h"
#include "Mtime.h"
#include "Constantes.h"
#include <string>
#include <fstream>
#include <iomanip>
#include <iostream>
using namespace std;


TestBusqueda::TestBusqueda()
{
	nombreAlgoritmo.push_back("Lineal");
	complegidadAlgoritmo.push_back(AXpB);
	nombreAlgoritmo.push_back("Binario");
	complegidadAlgoritmo.push_back(logN);

	srand((unsigned)time(NULL)); //srand(time(0));
}
TestBusqueda::~TestBusqueda() {}

/*
	 * Busca una clave en un array de enteros según el método indicado
	 * param v: el array de enteros a ordenar
	 * param size: tamaño del array de enteros a ordenar
	 * param key: clave a buscar en el array de enteros
	 * param metodo: Metodo de ordenacion a utilizar
	 * param pos: Variable en la que escribe pa posicion de la clave
	 * return: Tiempo empleado en la busqueda (en milisegundos)
	 */
double TestBusqueda::buscarClave(int v[], int size, int key, int metodo, int &pos)
{
	AlgoritmosBusqueda estrategia;
	Mtime t;
	LARGE_INTEGER t_inicial, t_final;
	QueryPerformanceCounter(&t_inicial);
	// Invoca al método de busqueda elegido
	switch (metodo) {
	case LINEAL: pos = estrategia.buscaLineal(v, size, key);
		break;
	case BINARIA: pos = estrategia.buscaBinaria(v, size, key);
		break;
	}
	QueryPerformanceCounter(&t_final);
	return t.performancecounter_diff(&t_final, &t_inicial) * 1000;
}

/*
 * Comprueba los metodos de busqueda
 */
void TestBusqueda::comprobarMetodosBusqueda()
{
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
		double secs = buscarClave(v->getDatos(), talla, clave, metodo, pos);
		cout << endl << endl << "Tiempo empleado por " << nombreAlgoritmo[metodo] <<": " << secs << endl;
		if (pos == -1)
			cout << "La clave no se encuentra en el vector.";
		else cout << "Posicion de la clave: " << pos;
		cout << endl << endl;
	} /* fin del for */
	delete v;
	ConjuntoInt::setCaso(antiguoCaso);
	system("pause");
	system("cls");
}

/*
 * Calcula el caso medio de un metodo de busqueda,
 * Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
 * param metodo: metodo de busqueda a estudiar.
 */
void TestBusqueda::casoMedio(int metodo)
{
	cout << "\t  Medicion del algoritmo de busqueda " << nombreAlgoritmo[metodo] << "\n"
		<< "\t       TALLA \t       TIEMPO (ms)\n";

	ofstream file("t" + nombreAlgoritmo[metodo] + ".dat");
	if (file.fail())
		cout << "Error al abrir al crear el archivo.\nNo se guardaran los datos.\n";
	cout << "METOD: " << metodo << "\n";
	double tiempo;
	int pos;
	///Control del orden, para el metodo binario
	int casoAntiguo = ConjuntoInt::getCaso();
	if (metodo == BINARIA) {
		ConjuntoInt::setCaso(MEJOR);
	}
	for (int talla = TALLA_INI; talla <= TALLA_FIN; talla += TALLA_DELTA) {
		ConjuntoInt *v = new ConjuntoInt(talla);

		tiempo = 0;
		for (int i = 0; i < REPETICIONES; i++) {
			v->GeneraVector(talla);
			tiempo += buscarClave(v->getDatos(), talla, v->GeneraKey(), metodo, pos);
			v->vaciar();
		}
		tiempo /= REPETICIONES;

		delete v;


		//Mostrar los datos
		cout << "\t\t" << talla << "\t\t" << tiempo << "\n";
		file << talla << "\t" << tiempo << "\n";
	}
	file.close();
	///Restaurar el caso anteiror
	ConjuntoInt::setCaso(casoAntiguo);

	//Generar grafica
	char opt;
	cout << "\nGenerar grafica (s, n): ";
	cin >> opt;
	if (opt == 's' || opt == 'S') {
		Graficas g;
		g.generarGraficaMEDIO(nombreAlgoritmo[metodo], complegidadAlgoritmo[metodo]);
		cout << "La grafica fue generada.\n\n";
		system("start grafica.gpl");
	}
	else cout << "No se generara la grafica.\n\n";

	system("pause");
}
/*
 * Compara dos metodos de busqueda.
 * Genera el fichero de datos y permite las opcion de crear la gráfica correspondiente.
 * param metodo1: Primer metodo de busqueda a comparar
 * param metodo2: Segundo metodo de busqueda a comparar
 */
void TestBusqueda::comparar(int metodo1, int metodo2) {
	cout << "\t  Comparativa del algoritmo " << nombreAlgoritmo[metodo1] << " y " << nombreAlgoritmo[metodo2] << "\n\n"
		<< "\t             \t\t       \t      TIEMPO (ms)\n"
		<< "\t       TALLA \t\t       \t" << nombreAlgoritmo[metodo1] << "  \t" << nombreAlgoritmo[metodo2] << "\n";

	ofstream file("t" + nombreAlgoritmo[metodo1] + nombreAlgoritmo[metodo2] + ".dat");
	if (file.fail())
		cout << "Error al abrir al crear el archivo.\nNo se guardaran los datos.\n";

	double tiempo1, tiempo2;
	int pos;
	for (int talla = TALLA_INI; talla <= TALLA_FIN; talla += TALLA_DELTA) {
		ConjuntoInt *v = new ConjuntoInt(talla);

		tiempo1 = 0;
		tiempo2 = 0;
		for (int i = 0; i < REPETICIONES; i++) {
			v->GeneraVector(talla);
			tiempo1 += buscarClave(v->getDatos(), talla, v->GeneraKey(), metodo1, pos);
			v->GeneraVector(talla);
			tiempo2 += buscarClave(v->getDatos(), talla, v->GeneraKey(), metodo2, pos);
			v->vaciar();
		}
		tiempo1 /= REPETICIONES;
		tiempo2 /= REPETICIONES;

		delete v;


		//Mostrar los datos
		cout.precision(4);
		cout << "\t\t" << talla << "\t\t\t" << tiempo1 << "\t\t " << tiempo2 << "\n";
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
	}
	else cout << "No se generara la grafica.\n\n";

	system("pause");
}

void TestBusqueda::compararTodos() {
	///Para que este se vea bonito es recomendable cambiar la constante REPETICIONES a 1000
	cout << "\t  Comparativa de todos los algoritmos \t"
		<< "\n\n"
		<< "\t             \t\t       \t      TIEMPO (ms)\n"
		<< "\t       TALLA \t\t       \t";
	for (int algoritmo = 0; algoritmo < nombreAlgoritmo.size(); algoritmo++)
		cout << nombreAlgoritmo[algoritmo] << "  \t";
	cout << "\n";

	ofstream file("tTodos.dat");
	if (file.fail())
		cout << "Error al abrir al crear el archivo.\nNo se guardaran los datos.\n";

	double *tiempo = new double[nombreAlgoritmo.size()];
	int pos;
	for (int talla = TALLA_INI; talla <= TALLA_FIN; talla += TALLA_DELTA) {
		ConjuntoInt *v = new ConjuntoInt(talla);

		for (int algoritmo = 0; algoritmo < nombreAlgoritmo.size(); algoritmo++) {
			tiempo[algoritmo] = 0;
			for (int i = 0; i < REPETICIONES; i++) {
				v->GeneraVector(talla);
				tiempo[algoritmo] += buscarClave(v->getDatos(), talla, v->GeneraKey(), algoritmo, pos);
				v->vaciar();
			}
			tiempo[algoritmo] /= REPETICIONES;
		}

		delete v;


		//Mostrar los datos
		cout.precision(4);
		cout << "\t\t" << talla << "\t";
		for (int algoritmo = 0; algoritmo < nombreAlgoritmo.size(); algoritmo++)
			cout << "\t\t" << tiempo[algoritmo];
		cout << "\n";
		file << talla;
		for (int algoritmo = 0; algoritmo < nombreAlgoritmo.size(); algoritmo++)
			file << "\t" << tiempo[algoritmo];
		file << "\n";
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
	}
	else cout << "No se generara la grafica.\n\n";

	system("pause");
}
