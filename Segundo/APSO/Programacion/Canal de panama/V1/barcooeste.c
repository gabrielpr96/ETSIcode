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
	key_t clave;
	int cola;
	signal(10,R10);
	signal(12,R12);

	clave = ftok ("./fichcola.txt", 18);
    cola = crea_cola(clave);

	visualiza(cola, VOESTEIN, PINTAR, TIPOOESTE);
	sleep(2);
	visualiza(cola, VOESTEIN, BORRAR, TIPOOESTE);


	return 0;
}
