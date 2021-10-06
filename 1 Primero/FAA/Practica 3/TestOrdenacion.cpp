/* 
 * La clase TestOrdenacion contiene los metodos para:
 * 1. Comprobar que los m�todos de ordenacion de la clase Ordenacion funcionan adecuadamente.
 * 2. Calcular la eficiencia para el caso medio de un m�todo de ordenaci�n,
 *    guardando los datos y permitiendo imprimir la gr�fica correspondiente 
 * 3. Comparar el coste temporal de dos de los m�todos de ordenaci�n 
 *    burbuja, inserci�n, y selecci�n, guardando los datos y permitiendo imprimir la gr�fica correspondiente.
 */
#include "AlgoritmosOrdenacion.h"
#include "TestOrdenacion.h"
#include "ConjuntoInt.h"
#include "Graficas.h"
#include "Mtime.h"
#include "Constantes.h"
#include <string>
#include <fstream>
#include <iomanip>
#include <iostream>
using namespace std;


TestOrdenacion::TestOrdenacion()
{
	nombreAlgoritmo.push_back("Burbuja");
	complegidadAlgoritmo.push_back(CUADRADO);
	nombreAlgoritmo.push_back("Insercion");
	complegidadAlgoritmo.push_back(CUADRADO);
	nombreAlgoritmo.push_back("Seleccion");
	complegidadAlgoritmo.push_back(CUADRADO);
	nombreAlgoritmo.push_back("Shell");
	complegidadAlgoritmo.push_back(AX3t2pB);
	nombreAlgoritmo.push_back("Mergesort");
	complegidadAlgoritmo.push_back(NlogN);
	nombreAlgoritmo.push_back("Quicksort");
	complegidadAlgoritmo.push_back(NlogN);
	nombreAlgoritmo.push_back("ShakeSort");
	complegidadAlgoritmo.push_back(CUADRADO);
	nombreAlgoritmo.push_back("BrickSort");
	complegidadAlgoritmo.push_back(CUADRADO);

	srand((unsigned)time(NULL)); //srand(time(0));
} 
TestOrdenacion::~TestOrdenacion(){}

/*
 * Ordena un array de enteros seg�n el m�todo indicado
 * param v: el array de enteros a ordenar
 * param size: tama�o del array de enteros a ordenar
 * param metodo: Metodo de ordenacion a utilizar
 * return Tiempo empleado en la ordenaci�n (en milisegundos)
 */
double TestOrdenacion::ordenarArrayDeInt(int v[],int size,int metodo) 
{
	AlgoritmosOrdenacion estrategia;
	Mtime t;
	LARGE_INTEGER t_inicial, t_final;
	QueryPerformanceCounter(&t_inicial);
	// Invoca al m�todo de ordenaci�n elegido
	switch (metodo){
		case BURBUJA: estrategia.ordenaBurbuja(v, size);
			break;
		case INSERCION: estrategia.ordenaInsercion(v, size);
			break;
		case SELECCION: estrategia.ordenaSeleccion(v, size);
			break;
		case SHELL: estrategia.ordenaShell(v, size);
			break;
		case MERGESORT: estrategia.ordenaMergesort(v, size);
			break;
		case QUICKSORT: estrategia.ordenaQuicksort(v, size);
			break;
		case SHAKESORT: estrategia.ordenaShakesort(v, size);
			break;
		case BRICKSORT: estrategia.ordenaBricksort(v, size);
			break;
	}
	QueryPerformanceCounter(&t_final);
	return t.performancecounter_diff(&t_final, &t_inicial) * 1000;   
}

/*
 * Comprueba los metodos de ordenacion
 */
void TestOrdenacion::comprobarMetodosOrdenacion()
{
	int talla;
	cout<<endl<<endl<<"Introduce la talla: ";
	cin>>talla; 
	system("cls"); 
	for (int metodo = 0; metodo < nombreAlgoritmo.size(); metodo++)
	{
		ConjuntoInt *v= new ConjuntoInt(talla); 
		v->GeneraVector(talla);
		cout <<endl<<endl<< "vector inicial para el metodo "<<nombreAlgoritmo[metodo]<< ":"<<endl<<endl;
		v->escribe();
		double secs=ordenarArrayDeInt(v->getDatos(),talla,metodo);
		cout<<endl<<endl<<"Array ordenado con metodo "<<nombreAlgoritmo[metodo]<< ":"<<endl<<endl;
		v->escribe();
		cout<<endl;
		v->vaciar(); 
		delete v;
		system("pause");
		system("cls");
	} /* fin del for */
	system("cls");
}
    
