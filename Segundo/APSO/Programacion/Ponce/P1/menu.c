#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "funciones.h"


int menu(){
	system("clear");
	printf("1. Leer datos\n2. Visualizar datos generales\n3. Visualizar seno del maximo\n4. Salir\n");
	int opt;
	scanf("%d", &opt);
	system("clear");
	return opt;
}


int main(){
	int arr[15], n = 0, opt;
	do{
		opt = menu();
		switch(opt){
			case 1:
				printf("Introduzca un maxmo de 15 enteros, -1 para terminar\n");
				int tmp, i;
				n = 0;
				do{
					printf("Numero %d: ", n+1);
					scanf("%d", &tmp);
					if(tmp != -1){
						arr[n] = tmp;
						n++;
					}
				}while(tmp != -1 && n < 15);
			break;
			case 2:
				printf("Maximo: %d Minimo: %d Media %0.2f\n", maximo(arr, n), minimo(arr, n), media(arr, n));
			break;
			case 3:
				printf("La raiz cubica del mayor de los elementos es: %0.2f\n", sin(maximo(arr, n)));
			break;
			case 4:
				printf("Hasta luego.\n");
			break;
		}
		printf("\n\nPulsa una tecla para continuar.\n");
		fflush(stdin);
		getchar();
		getchar();
	}while(opt != 4);

	return 0;
}
