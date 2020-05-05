#include <unistd.h>
#include <stdio.h>
#include <sys/msg.h>
#include <sys/wait.h>

struct Mensaje{
	long id;
	int dato;
};

void continuar(){
	printf("L23 con pid %d. Mensaje 3\n", getpid());
}

int main(){
	int pid24, cola;
	key_t clave;
	struct Mensaje mensaje;

	signal(14, continuar);

	clave = ftok("Makefile", 144);
	cola = msgget(clave, 0600 | IPC_CREAT);

	pid24 = fork();
	if(pid24 == 0)
		execl("L24", "L24", NULL);
	close(2);

	msgrcv(cola, (struct msgbuf*)&mensaje, sizeof(struct Mensaje)-sizeof(long), 1, 0);

	printf("L23 con pid %d ha leído el número %d. Mensaje 2\n", getpid(), mensaje.dato);

	alarm(mensaje.dato);

	pause();

	kill(pid24, 12);

	wait(NULL);

	return 0;
}
