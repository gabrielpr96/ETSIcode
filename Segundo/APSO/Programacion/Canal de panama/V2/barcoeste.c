#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <signal.h>
#include <time.h>
#include "comunbarcos.h"

int main(){
	srand(time(0));
	struct Parametros param;
	key_t clave;
	int cola;
	signal(10,R10);
	signal(12,R12);

	clave = ftok ("./fichcola.txt", 18);
    cola = crea_cola(clave);

	read(2, (char*)&param, sizeof(struct Parametros));
	close(2);
	dup(1);

	visualiza(cola, VESTEIN, PINTAR, TIPOESTE);
	sleep((rand()%(param.lagomax-param.lagomin+1))+param.lagomin);
	visualiza(cola, VESTEIN, BORRAR, TIPOESTE);


	return 0;
}
