#include <iostream>
#include <cstdlib>

using namespace std;

int main(){
	int longitud_en_en_metros = 15000;
	double Precio_total_en_euros;


	Precio_total_en_euros = 5000 * (longitud_en_en_metros / 5280.0);

	cout << "\nResultado de la primera operacion: " << Precio_total_en_euros;

	Precio_total_en_euros = 5000 * (longitud_en_en_metros / 5280);

	cout << "\nResultado de la segunda operacion: " << Precio_total_en_euros << "\n\n";

	system("pause");
	return 0;
}

/*
	En ambos casos la divisi�n realidad es 15000/5280 = 2.84090909
	En el primero se realiza con un entero y un punto flotante(indicado mediante el ".0" al final del int literal), luego devolver� un punto flotante 2.84090909
	En el segundo la operaci�n es con dos enteros, aproxima a la baja y devuelve 2.
	Esta diferencia se ve reflejada en las salidas.
*/