/*
 * Calcula el caso medio de un metodo de ordenacion,
 * Permite las opciones de crear el fichero de datos y la gr�fica correspondiente.
 * param metodo: metodo de ordenacion a estudiar.
 */
void TestOrdenacion::casoMedio(int metodo)
{
	cout << "\t  Medicion del algoritmo " << nombreAlgoritmo[metodo] << "\n"
		<< "\t       TALLA \t       TIEMPO (ms)\n";

	ofstream file("t"+nombreAlgoritmo[metodo] + ".dat");
	if (file.fail())
		cout << "Error al abrir al crear el archivo.\nNo se guardaran los datos.\n";

	double tiempo;
	for (int talla = TALLA_INI; talla <= TALLA_FIN; talla += TALLA_DELTA) {
		ConjuntoInt *v = new ConjuntoInt(talla);

		tiempo = 0;
		for (int i = 0; i < REPETICIONES; i++) {
			v->GeneraVector(talla);
			tiempo += ordenarArrayDeInt(v->getDatos(), talla, metodo);
			v->vaciar();
		}
		tiempo /= REPETICIONES;

		delete v;


		//Mostrar los datos
		cout << "\t\t" << talla << "\t     " << setw(10)<<fixed<<setprecision(4) << tiempo << "\n"; ///TODO: Poner el setw, fixed y setpresition 4 a todo.
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
	}else cout << "No se generara la grafica.\n\n";

	system("pause");
}
/*
 * Compara dos metodos de ordenacion. 
 * Genera el fichero de datos y permite las opcion de crear la gr�fica correspondiente.
 * param metodo1: Primer metodo de ordenacion a comparar
 * param metodo2: Segundo metodo de ordenacion a comparar
 */
void TestOrdenacion::comparar(int metodo1, int metodo2) {
	cout << "\t  Comparativa del algoritmo " << nombreAlgoritmo[metodo1] << " y " << nombreAlgoritmo[metodo2] << "\n\n"
		<< "\t             \t\t       \t      TIEMPO (ms)\n"
		<< "\t       TALLA \t\t       \t" << nombreAlgoritmo[metodo1] << "  \t" << nombreAlgoritmo[metodo2] << "\n";

	ofstream file("t" + nombreAlgoritmo[metodo1] + nombreAlgoritmo[metodo2] + ".dat");
	if (file.fail())
		cout << "Error al abrir al crear el archivo.\nNo se guardaran los datos.\n";

	double tiempo1, tiempo2;
	for (int talla = TALLA_INI; talla <= TALLA_FIN; talla += TALLA_DELTA) {
		ConjuntoInt *v = new ConjuntoInt(talla);

		tiempo1 = 0;
		tiempo2 = 0;
		for (int i = 0; i < REPETICIONES; i++) {
			v->GeneraVector(talla);
			tiempo1 += ordenarArrayDeInt(v->getDatos(), talla, metodo1);
			v->GeneraVector(talla);
			tiempo2 += ordenarArrayDeInt(v->getDatos(), talla, metodo2);
			v->vaciar();
		}
		tiempo1 /= REPETICIONES;
		tiempo2 /= REPETICIONES;

		delete v;


		//Mostrar los datos
		cout.precision(4);
		cout << "\t\t" << talla << "\t\t    "<< setw(10) << fixed << setprecision(4) << tiempo1
			 << "\t     " << setw(10) << fixed << setprecision(4) << tiempo2 << "\n";
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
	}else cout << "No se generara la grafica.\n\n";

	system("pause");
}

void TestOrdenacion::compararTodos() {
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
	for (int talla = TALLA_INI; talla <= TALLA_FIN; talla += TALLA_DELTA) {
		ConjuntoInt *v = new ConjuntoInt(talla);

		for (int algoritmo = 0; algoritmo < nombreAlgoritmo.size(); algoritmo++) {
			tiempo[algoritmo] = 0;
			for (int i = 0; i < REPETICIONES; i++) {
				v->GeneraVector(talla);
				tiempo[algoritmo] += ordenarArrayDeInt(v->getDatos(), talla, algoritmo);
				v->vaciar();
			}
			tiempo[algoritmo] /= REPETICIONES;
		}

		delete v;


		//Mostrar los datos
		cout.precision(4);
		cout << "\t\t" << talla << "\t";
		for (int algoritmo = 0; algoritmo < nombreAlgoritmo.size(); algoritmo++)
			cout << "\t    " << setw(10) << fixed << setprecision(4) << tiempo[algoritmo];
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
