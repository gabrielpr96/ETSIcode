#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(){
	int pidContador, pidCalculador, pidAdivina, n;
	char buff[10];

	pidContador = fork();
	if(pidContador == 0)
		execl("contador", "contador", NULL);

	wait(NULL);

	printf("Introduzca un entero positivo: ");
	scanf("%d", &n);
	snprintf(buff, sizeof(buff), "%d", n);

	pidCalculador = fork();
	if(pidCalculador == 0)
		execl("calculador", "calculador", buff, NULL);

	pidAdivina = fork();
	if(pidAdivina == 0)
		execl("adivina", "adivina", NULL);

	wait(NULL);
	kill(pidAdivina, 11);

	wait(&n);
	printf((n>>8)==1?"Felicidades, has ganado.\n":"Has perdido.\n");

	return 0;
}
