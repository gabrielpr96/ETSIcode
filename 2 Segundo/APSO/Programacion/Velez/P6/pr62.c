#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/msg.h>
#include <errno.h>
#include <unistd.h>
#include <time.h>

struct Mensaje1{
	long id;
	char dato;
};

int main(){
	srand(time(0));

	key_t clave;
	int cola, i;
	struct Mensaje1 mensaje1;

	clave = ftok("Makefile", 144);
	cola = msgget(clave, 0600 | IPC_CREAT);

	mensaje1.id = 1;
	for(i = 0; i < 10; i++){
		mensaje1.dato = (char)((rand()%94)+32);
		msgsnd(cola, (struct msgbuf *) &mensaje1, sizeof(struct Mensaje1)-sizeof(long), 0);

		sleep((rand()%8)+8);
	}

	return 0;
}
