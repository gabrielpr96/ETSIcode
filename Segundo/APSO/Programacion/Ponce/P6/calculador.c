#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/msg.h>
#include <time.h>
#include <fcntl.h>

struct Mensaje{
	long id;
	int dato;
};

int main(){
	srand(time(0));

	int cola, n;
	key_t clave;
	struct Mensaje mensaje;

	clave = ftok("Makefile", 144);
	cola = msgget(clave, 0600 | IPC_CREAT);
	msgrcv(cola, (struct msgbuf *)&mensaje, sizeof(struct Mensaje)-sizeof(long), 1, 0);

	n = rand()%(mensaje.dato+1);

	sleep(2);
	printf("Número calculado, intente adivinarlo en 10 intentos.\n");

	write(2, (char*) &n, sizeof(int));
	close(2);

	exit(0);

	return 0;
}
