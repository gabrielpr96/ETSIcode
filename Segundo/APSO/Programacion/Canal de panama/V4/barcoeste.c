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
	visualiza(cola, VESTEIN, BORRAR, TIPOESTE);
	visualiza(cola, VHORNOS, PINTAR, TIPOESTE);
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

	visualiza(cola, VESTEIN, PINTAR, TIPOESTE);							// 1- Pintarse en entrada E
	alarm((rand()%(param.mevoymax-param.mevoymin+1))+param.mevoymin);	// 2- Comienzo a esperar
	read(lago, &uno, 1);												// 3- Pedir testigo del lago
	alarm(0);															// 4- Cancelar la alarma
	read(esclusae, &uno, 1);											// 5- Pedir testigo de la exclusa E
	visualiza(cola, VESTEIN, BORRAR, TIPOESTE);							// 6- Borrarse de entrada E
	visualiza(cola, VESCLUSAESTE, PINTAR, TIPOESTE);					// 7- Pintarse en la exclusa E
	sleep(param.tesclusa);												// 8- Esperar el tiempo de la exclusa
	visualiza(cola, VESCLUSAESTE, BORRAR, TIPOESTE);					// 9- Borrarse de la eclusa E
	visualiza(cola, VLAGOE, PINTAR, TIPOESTE);							//10- Pintarse en el lago E
	write(esclusae, &uno, 1);											//11- Liberar la exclusa
	sleep((rand()%(param.lagomax-param.lagomin+1))+param.lagomin);		//12- Esperar el tiempo del lago
	read(esclusao, &uno, 1);											//13- Pedir testigo de la exclusa O
	visualiza(cola, VLAGOE, BORRAR, TIPOESTE);							//14- Borrarse del lago E
	visualiza(cola, VESCLUSAOESTE, PINTAR, TIPOESTE);					//15- Pintarse en la exclusa O
	write(lago, &uno, 1);												//16- Liberar el lago
	sleep(param.tesclusa);												//17- Esperar el tiempo de la exclusa
	visualiza(cola, VESCLUSAOESTE, BORRAR, TIPOESTE);					//18- Borrarse de la exclusa O
	visualiza(cola, VOESTEOUT, PINTAR, TIPOESTE);						//19- Pintarse en la salida O
	write(esclusao, &uno, 1);											//20- Liverar la exclusa O

	close(lago);
	close(esclusae);
	close(esclusao);

	return 0;
}
