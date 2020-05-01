#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <fcntl.h>
#include <sys/msg.h>

struct Mensaje{
	long id;
	int dato;
};

int tiempo;

void finTiempo(){
	exit(0);
}

void continuar(){}

int main(){
	int secreto, tmp, intentos = 10, cola;
	key_t clave;
	struct Mensaje mensaje;
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

	clave = ftok("Makefile", 144);
	cola = msgget(clave, 0600 | IPC_CREAT);

	mensaje.id = 2;
	mensaje.dato = tmp==secreto;
	msgsnd(cola, (struct msgbuf*) &mensaje, sizeof(struct Mensaje)-sizeof(long), 0);
	exit(0);

	return 0;
}
