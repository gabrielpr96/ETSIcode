#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <fcntl.h>

int main(){
	int pidContador, pidCalculador, pidAdivina, n, tubo[2], fifo;
	unsigned long contador;
	char buff[10];

	mkfifo("fifo", 0600);
	pipe(tubo);

	pidContador = fork();
	if(pidContador == 0)
		execl("contador", "contador", NULL);

	wait(NULL);
	fifo = open("fifo", O_RDWR);
	read(fifo, (char*) &contador, sizeof(contador));
	close(fifo);

	printf("El contador ha finalizado con: %lu\n", contador);

	printf("Introduzca un entero positivo: ");
	scanf("%d", &n);
	snprintf(buff, sizeof(buff), "%d", n);

	pidCalculador = fork();
	if(pidCalculador == 0){
		close(2);
		dup(tubo[1]);
		execl("calculador", "calculador", buff, NULL);
	}

	pidAdivina = fork();
	if(pidAdivina == 0){
		close(2);
		dup(tubo[0]);
		execl("adivina", "adivina", NULL);
	}

	wait(NULL);
	wait(&n);
	printf((n>>8)==1?"Felicidades, has ganado.\n":"Has perdido.\n");

	return 0;
}
