#include <stdio.h>
#include <stdlib.h>
#include "funciones.h"


int menu(){
	system("clear");
	printf("1. Leer datos\n2. Visualizar la suma\n3. Calcular la raiz cubica del maximo\n4. Salir\n");
	int opt;
	scanf("%d", &opt);
	system("clear");
	return opt;
}


int main(){
	int arr[5], opt;
	do{
		opt = menu();
		switch(opt){
			case 1:
				printf("Introduzca 5 enteros\n");
				int i;
				for(i = 0; i < 5; i++){
					printf("Numero %d: ", i+1);
					scanf("%d", &arr[i]);
				}

				printf("Numeros que has introducido: ");
				for(i = 0; i < 5; i++)
					printf("%d ", arr[i]);
			break;
			case 2:
				printf("La suma de todos los elementos es: %d\n", suma(arr));
			break;
			case 3:
				printf("La raiz cubica del mayor de los elementos es: %0.2f\n", cubica(arr));
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
