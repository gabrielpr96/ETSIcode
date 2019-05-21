#include "TestOrdenacion.h"
#include "TestBusqueda.h"
#include "Constantes.h"
#include "ConjuntoInt.h"
#include <iostream>

using namespace std;

	/**********************************************************
	 ****************       OPERACIONES        ****************
	 ****												   ****
	 ****		--------       000      --------		   ****
	 ****					  Salir						   ****
	 ****												   ****
	 **********************************************************
	 ****************  OPERACIONES ORDENACION  ****************
	 ****												   ****
	 ****		--------	Tipo 11X	--------		   ****
	 ****		   X = 1 Probar los algoritmos 			   ****
	 ****		X = 2 Comparar todos los algoritmos		   ****
	 ****												   ****
	 ****		--------	Tipo 12X	--------		   ****
	 ****		        Medir el algitmo X				   ****
	 ****												   ****
	 ****		--------	Tipo 2XY	--------		   ****
	 ****		  Comparar el algoritmo X con el Y		   ****
	 ****												   ****
	 **********************************************************
	 ****************   OPERACIONES BUSQUEDA   ****************
	 ****												   ****
	 ****		--------	Tipo 31X	--------		   ****
	 ****		   X = 1 Probar los algoritmos 			   ****
	 ****		X = 2 Comparar todos los algoritmos		   ****
	 ****												   ****
	 ****		--------	Tipo 32X	--------		   ****
	 ****		        Medir el algitmo X				   ****
	 ****												   ****
	 ****		--------	Tipo 4XY	--------		   ****
	 ****		  Comparar el algoritmo X con el Y		   ****
	 ****												   ****
	 **********************************************************
	 ****************          AJUSTE          ****************
	 ****												   ****
	 ****		--------	Tipo 51X	--------		   ****
	 ****    Establecer el caso X para las instancias	   ****
	 ****												   ****
	 **********************************************************/

