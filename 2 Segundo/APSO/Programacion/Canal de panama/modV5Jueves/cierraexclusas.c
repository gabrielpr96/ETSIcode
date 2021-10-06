#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>

int tCerrada = 0, nCierres = 0;

void R10(){
	int fCierreExclusas = open("cierre", O_RDWR);
	write(fCierreExclusas, &tCerrada, sizeof(tCerrada));
	write(fCierreExclusas, &nCierres, sizeof(nCierres));
	close(fCierreExclusas);
	exit(0);
}

int main(){
	int pidExclusaE, pidExclusaO, tEspera;
	signal(10,R10);

	srand(getpid());

	read(2, &pidExclusaE, sizeof(pidExclusaE));
	read(2, &pidExclusaO, sizeof(pidExclusaO));

	while(1){
		sleep((rand()%3)+10);
		printf("Cerrada\n");

		kill(pidExclusaE, 12);
		kill(pidExclusaO, 12);

		tEspera = (rand()%6)+25;
		tCerrada += tEspera;
		nCierres++;
		sleep(tEspera);
		printf("Abierta\n");

		kill(pidExclusaE, 12);
		kill(pidExclusaO, 12);
	}

}
