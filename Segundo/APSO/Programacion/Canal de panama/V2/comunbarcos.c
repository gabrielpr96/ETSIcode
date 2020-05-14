#include "comunbarcos.h"

int llega10 = 0;

void R10(){llega10=1;}
void R12(){exit(-1);}

int crea_cola(key_t clave){
	int identificador;
	if(clave == (key_t)-1){
		printf("Error al obtener clave para cola mensajes\n");
		exit(-1);
	}
	identificador = msgget(clave, 0600 | IPC_CREAT);
	if(identificador == -1){
		printf("Error al obtener identificador para cola mensajes\n");
		perror("msgget");
		exit(-1);
	}
	return identificador;
}

void visualiza(int cola, int donde, int que, int como){
	struct tipo_elemento peticion;

	peticion.tipo = 1;
	peticion.pid = getpid();
	peticion.donde = donde;
	peticion.que = que;
	peticion.cualidad = como;

	msgsnd(cola, (struct tipo_elemento*)&peticion, sizeof(struct tipo_elemento)-sizeof(long), 0);
	if(que == PINTAR){
		if(!llega10) pause();
		llega10 = 0;
	}
}
