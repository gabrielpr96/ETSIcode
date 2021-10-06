#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <signal.h>
#include <time.h>
#include "comunbarcos.h"

int cola, lago, esclusae, esclusao;

void R14(){
	visualiza(cola, VOESTEIN, BORRAR, TIPOOESTE);
	visualiza(cola, VHORNOS, PINTAR, TIPOOESTE);
	close(lago);
	close(esclusae);
	close(esclusao);
	exit(0);
}

int main(){
	srand(time(0));
	struct Parametros param;
	key_t clave;
	char uno = 1;
	signal(10, R10);
	signal(12, R12);
	signal(14, R14);

	lago = open("/home/apso/lago", O_RDWR);
	esclusae = open("/home/apso/esclusae", O_RDWR);
	esclusao = open("/home/apso/esclusao", O_RDWR);

	clave = ftok ("./fichcola.txt", 18);
    cola = crea_cola(clave);

	read(2, (char*)&param, sizeof(struct Parametros));
	close(2);
	dup(1);

	visualiza(cola, VOESTEIN, PINTAR, TIPOOESTE);
	alarm((rand()%(param.mevoymax-param.mevoymin+1))+param.mevoymin);
	read(lago, &uno, 1);
	alarm(0);
	read(esclusao, &uno, 1);
	visualiza(cola, VOESTEIN, BORRAR, TIPOOESTE);
	visualiza(cola, VESCLUSAOESTE, PINTAR, TIPOOESTE);
	sleep(param.tesclusa);
	visualiza(cola, VESCLUSAOESTE, BORRAR, TIPOOESTE);
	visualiza(cola, VLAGOO, PINTAR, TIPOOESTE);
	write(esclusao, &uno, 1);
	sleep((rand()%(param.lagomax-param.lagomin+1))+param.lagomin);
	read(esclusae, &uno, 1);
	visualiza(cola, VLAGOO, BORRAR, TIPOOESTE);
	visualiza(cola, VESCLUSAESTE, PINTAR, TIPOOESTE);
	write(lago, &uno, 1);
	sleep(param.tesclusa);
	visualiza(cola, VESCLUSAESTE, BORRAR, TIPOOESTE);
	visualiza(cola, VESTEOUT, PINTAR, TIPOOESTE);
	write(esclusae, &uno, 1);

	close(lago);
	close(esclusae);
	close(esclusao);

	return 0;
}
