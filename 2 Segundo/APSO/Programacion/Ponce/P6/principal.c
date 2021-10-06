#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <sys/msg.h>
#include <fcntl.h>

struct Mensaje{
	long id;
	int dato;
};

int main(){
	int pidContador, pidCalculador, pidAdivina, n, tubo[2], fifo, cola;
	key_t clave;
	unsigned long contador;
	struct Mensaje mensaje;

	mkfifo("fifo", 0600);
	pipe(tubo);

	clave = ftok("Makefile", 144);
	cola = msgget(clave, 0600 | IPC_CREAT);

	pidContador = fork();
	if(pidContador == 0)
		execl("contador", "contador", NULL);

	wait(NULL);
	fifo = open("fifo", O_RDWR);
	read(fifo, (char*) &contador, sizeof(contador));
	close(fifo);

	pidCalculador = fork();
	if(pidCalculador == 0){
		close(2);
		dup(tubo[1]);
		execl("calculador", "calculador", NULL);
	}

	pidAdivina = fork();
	if(pidAdivina == 0){
		close(2);
		dup(tubo[0]);
		execl("adivina", "adivina", NULL);
	}


	printf("El contador ha finalizado con: %lu\n", contador);
	printf("Introduzca un entero positivo: ");
	scanf("%d", &n);

	mensaje.id = 1;
	mensaje.dato = n;
	msgsnd(cola, (struct msgbuf*) &mensaje, sizeof(struct Mensaje)-sizeof(long), 0);

	wait(NULL);
	wait(NULL);

	msgrcv(cola, (struct msgbuf *)&mensaje, sizeof(struct Mensaje)-sizeof(long), 2, 0);
	printf(mensaje.dato==1?"Felicidades, has ganado.\n":"Has perdido.\n");

	unlink("fifo");
	msgctl(cola, IPC_RMID, (struct msqid_ds *)NULL);

	return 0;
}
