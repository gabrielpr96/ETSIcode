#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/msg.h>
#include <sys/wait.h>
#include <time.h>

struct Mensaje{
	long id;
	int dato;
};

void continuar(){
	printf("L22 con pid %d. Mensaje 5\n", getpid());
}

int main(){
	srand(time(0));
	int pid22 = getpid(), cola;
	key_t clave;
	struct Mensaje mensaje;

	signal(12, continuar);

	clave = ftok("Makefile", 144);
	cola = msgget(clave, 0600 | IPC_CREAT);

	mensaje.id = 1;
	mensaje.dato = (rand()%6)+5;
	msgsnd(cola, (struct msgbuf*)&mensaje, sizeof(struct Mensaje)-sizeof(long), 0);

	write(2, (char*)&pid22, sizeof(int));

	pause();

	kill(getppid(), 12);

	close(2);

	return 0;
}
