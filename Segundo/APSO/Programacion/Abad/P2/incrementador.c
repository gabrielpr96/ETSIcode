#include <stdio.h>
#include <unistd.h>
#include <pthread.h>

int inc = 1;

void *hilo1(int *ini){
	int n = *ini;
	do{
		printf("Contador: %d\n", n);
		n += inc;
		sleep(1);
	}while(inc != 0);
}

void *hilo2(void){
	do{
        scanf("%d", &inc);
	}while(inc != 0);
}


int main(){
	pthread_t h1, h2;

	int ini;
	printf("Valor inicial: ");
	scanf("%d", &ini);

	pthread_create(&h1, NULL, (void *) &hilo1, &ini);
	pthread_create(&h2, NULL, (void *) &hilo2, NULL);

	pthread_join(h1, NULL);
	pthread_join(h2, NULL);

	return 0;
}
