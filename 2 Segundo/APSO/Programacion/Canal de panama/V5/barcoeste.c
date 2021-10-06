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

int main(){
	srand(time(0));
	int cola, lago, esclusae, esclusao, colalago, colaesclusae, colaesclusao;
	int pid = getpid();
	struct Parametros param;
	key_t clave;
	char uno = 1;
	llega14 = 0;
	llega16 = 0;

	signal(10, R10);
	signal(12, R12);
	signal(14, R14);
	signal(16, R16);

	lago = open("/home/apso/lago", O_RDWR);
	esclusae = open("/home/apso/esclusae", O_RDWR);
	esclusao = open("/home/apso/esclusao", O_RDWR);
	colalago = open("/home/apso/colalago", O_RDWR);
	colaesclusae = open("/home/apso/colaesclusae", O_RDWR);
	colaesclusao = open("/home/apso/colaesclusao", O_RDWR);

	clave = ftok ("./fichcola.txt", 18);
    cola = crea_cola(clave);

	read(2, (char*)&param, sizeof(struct Parametros));
	close(2);
	dup(1);

	visualiza(cola, VESTEIN, PINTAR, TIPOESTE);							// 1- Pintarse en entrada E
	alarm((rand()%(param.mevoymax-param.mevoymin+1))+param.mevoymin);	// 2- Comienzo a esperar
	write(colalago, (char*)&pid, sizeof(int));							// 3- Pedir testigo del lago
	if(llega16 == 0 && llega14 == 0)pause();							// 4- Esperar a que llegue mi turno o la alarma
	if(llega14 == 1){
		visualiza(cola, VESTEIN, BORRAR, TIPOESTE);
		visualiza(cola, VHORNOS, PINTAR, TIPOESTE);
		pause();														//5b- Esperar a que llegue el turno
		write(lago, &uno, 1);											//5b- Liberar el lago
	}else{
		alarm(0);														// 5- Cancelar la alarma
		llega16 = 0;
		write(colaesclusae, (char*)&pid, sizeof(int));					// 6- Pedir testigo de la exclusa E
		if(llega16==0){pause();}										// 7- Esperar a que llegue mi turno
		llega16=0;
		visualiza(cola, VESTEIN, BORRAR, TIPOESTE);						// 8- Borrarse de entrada E
		visualiza(cola, VESCLUSAESTE, PINTAR, TIPOESTE);				// 9- Pintarse en la exclusa E
		sleep(param.tesclusa);											//10- Esperar el tiempo de la exclusa
		visualiza(cola, VESCLUSAESTE, BORRAR, TIPOESTE);				//11- Borrarse de la eclusa E
		visualiza(cola, VLAGOE, PINTAR, TIPOESTE);						//12- Pintarse en el lago E
		write(esclusae, &uno, 1);										//13- Liberar la exclusa
		sleep((rand()%(param.lagomax-param.lagomin+1))+param.lagomin);	//14- Esperar el tiempo del lago
		write(colaesclusao, (char*)&pid, sizeof(int));					//15- Pedir testigo de la exclusa O
		if(llega16==0){pause();}										//16- Esperar a que llegue mi turno
		llega16=0;
		visualiza(cola, VLAGOE, BORRAR, TIPOESTE);						//17- Borrarse del lago E
		visualiza(cola, VESCLUSAOESTE, PINTAR, TIPOESTE);				//18- Pintarse en la exclusa O
		write(lago, &uno, 1);											//19- Liberar el lago
		sleep(param.tesclusa);											//20- Esperar el tiempo de la exclusa
		visualiza(cola, VESCLUSAOESTE, BORRAR, TIPOESTE);				//21- Borrarse de la exclusa O
		visualiza(cola, VOESTEOUT, PINTAR, TIPOESTE);					//22- Pintarse en la salida O
		write(esclusao, &uno, 1);										//23- Liverar la exclusa O
	}

	close(lago);
	close(esclusae);
	close(esclusao);
	close(colalago);
	close(colaesclusae);
	close(colaesclusao);

	return 0;
}
