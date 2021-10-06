#include <sys/wait.h>
#include <sys/msg.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include "comun.h"

int llega10 = 0;

int crea_cola(key_t clave);
void R10(){
	llega10 = 1;
}


int main(){
	struct tipo_registro mensaje;
    int cola, res, barcosE = 0, barcosO = 0;
	long tiempoE = 0, tiempoO = 0;

	signal(10, R10);

    cola = crea_cola(ftok("./fichcola.txt", 144));


	while(llega10 == 0){
		 res = msgrcv(cola, (struct tipo_registro *) &mensaje,sizeof(struct tipo_registro)-sizeof(long), 0, IPC_NOWAIT);
		 if(res != -1){
			 if(mensaje.cualidad == TIPOESTE){
				 barcosE ++;
				 tiempoE += mensaje.tiempo;
			 }else if(mensaje.cualidad == TIPOOESTE){
				 barcosO ++;
				 tiempoO += mensaje.tiempo;
			 }
		 }
	}
	sleep(1);

	printf("Numero de barcos este: %d, tiempo medio %fs\n", barcosE, tiempoE/(float)barcosE);
	printf("Numero de barcos oeste: %d, tiempo medio %fs\n", barcosO, tiempoO/(float)barcosO);

	msgctl(cola, IPC_RMID, 0);

	return 0;
}

/*********** FUNCION: crea_cola ********************************************************/
/*********** Obtiene acceso a la cola de mensajes con el id que se pasa ****************/
int crea_cola(key_t clave)
{
 int identificador;
 if(clave == (key_t) -1)
 {
   printf("Error al obtener clave para cola mensajes\n");
   exit(-1);
 }

 identificador = msgget(clave, 0600 | IPC_CREAT);
 //LA BORRAMOS POR SI TIENE MENSAJES DE EJECUCIONES ANTERIORES
 msgctl(identificador,IPC_RMID,NULL);
 identificador = msgget(clave, 0600 | IPC_CREAT);

 if (identificador == -1)
 {
   printf("Error al obtener identificador para cola mensajes\n");
   perror("msgget");
   exit (-1);
 }

 return identificador;
}
