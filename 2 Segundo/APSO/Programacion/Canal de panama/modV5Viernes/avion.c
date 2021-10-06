#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/msg.h>
#include <errno.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#include "comun.h"
#include "comunbarcos.h"


int main()
{

	int colagrafica, colaaviones;
	int flago, testigo=1;
    struct testigoAviones tAviones;

	srand(getpid());
	signal(10,R10); // me preparo para la senyal 10
	signal(12,R12); // me preparo para la senyal 12

	// Creamos y abrimos las colas de mensajes
	colagrafica=crea_cola(ftok ("./fichcola.txt", 18));
	colaaviones=crea_cola(ftok ("./fichcola.txt", 144));

	// Abrimos las fifos
	flago=open("lago",O_WRONLY);

	//Nos pintamos en el cabo de ornos
	visualiza(colagrafica, VHORNOS, PINTAR, TIPOAVION);

	//Quedo a la espera si no soy el unico avion
	msgrcv(colaaviones, (struct testigoAviones *) &tAviones,sizeof(struct testigoAviones)-sizeof(long), 1, 0);

	//Busca sitio en el lago
	read(flago ,&testigo, sizeof(testigo));

	//Nos pintamos en el lago
	visualiza(colagrafica, VHORNOS, BORRAR, TIPOAVION);
	visualiza(colagrafica, VLAGOE, PINTAR, TIPOAVION);

	//Espera un tiempo aleatorio en el lago
	sleep((rand()%3)+2);

	//Se pinta en una de las
	visualiza(colagrafica, VLAGOE, BORRAR, TIPOAVION);
	if(rand()%2 == 0)
		visualiza(colagrafica, VESTEOUT, PINTAR, TIPOAVION);
	else
		visualiza(colagrafica, VOESTEOUT, PINTAR, TIPOAVION);

	//Devuelve el testigo al lago y a los aviones
	write(flago ,&testigo, sizeof(testigo));
	msgsnd(colaaviones, (struct testigoAviones *) &tAviones,sizeof(struct testigoAviones)-sizeof(long),0);

	close(flago);
}
