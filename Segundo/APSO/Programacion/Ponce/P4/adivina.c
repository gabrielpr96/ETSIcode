#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>

unsigned long long contador;
int secreto = -1;

void *hilo1(void){
	contador = 0;
	while(contador < 300000) contador++;
}

void *hilo2(int *num){
	sleep(2);
	printf("Numero calculado, intente adivinarlo en 10 intentos\n");
	secreto = rand()%((*num)+1);
}
void *hilo3(void){
	while(secreto == -1);
	int intentos = 10, tmp = -1;

	while(intentos > 0 && tmp != secreto){
		printf("Intento: ");
		scanf("%d", &tmp);
		if(tmp > secreto)
			printf("El secreto es menor. Te quedan %d intentos.\n", intentos);
		else if(tmp < secreto)
			printf("El secreto es mayor. Te quedan %d intentos.\n", intentos);
		intentos--;
	}

	pthread_exit((void *)(tmp==secreto?1:0));
}


int main(){
	srand(time(0));
	pthread_t h1, h2, h3;

	pthread_create(&h1, NULL, (void *) &hilo1, NULL);
	pthread_detach(h1);

	sleep(2);
	printf("Ha llegado a %d\n", contador);
	int num;
	printf("Intriduce un entero positivo: ");
	scanf("%d", &num);

	pthread_create(&h2, NULL, (void *) &hilo2, &num);
	pthread_create(&h3, NULL, (void *) &hilo3, &h2);
	int res;
	pthread_join(h2, NULL);
	pthread_join(h3, (void *) &res);

	printf(res==1?"Felicidades, has ganado.\n":"Has perdido.\n");

	pthread_join(h1, NULL);
	return 0;
}