int menuCasosInstancia() {
	int c;
	system("cls");

	do {
		cout << "\n FAA. Practica 3. Curso 18/19 \n"
			<< "                                                           Borja Lopez Pineda \n"
			<< "-----                           MENU AJUSTES                            -----\n"
			<< "-----                SELECCIONE UN CASO PARA LA INSTANCIA               -----\n\n"
			<< "                                1.- Mejor\n"
			<< "                                2.- Medio\n"
			<< "                                3.- Peor\n"
			<< "                      ---------------------------------\n"
			<< "                         Elija una opcion: ";

		c = cin.get();
		system("cls");
	} while (c != '1' && c != '2' && c != '3');


	switch (c) {
	case '1':
		c = MEJOR;
		break;
	case '2':
		c = MEDIO;
		break;
	case '3':
		c = PEOR;
		break;
	}

	cout << "\n\n";
	return c;
}
int menuAjustes() {
	int c;
	system("cls");

	do {
		cout << "\n FAA. Practica 3. Curso 18/19 \n"
			<< "                                                           Borja Lopez Pineda \n"
			<< "-----                           MENU AJUSTES                            -----\n\n"
			<< "                     1.- Cambiar el caso de la instancia\n\n"
			<< "                     0.- Regresar\n"
			<< "                  ---------------------------------\n"
			<< "                       Elija una opcion: ";

		c = cin.get();
		system("cls");
	} while (c != '1' && c != '0');


	switch (c) {
	case '1':
		c = menuCasosInstancia() + 10;
		break;
	case '0':
		c = 0;
		break;
	}

	cout << "\n\n";
	return c;
}
int menuAlgoritmosBusqueda() {
	int c;

	system("cls");
	do {
		cout << "\n FAA. Practica 3. Curso 18/19 \n"
			<< "                                                           Borja Lopez Pineda \n"
			<< "-----            MENU SELECCION DE ALGORITMOS DE ORDENACION              -----\n\n"
			<< "                         1.- Busqueda Secuencial Iterativa\n"
			<< "                         2.- Busqueda Binaria Recursiva\n"
			<< "                         3.- Busqueda Ternaria Recursiva\n"
			<< "                  ---------------------------------\n"
			<< "                       Elija una opcion: ";

		c = cin.get();
		system("cls");
	} while (c != '1' && c != '2' && c != '3');

	switch (c) {
	case '1':
		c = SECUENCIALIt;
		break;
	case '2':
		c = BINARIARc;
		break;
	case '3':
		c = TERNARIARc;
		break;
	}

	return c;
}
int menuAlgoritmosOrdenacion() {
	int c;

	system("cls");
	do {
		cout << "\n FAA. Practica 3. Curso 18/19 \n"
			<< "                                                           Borja Lopez Pineda \n"
			<< "-----             MENU SELECCION DE ALGORITMOS DE BUSQUEDA               -----\n\n"
			<< "                         1.- Burbuja\n"
			<< "                         2.- Insercion\n"
			<< "                         3.- Seleccion\n"
			<< "                         4.- Shell\n"
			<< "                         5.- Mergesort\n"
			<< "                         6.- Quicksort\n"
			<< "                         7.- Shakesort\n"
			<< "                         8.- Bricksort\n"
			<< "                  ---------------------------------\n"
			<< "                       Elija una opcion: ";

		c = cin.get();
		system("cls");
	} while (c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8');

	switch (c) {
	case '1':
		c = BURBUJA;
		break;
	case '2':
		c = INSERCION;
		break;
	case '3':
		c = SELECCION;
		break;
	case '4':
		c = SHELL;
		break;
	case '5':
		c = MERGESORT;
		break;
	case '6':
		c = QUICKSORT;
		break;
	case '7':
		c = SHAKESORT;
		break;
	case '8':
		c = BRICKSORT;
		break;
	}

	return c;
}
int menuOperaciones(bool ordenacion) {
	int c;
	system("cls");

	do {
		cout << "\n FAA. Practica 3. Curso 18/19 \n"
			<< "                                                           Borja Lopez Pineda \n"
			<< "-----                   MENU ALGORITMOS DE "<<(ordenacion?"ORDENACION":"BUSQUEDA  ")<<"                   -----\n\n"
			<< "                         1.- Probar los metodos de " << (ordenacion ? "ordenacion" : "busqueda") << "\n"
			<< "                         2.- Obtener el caso medio de un metodo de ordenacion\n"
			<< "                         3.- Comparar dos metodos\n"
			<< "                         4.- Comparar todos los metodos\n\n"
			<< "                         0.- Regresar\n"
			<< "                  ---------------------------------\n"
			<< "                       Elija una opcion: ";

		c = cin.get();
		system("cls");
	} while (c != '1' && c != '2' && c != '3' && c != '4' && c != '0');


	switch (c) {
	case '1':
		c = 11;
		break;
	case '2':
		if(ordenacion)
			c = menuAlgoritmosOrdenacion();
		else
			c = menuAlgoritmosBusqueda();
		c += 20;
		break;
	case '3':
		if (ordenacion)
			c = (menuAlgoritmosOrdenacion() * 10) + menuAlgoritmosOrdenacion();
		else
			c = (menuAlgoritmosBusqueda() * 10) + menuAlgoritmosBusqueda();
		c += 100;
		break;
	case '4':
		c = 12;
		break;
	case '0':
		c = 0;
		break;
	}

	return c;
}
int menu() {
	int c;
	system("cls");

	do {
		cout << "\n FAA. Practica 3. Curso 18/19 \n"
			<< "                                                           Borja Lopez Pineda \n"
			<< "-----                           MENU PRINCIPAL                          -----\n"
			<< "-----   ANALISIS EXPERIMENTAL DE ALGORITMOS DE ORDENACION Y BUSQUEDA    -----\n\n"
			<< "                     1.- Algoritmos de ordenacion\n"
			<< "                     2.- Algoritmos de busqueda\n"
			<< "                     3.- Opciones\n\n"
			<< "                     0.- Salir\n"
			<< "                  ---------------------------------\n"
			<< "                       Elija una opcion: ";

		c = cin.get();
		system("cls");
	} while (c != '1' && c != '2' && c != '3' && c != '0');


	switch (c) {
	case '1':
		c = menuOperaciones(true);
		if (c == 0)
			c = menu();
		else
			c += 100;
		break;
	case '2':
		c = menuOperaciones(false);
		if (c == 0)
			c = menu();
		else
			c += 300;
		break;
	case '3':
		c = menuAjustes();
		if (c == 0)
			c = menu();
		else
			c += 500;
		break;
	case '0':
		c = 0;
		break;
	}

	cout << "\n\n";
	return c;
}

/** Programa principal **/
int main()
{
	ConjuntoInt::setCaso(MEDIO);
	TestOrdenacion testOrdenacion;
	TestBusqueda testBusqueda;

	int opt, p1, p2, p3;

	do {
		opt = menu();
		p1 = opt / 100;
		p2 = (opt / 10) % 10;
		p3 = opt % 10;
		//cout << opt << "\n";
		//system("pause");


		switch (p1) {
			case 1:
				switch (p2) {
					case 1:
						if (p3 == 1)
							testOrdenacion.comprobarMetodosOrdenacion();
						else if (p3 == 2)
							testOrdenacion.compararTodos();
					break;
					case 2:
						testOrdenacion.casoMedio(p3);
					break;
				}
			break;
			case 2:
				testOrdenacion.comparar(p2, p3);
			break;
			case 3:
				switch (p2) {
				case 1:
					if (p3 == 1)
						testBusqueda.comprobarMetodosBusqueda();
					else if (p3 == 2)
						testBusqueda.compararTodos();
					break;
				case 2:
					testBusqueda.casoMedio(p3);
					break;
				}
			break;
			case 4:
				testBusqueda.comparar(p2, p3);
			case 5:
				switch (p2) {
					case 1:
						ConjuntoInt::setCaso(p3);
					break;
				}
			break;
		}
	} while (opt != 0);

	system("taskkill /F /T /IM wgnuplot.exe");
	return 0;
}