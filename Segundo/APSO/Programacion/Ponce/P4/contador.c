#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/types.h>

unsigned long n;

void alarma(){
	printf("El contador ha finalizado con: %lu\n", n);

	exit(0);
}


int main(){
	signal(14, alarma);
	n = 0;

	alarm(2);

	while(n < 200000000) n++;

	alarma();

	return 0;
}
