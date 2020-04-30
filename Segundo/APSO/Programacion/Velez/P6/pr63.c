#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/msg.h>
#include <errno.h>
#include <unistd.h>
#include <time.h>

struct Mensaje2{
	long id;
	int dato;
};

int main(){
	srand(time(0));

	key_t clave;
	int cola, i;
	struct Mensaje2 mensaje2;

	clave = ftok("Makefile", 144);
	cola = msgget(clave, 0600 | IPC_CREAT);

	mensaje2.id = 2;
	for(i = 0; i < 10; i++){
		mensaje2.dato = ((rand()%69)+4)*2;
		msgsnd(cola, (struct msgbuf *) &mensaje2, sizeof(struct Mensaje2)-sizeof(long), 0);

		sleep((rand()%10)+3);
	}

	return 0;
}
