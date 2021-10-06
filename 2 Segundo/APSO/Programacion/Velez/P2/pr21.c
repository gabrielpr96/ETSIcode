#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>

int entrada = 0;

void *hilo1(void){
	do{
		//Numero aleatorio par entre 12 y 144
		int n = ((rand()%67)+6)*2;

		printf("Introducido: %d Aleatorio: %d Suma: %d\n", entrada, n, n+entrada);

		sleep(1);
	}while(entrada != -1);
}

void *hilo2(void){
	do{
        scanf("%d", &entrada);
	}while(entrada != -1);
}


int main(){
	srand(time(0));
	pthread_t h1, h2;

	pthread_create(&h1, NULL, (void *) &hilo1, NULL);
	pthread_create(&h2, NULL, (void *) &hilo2, NULL);

	pthread_join(h1, NULL);
	pthread_join(h2, NULL);

	return 0;
}
