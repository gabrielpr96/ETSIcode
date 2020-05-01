#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <fcntl.h>

int tiempo;

void finTiempo(){
	exit(0);
}

void continuar(){}

int main(){
	int secreto, tmp, intentos = 10;
	tiempo = 1;
	signal(14, finTiempo);

	read(2, (char *) &secreto, sizeof(int));
	close(2);

	alarm(60);

	while(intentos > 0 && tmp != secreto){
		printf("Intento: ");
		scanf("%d", &tmp);
		intentos--;
		if(tmp > secreto)
			printf("El secreto es menor. Te quedan %d intentos.\n", intentos);
		else if(tmp < secreto)
			printf("El secreto es mayor. Te quedan %d intentos.\n", intentos);
	}

	exit(tmp==secreto?1:0);

	return 0;
}
